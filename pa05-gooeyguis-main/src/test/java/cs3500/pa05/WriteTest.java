package cs3500.pa05;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa05.model.Write;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

/**
 * Class containing tests for the Write class
 */
public class WriteTest {


  /**
   * Writes a JSON string to a specified file and then verifies the file was created
   */
  @Test
  public void testWriteToFile() {
    String node = "{\"name\": \"John\", \"age\": 30}";
    String filePath = "output.txt";

    try {
      Write.writeToFile(node, filePath);
      File file = new File(filePath);
      assertTrue(file.exists());
      String content = new String(Files.readAllBytes(Paths.get(filePath)));
      assertEquals(node, content);
      file.delete();
    } catch (IOException e) {
      fail("IOException occurred: " + e.getMessage());
    }
  }
}