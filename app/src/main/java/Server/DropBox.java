package Server;

import java.io.*;
import javax.imageio.ImageIO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.text.Text;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import com.dropbox.core.*;

public class DropBox {


  DbxClientV2 client;
  private static final String ACCESS_TOKEN = "sl.BrGVppKze26dE8IdkNzDizFIPL0b7UjK0xor2NKPnVmlHa5JVeDCAfchpA2hcsCBsV0o5LcsmN85g0_sG_h66pfnqEaOTGDAKQ3sNH-eqTvlscOmloY3YdejR0Lamj4Li8ku4-XpwTa8y_gRFZ9N3Fk";

  private static final String ACCESS_TOKEN = "sl.BrKabeYmB0qpiFBXnBVl3JZ0MHtY9immPA03NSQHv7kh9OQlAfmPgk0FmRl-utf_aQp_XJXTLB4CzhO10hmDQEftHmK-dnFZJ9rNvuD2gJAR-BgGdTJ4n4crtXC_hnVRwKtehr-8D_AcbFKjQrQGAiw";


  public String DropBox(String title, String ingredients, String instructions)
      throws DbxException, FileNotFoundException, IOException {

    String url = "";
    boolean exists = false;
    combinePDF(title, ingredients, instructions);

    // Create Dropbox client
    DbxRequestConfig config1 = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    this.client = new DbxClientV2(config1, ACCESS_TOKEN);
    // Get current account info
    FullAccount account = client.users()
        .getCurrentAccount();
    System.out.println(account.getName().getDisplayName());
    ListFolderResult result = client.files().listFolder("");
    while (true) {
      if (!result.getHasMore()) {
        break;
      }
      result = client.files().listFolderContinue(result.getCursor());
    }
    String urlTitle = title.replace(" ", "");
    for (Metadata metadata : result.getEntries()) {
      if ((metadata.getName()).equals(urlTitle)) {
        exists = true;
      }
    }
    if (exists == false) {
      try (InputStream in = new FileInputStream(title + ".pdf")) {
        client.files().createFolderV2("/" + urlTitle);
        FileMetadata metadata = client.files().uploadBuilder("/" + urlTitle + "/" + title + ".pdf")
            .uploadAndFinish(in);
      }
    }
    if (exists == false) {
      try {
        url = client.sharing().createSharedLinkWithSettings("/" + urlTitle).getUrl();
        System.out.println(url);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return url;
  }

  public void updateShare(String title, String ingredients, String instructions)
      throws DeleteErrorException, DbxException, FileNotFoundException, IOException {
    combinePDF(title, ingredients, instructions);
    this.client.files().deleteV2("/" + title.replace(" ", "") + "/" + title + ".pdf");
    try (InputStream in = new FileInputStream(title + ".pdf")) {
      FileMetadata metadata = client.files().uploadBuilder("/" + title.replace(" ", "") + "/" + title + ".pdf")
          .uploadAndFinish(in);
    }
  }

  public void deleteFile(String title) throws DeleteErrorException, DbxException {
    Metadata metadata = this.client.files().delete("/" + title.replace(" ", "") + "/" + title + ".pdf");
    Metadata metadata1 = this.client.files().delete("/" + title.replace(" ", ""));
  }

  public void deleteAll() throws DeleteErrorException, DbxException {
    // Create Dropbox client
    DbxRequestConfig config1 = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    this.client = new DbxClientV2(config1, ACCESS_TOKEN);
    // Get current account info
    FullAccount account = client.users()
        .getCurrentAccount();
    ListFolderResult result = this.client.files().listFolder("");
    while (true) {
      if (!result.getHasMore()) {
        break;
      }
      result = client.files().listFolderContinue(result.getCursor());
    }
    for (Metadata metadata : result.getEntries()) {
      this.client.files().delete("/" + metadata.getName());
    }
  }

  public static void combinePDF(String title, String ingredients, String instructions) {

    // Input JPG file and text
    String jpgFilePath = title + ".png";

    // Output PDF file
    String pdfFilePath = title + ".pdf";
    Document document = new Document();
    try {
      PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
      document.open();
      document.add(new Paragraph("Title: "));
      document.add(new Paragraph(title));
      document.add(new Paragraph("Ingredients: "));
      document.add(new Paragraph(ingredients));
      document.add(new Paragraph("Instructions: "));
      document.add(new Paragraph(instructions));
      Image image1 = Image.getInstance(jpgFilePath);

      // Add the image to the Document
      document.add(image1);
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("PDF created successfully at: " + pdfFilePath);
  }

  // for testing purpose
  public String DropBoxTest(String title, String ingredients, String instructions, String jpgFilePath)
      throws DbxException, FileNotFoundException, IOException {

    String url = "";

    combinePDFTest(title, ingredients, instructions, jpgFilePath);

    // Create Dropbox client
    DbxRequestConfig config1 = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    DbxClientV2 client = new DbxClientV2(config1, ACCESS_TOKEN);
    // Get current account info
    FullAccount account = client.users()
        .getCurrentAccount();
    System.out.println(account.getName().getDisplayName());

    ListFolderResult result = client.files().listFolder("");
    while (true) {
      for (Metadata metadata : result.getEntries()) {
        System.out.println(metadata.getPathLower());
        if (metadata.getName().equals(title + ".pdf")) {
          client.files().deleteV2("/" + metadata.getName());
        }
      }
      if (!result.getHasMore()) {
        break;
      }
      result = client.files().listFolderContinue(result.getCursor());
    }
    try (InputStream in = new FileInputStream(title + ".pdf")) {
      FileMetadata metadata = client.files().uploadBuilder("/" + title + ".pdf")
          .uploadAndFinish(in);
    }
    try {
      url = client.sharing().createSharedLinkWithSettings("/" + title +
          ".pdf").getUrl();
      System.out.println(url);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return url;
  }

  public static void combinePDFTest(String title, String ingredients, String instructions, String jpgFilePath) {

    // Output PDF file
    String pdfFilePath = title + ".pdf";
    Document document = new Document();
    try {
      PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
      document.open();
      document.add(new Paragraph("Title: "));
      document.add(new Paragraph(title));
      document.add(new Paragraph("Ingredients: "));
      document.add(new Paragraph(ingredients));
      document.add(new Paragraph("Instructions: "));
      document.add(new Paragraph(instructions));
      Image image1 = Image.getInstance(jpgFilePath);

      // Add the image to the Document
      document.add(image1);
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("PDF created successfully at: " + pdfFilePath);
  }

  public void delete(String title) throws ListFolderErrorException, DbxException {
    DbxRequestConfig config1 = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    DbxClientV2 client = new DbxClientV2(config1, ACCESS_TOKEN);
    ListFolderResult result = client.files().listFolder("");
    for (Metadata metadata : result.getEntries()) {
      System.out.println(metadata.getPathLower());
      if (metadata.getName().equals(title + ".pdf")) {
        client.files().deleteV2("/" + metadata.getName());
      }
    }
  }

}
