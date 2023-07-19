package cs3500.pa05.view;


import javafx.scene.Scene;

/**
 * Represents a GUI view for a game of Whack-a-Mole.
 */
public interface Iview {
  /**
   * Loads a scene from a Whack-a-Mole GUI layout.
   *
   * @return the layout
   */
  Scene load() throws IllegalStateException;
}

