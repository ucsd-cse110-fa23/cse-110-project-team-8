package PantryPalTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import PantryPal.*;

public class SortTests {
    RecipeList lst;

    @BeforeEach
    void setUp() {
        this.lst = new RecipeList();
        Recipe r1 = new Recipe("a", "instruction", "ingredients", "dinner");
        r1.setCreationTime();
        Recipe r2 = new Recipe("b", "instruction", "ingredients", "dinner");
        r2.setCreationTime();
        Recipe r3 = new Recipe("c", "instruction", "ingredients", "dinner");
        r3.setCreationTime();
        lst.add(r1);
        lst.add(r2);
        lst.add(r3);
    }

    // BDD1
    @Test
    void testsortAtoZ() {
        lst.sortAtoZ();
        int lessthan = lst.get(0).getTitle().compareTo(lst.get(1).getTitle());
        assertEquals(lessthan < 0, true);
    }

    // BDD2
    @Test
    void testsortZtoA() {
        lst.sortZtoA();
        int greaterthan = lst.get(0).getTitle().compareTo(lst.get(1).getTitle());
        assertEquals(greaterthan > 0, true);
    }

    // BDD3
    @Test
    void testsortOldtoNew() {
        lst.sortOldtoNew();
        int greaterthan = lst.get(0).getCreationTime().compareTo(lst.get(1).getCreationTime());
        assertEquals(greaterthan > 0, false);
    }

    // BDD4
    @Test
    void testsortNewtoOld() {
        lst.sortNewtoOld();
        int lessthan = lst.get(0).getCreationTime().compareTo(lst.get(1).getCreationTime());
        assertEquals(lessthan < 0, false);
    }
}