package cs3500.pa05.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * To walk the file-system and accumulate any markdown files
 */
public class BujoFileWalker implements FileVisitor<Path> {
  private ArrayList<String> bujoFiles = new ArrayList<>();

  /**
   *Invoked for a directory before entries in the directory are visited.
   *If this method returns CONTINUE, then entries in the directory are visited.
   *
   * @param dir a reference to the directory
   * @param attrs the directory's basic attributes
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws
      IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a file that could not be visited. This method is invoked
   * if the file's attributes could not be read, the file is a directory that could not be opened,
   * and other reasons.
   *
   * @param file a reference to the file
   * @param exc the I/O exception that prevented the file from being visited
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    handleFileFailed();
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a file in a directory. If the file is a markdown file, it is
   * added to the collection of MarkdownFile
   *
   * @param file a reference to the file
   *
   * @param attrs the file's basic attributes
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    if (file.toString().contains(".bujo")) {
      String path = file.toString();
      bujoFiles.add(path);
    }

    return FileVisitResult.CONTINUE;
  }

  /**
   *  Invoked for a directory after entries in the directory, and all of
   *  their descendants, have been visited
   *
   * @param dir a reference to the directory
   * @param exc null if the iteration of the directory completes
   *            without an error; otherwise the I/O exception that
   *            caused the iteration of the directory to complete
   *            prematurely
   *
   * @return the visit result
   *
   * @throws IOException if an I/O error occurs
   */
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   *  Returns the list of MarkdownFiles
   *
   * @return the markdown files
   */
  public ArrayList<String> getBujoFiles() {
    return this.bujoFiles;
  }

  /**
   * Extension of visitFileFailed() for testing purposes
   *
   * @throws IOException a bad file
   */
  public void handleFileFailed() throws IOException {
    throw new IOException("Bad File!");
  }
}