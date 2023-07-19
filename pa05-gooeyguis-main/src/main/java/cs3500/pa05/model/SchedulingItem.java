package cs3500.pa05.model;

/**
 * An abstract class that represents a general item in a schedule. Each scheduling item has a name,
 * description, and day associated with it.
 */
public abstract class SchedulingItem {
  protected final String name;
  protected final String description;

  protected final String day;

  /**
   * Constructor to create a new scheduling item.
   *
   * @param itemName    The name of the scheduling item.
   * @param description A description of the scheduling item.
   * @param day         The day on which the scheduling item occurs.
   */
  SchedulingItem(String itemName, String description, String day) {
    this.name = itemName;
    this.description = description;
    this.day = day;
  }

  /**
   * Get the name of the scheduling item.
   *
   * @return Name of the scheduling item.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the description of the scheduling item.
   *
   * @return Description of the scheduling item.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Get the day on which the scheduling item occurs.
   *
   * @return Day on which the scheduling item occurs.
   */
  public String getDay() {
    return this.day;
  }
}
