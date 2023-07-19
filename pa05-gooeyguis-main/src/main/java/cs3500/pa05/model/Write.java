package cs3500.pa05.model;


import java.io.FileWriter;
import java.io.IOException;

/**
 * To write the complied study-guide notes into a single file
 */
public class Write {

  /**
   * To write the complied study-guide notes into a single file.
   * If the given file does not exist, one is created
   *
   * @param out the file-path to an already existing file or a file to be created
   * @throws IOException if an I/O error occurs
   */
  public static <T> void writeToFile(String node, String out) throws IOException {
    FileWriter fileWriter = new FileWriter(out);
    fileWriter.write(node);
    System.out.println("JsonNode successfully written to file.");
    fileWriter.close();

  }
}
