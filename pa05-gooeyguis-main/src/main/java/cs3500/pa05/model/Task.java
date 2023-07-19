package cs3500.pa05.model;

/**
 * This class represents a task in the scheduling system and extends the abstract class
 */
public class Task extends SchedulingItem {
  private boolean isCompleted;

  /**
   * Creates a new task with the specified name, description, day and completion status.
   *
   * @param itemName    the name of the task
   * @param description the description of the task
   * @param day         the day the task is scheduled for
   * @param isCompleted the completion status of the task
   */
  public Task(String itemName, String description, String day, Boolean isCompleted) {
    super(itemName, description, day);
    this.isCompleted = isCompleted;
  }

  /**
   * Marks the task as completed.
   */
  public void setCompleted() {
    this.isCompleted = true;
  }

  /**
   * Returns the completion status of the task.
   *
   * @return the completion status of the task
   */
  public boolean getCompleted() {
    return this.isCompleted;
  }

  /**
   * Returns a string representation of the task.
   *
   * @return a string representation of the task
   */
  public String toString() {
    String completion;
    if (isCompleted) {
      completion = " Yes";
    } else {
      completion = " No";
    }
    return "Day: " + this.day + "\n"
        + "Task Name: " + name + "\n"
        + "Description: " + description + "\n"
        + "Completed?: " + completion;
  }

  /**
   * Converts the task into a TaskJson object.
   */
  public TaskJson toJson() {
    return new TaskJson(this.name, this.description, this.day, this.isCompleted);
  }

  /**
   * Checks if this task is equal to the specified object.
   *
   * @param o the object to compare with
   * @return true if the specified object is equal to this task; false otherwise
   */
  public boolean equals(Object o) {
    if (!(o instanceof Task)) {
      return false;
    }
    Task t = (Task) o;

    return this.name.equals(t.name)
        && this.day.equals(t.day)
        && this.description.equals(t.description)
        && this.isCompleted == t.isCompleted;
  }
}
