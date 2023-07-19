package cs3500.pa05;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.BujoFileWalker;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for BujoFileWalker class
 */
public class BujoFileWalkerTest {
  private BujoFileWalker walker;
  private Path testDir;
  private Path testFile;
  private BasicFileAttributes attrs;

  /**
   * Initializes test directory, file, and walker to be used for testing
   *
   * @throws IOException if there is a failure to read files
   */
  @BeforeEach
  public void setUp() throws IOException {
    walker = new BujoFileWalker();
    testDir = Paths.get("");
    testFile = Paths.get("");

    attrs = Files.readAttributes(testFile, BasicFileAttributes.class);

    // Create a temporary directory and file for the test
    if (!Files.exists(testDir)) {
      Files.createDirectory(testDir);
    }

    if (!Files.exists(testFile)) {
      Files.createFile(testFile);
    }
  }

  @Test
  public void testPreVisitDirectory() throws IOException {
    assertEquals(FileVisitResult.CONTINUE, walker.preVisitDirectory(testDir, attrs));
  }

  @Test
  public void testVisitFileFailed() {
    IOException ex = assertThrows(IOException.class, () -> walker.visitFileFailed(testFile,
        new IOException()));
    assertEquals("Bad File!", ex.getMessage());
  }

  @Test
  public void testVisitFile() throws IOException {
    assertEquals(FileVisitResult.CONTINUE, walker.visitFile(testFile, attrs));
    assertFalse(walker.getBujoFiles().contains(testFile.toString()));
  }

  @Test
  public void testPostVisitDirectory() throws IOException {
    assertEquals(FileVisitResult.CONTINUE, walker.postVisitDirectory(testDir, null));
  }

  @Test
  public void testGetBujoFiles() throws IOException {
    walker.visitFile(testFile, attrs);
    ArrayList<String> bujoFiles = walker.getBujoFiles();
    assertFalse(bujoFiles.contains(testFile.toString()));
  }
}

