package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The JournalWeek class implements IWeek interface and manages a week of the journal.
 */
public class JournalWeek implements Iweek {

  private String title;

  private Day[] days = {new Day(DayType.SUNDAY.rep), new Day(DayType.MONDAY.rep),
      new Day(DayType.TUESDAY.rep), new Day(DayType.WEDNESDAY.rep), new Day(DayType.THURSDAY.rep),
      new Day(DayType.FRIDAY.rep), new Day(DayType.SATURDAY.rep)};

  private int maxEvents = Integer.MAX_VALUE;

  private int maxTasks = Integer.MAX_VALUE;

  private String theme;

  /**
   * The constructor takes a theme for the week.
   *
   * @param theme the theme of the week.
   */
  public JournalWeek(String theme) {
    this.theme = theme;
  }

  /**
   * Get the information about tasks of the day.
   *
   * @param numDayRep the representation number of the day.
   * @return the array with total tasks, completed tasks, and the difference between them.
   */
  public int[] getDayTaskInfo(int numDayRep) {
    int dayTasks = days[numDayRep].getTasks();
    int completed = days[numDayRep].getCompletedTasks();
    int difference = dayTasks - completed;
    int[] returnPackage = {dayTasks, completed, difference};
    return returnPackage;
  }

  /**
   * Remove a task from all the days.
   *
   * @param t the task to be removed.
   */
  public void removeTask(Task t) {
    for (Day d : days) {
      d.removeTask(t);
    }
  }

  /**
   * Remove an event from all the days.
   *
   * @param e the event to be removed.
   */
  public void removeEvent(Event e) {
    for (Day d : days) {
      d.removeEvent(e);
    }
  }

  /**
   * Get the total number of tasks from all the days.
   *
   * @return the total number of tasks.
   */
  public int getTotalTasks() {
    int count = 0;
    for (Day d : days) {
      count += d.getTasks();
    }
    return count;
  }

  /**
   * Get the total number of completed tasks from all the days.
   *
   * @return the total number of completed tasks.
   */
  public int getCompletedTasks() {
    int count = 0;
    for (Day d : days) {
      count += d.getCompletedTasks();
    }
    return count;
  }

  /**
   * Get the total number of events from all the days.
   *
   * @return the total number of events.
   */
  public int getTotalEvents() {
    int count = 0;
    for (Day d : days) {
      count += d.getEvents();
    }
    return count;
  }

  /**
   * Clear all the tasks and events from the week and reset maxTasks and maxEvents to
   * default values.
   */
  public void clear() {
    this.title = null;
    this.maxEvents = Integer.MAX_VALUE;
    this.maxTasks = Integer.MAX_VALUE;
    for (Day d : days) {
      d.clear();
    }
  }

  /**
   * Get the list of all tasks from the week.
   *
   * @return the list of all tasks.
   */
  public ArrayList<String> getTaskList() {
    ArrayList<String> taskList = new ArrayList<>();
    for (Day d : days) {
      taskList.addAll(d.getTaskList());
    }
    return taskList;
  }

  /**
   * Set a theme for the week.
   *
   * @param theme the theme to set.
   */
  public void setTheme(String theme) {
    this.theme = theme;
  }

  /**
   * Get the theme of the week.
   *
   * @return the theme of the week.
   */
  public String getTheme() {
    return this.theme;
  }

  /**
   * Set a limit on the number of events for the week.
   *
   * @param num the maximum number of events.
   */
  public void setMaxEvents(int num) {
    this.maxEvents = num;
  }

  /**
   * Set a limit on the number of tasks for the week.
   *
   * @param num the maximum number of tasks.
   */
  public void setMaxTasks(int num) {
    this.maxTasks = num;
  }

  /**
   * Get the maximum number of events for the week.
   *
   * @return the maximum number of events.
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Get the maximum number of tasks for the week.
   *
   * @return the maximum number of tasks.
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Add a task to the specified day of the week.
   *
   * @param day the day of the week.
   * @param t   the task to be added.
   */
  public void addTask(String day, Task t) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        d.addTask(t);
      }
    }
  }

  /**
   * Add an event to the specified day of the week.
   *
   * @param day the day of the week.
   * @param e   the event to be added.
   */
  public void addEvent(String day, Event e) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        d.addEvent(e);
      }
    }
  }

  /**
   * Set a title for the week.
   *
   * @param str the title to be set.
   */
  public void setTitle(String str) {
    this.title = str;
  }

  /**
   * Get the title of the week.
   *
   * @return the title of the week.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Get the total number of tasks for the specified day of the week.
   *
   * @param day the day of the week.
   * @return the total number of tasks, or -1 if the day doesn't exist.
   */
  public int getDaysTasks(String day) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        return d.getTasks();
      }
    }
    return -1;
  }

  /**
   * Get the total number of events for the specified day of the week.
   *
   * @param day the day of the week.
   * @return the total number of events, or -1 if the day doesn't exist.
   */
  public int getDaysEvents(String day) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        return d.getEvents();
      }
    }
    return -1;
  }

  /**
   * Save the current week to a JSON file.
   *
   * @return the filename of the JSON file, or null if there's an IOException.
   * @throws IOException if unable to write to the file.
   */
  public String saveToBujo() throws IOException {

    List<DayJson> daysJson = new ArrayList<>();
    for (Day d : days) {
      daysJson.add(d.toJson());
    }
    WeekJson weekJson =
        new WeekJson(this.title, this.maxTasks, this.maxEvents, this.theme, daysJson);
    JsonNode node = JsonUtils.serializeRecord(weekJson);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString;
    try {
      jsonString = objectMapper.writeValueAsString(node);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    String file = "src/bujoFiles/" + this.title + ".bujo";
    System.out.println(node);
    Write.writeToFile(jsonString, file);
    return file;
  }
}
