package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single day in the Journal. A Day object contains tasks and events for that specific
 * day.
 */
public class Day {
  private final String day;
  private ArrayList<Event> events = new ArrayList<>();
  private ArrayList<Task> tasks = new ArrayList<>();

  /**
   * Constructor to create a new Day object.
   *
   * @param day Name of the day
   */
  public Day(String day) {
    this.day = day;
  }

  /**
   * Clear all the tasks and events of the day.
   */
  public void clear() {
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
  }

  /**
   * Removes a specific task from the day.
   *
   * @param t The task to be removed.
   */
  public void removeTask(Task t) {
    for (Task task : tasks) {
      if (task.equals(t)) {
        tasks.remove(task);
        return;
      }
    }
  }

  /**
   * Removes a specific event from the day.
   *
   * @param e The event to be removed.
   */
  public void removeEvent(Event e) {
    for (Event event : events) {
      if (event.equals(e)) {
        events.remove(event);
        return;
      }
    }
  }

  /**
   * Gets the count of completed tasks of the day.
   *
   * @return The count of completed tasks.
   */
  public int getCompletedTasks() {
    int count = 0;
    for (Task t : tasks) {
      if (t.getCompleted()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Gets the list of tasks of the day.
   *
   * @return The list of tasks.
   */
  public ArrayList<String> getTaskList() {
    ArrayList<String> taskList = new ArrayList<>();
    for (Task t : tasks) {
      taskList.add(t.toString());
    }
    return taskList;
  }

  /**
   * Adds an event to the day.
   *
   * @param e The event to be added.
   */
  public void addEvent(Event e) {
    events.add(e);
  }

  /**
   * Adds a task to the day.
   *
   * @param t The task to be added.
   */
  public void addTask(Task t) {
    tasks.add(t);
  }

  /**
   * Gets the count of events of the day.
   *
   * @return The count of events.
   */
  public int getEvents() {
    return events.size();
  }

  /**
   * Gets the count of tasks of the day.
   *
   * @return The count of tasks.
   */
  public int getTasks() {

    return tasks.size();
  }

  /**
   * Gets the name of the day.
   *
   * @return The name of the day.
   */
  public String getDay() {
    return this.day;
  }

  /**
   * Converts the Day object to JSON format. The JSON format includes the day's name, tasks,
   * and events.
   *
   * @return The Day object in JSON format.
   */
  public DayJson toJson() {
    List<EventJson> eventJsons = new ArrayList<>();
    List<TaskJson> taskJsons = new ArrayList<>();
    for (Event e : events) {
      eventJsons.add(e.toJson());
    }
    for (Task t : tasks) {
      taskJsons.add(t.toJson());
    }
    return new DayJson(this.day, eventJsons, taskJsons);
  }
}
