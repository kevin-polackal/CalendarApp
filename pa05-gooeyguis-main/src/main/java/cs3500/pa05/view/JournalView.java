package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


/**
 * Handles the retrieval of resources and visual elements to be displayed to the user
 */
public class JournalView implements Iview {

  private FXMLLoader loader;

  /**
   * Constructor for a Journal View, initializes the loader and sets the controller and theme
   *
   * @param controller an instance of the controller
   * @param theme the set theme the view will show
   */
  public JournalView(JournalController controller, String theme) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(theme));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a JavaJournal GUI layout.
   *
   * @return the layout
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
