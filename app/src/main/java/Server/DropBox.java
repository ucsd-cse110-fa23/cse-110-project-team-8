package Server;

import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.awt.image.BufferedImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import javafx.geometry.Pos;

import com.dropbox.core.*;

public class DropBox {
  private static final String ACCESS_TOKEN = "sl.Bq2IXpeHMCYfNSvEcOhrfgJc_TyixbKMgF62KrGF7OsFdElt6-jbBHLwdUfnjDqYyuFAimTggO3W363oh6HWKyfB18qbYH3fDkLfOGfS3pUDC_b00yRUv-DosxbBXSPYWmFsean-_9qCoddT2BJDPyw";

  public static void main(String args[]) throws DbxException, FileNotFoundException, IOException {

    combinePDF();

    // Create Dropbox client
    DbxRequestConfig config1 = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    DbxClientV2 client = new DbxClientV2(config1, ACCESS_TOKEN);
    // Get current account info
    FullAccount account = client.users()
        .getCurrentAccount();
    System.out.println(account.getName().getDisplayName());
    client.files().deleteV2("/combined.pdf");
    try (InputStream in = new FileInputStream("combined.pdf")) {
      FileMetadata metadata = client.files().uploadBuilder("/combined.pdf")
          .uploadAndFinish(in);
    }
    ListFolderResult result = client.files().listFolder("");
    while (true) {
      for (Metadata metadata : result.getEntries()) {
        System.out.println(metadata.getPathLower());
      }

      if (!result.getHasMore()) {
        break;
      }

      result = client.files().listFolderContinue(result.getCursor());
    }
    try {
      String url = client.sharing().createSharedLinkWithSettings("/combined.pdf").getUrl();
      System.out.println(url);
    } catch (Exception e) {
    }

  }

  public static void combinePDF() {
    String imagePath = "image.jpeg"; // Update the file extension based on your actual image type
    String pdfFilePath = "combined.pdf";

    try {
      PDDocument document = new PDDocument();
      PDPage page = new PDPage();
      document.addPage(page);

      PDPageContentStream contentStream = new PDPageContentStream(document, page);

      // Add text to the PDF using a font that supports a wide range of characters
      contentStream.setFont(PDType1Font.COURIER, 12);
      contentStream.beginText();
      contentStream.newLineAtOffset(100, 700);
      contentStream.showText("textContent");
      contentStream.endText();

      // Add image to the PDF based on the file type
      PDImageXObject imageXObject = createImageXObject(imagePath, document);
      contentStream.drawImage(imageXObject, 100, 500, imageXObject.getWidth() / 2, imageXObject.getHeight() / 2);

      contentStream.close();

      // Save the combined PDF
      document.save(pdfFilePath);
      document.close();

      System.out.println("PDF created successfully!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static PDImageXObject createImageXObject(String imagePath, PDDocument document) throws IOException {
    File imageFile = new File(imagePath);
    String fileExtension = imagePath.substring(imagePath.lastIndexOf(".") + 1).toLowerCase();

    switch (fileExtension) {
      case "png":
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        return LosslessFactory.createFromImage(document, bufferedImage);
      case "jpg":
      case "jpeg":
        return PDImageXObject.createFromFileByExtension(imageFile, document);
      default:
        throw new IllegalArgumentException("Unsupported image type: " + fileExtension);
    }
  }
}
