package cs3500.pa05.model;

/**
 * Represents an event which is a specific type of SchedulingItem. The Event class is mainly used
 * to manage and manipulate the event data within the system.
 */
public class Event extends SchedulingItem {
  private final String startTime;
  private final String duration;

  /**
   * Constructor for creating a new Event.
   *
   * @param itemName    The name of the event.
   * @param description A description of the event.
   * @param day         The day on which the event occurs.
   * @param start       The start time of the event.
   * @param duration    The duration of the event.
   */
  public Event(String itemName, String description, String day, String start, String duration) {
    super(itemName, description, day);
    this.startTime = start;
    this.duration = duration;
  }

  /**
   * Get the start time of the event.
   *
   * @return Start time of the event.
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Get the duration of the event.
   *
   * @return Duration of the event.
   */
  public String getDuration() {
    return this.duration;
  }

  /**
   * Get a string representation of the event.
   *
   * @return A string representation of the event.
   */
  public String toString() {
    return "Event Name: " + name + "\n"
        + "Description: " + description + "\n"
        + "Time: " + startTime + "\n"
        + "Duration: " + duration + " minutes";
  }

  /**
   * Convert the event object into its corresponding EventJson representation.
   *
   * @return EventJson representation of the event.
   */
  public EventJson toJson() {
    return new EventJson(this.name, this.description, this.day, this.startTime, this.duration);
  }

  /**
   * Checks if the current event is equal to the provided object.
   *
   * @param o The object to be compared with the current event.
   * @return True if the provided object is an event and has the same attributes as the current
   *         event.
   */
  public boolean equals(Object o) {
    if (!(o instanceof Event)) {
      return false;
    }
    Event e = (Event) o;

    return this.name.equals(e.name)
        && this.day.equals(e.day)
        && this.description.equals(e.description)
        && this.startTime.equals(e.startTime)
        && this.duration.equals(e.duration);
  }
}
