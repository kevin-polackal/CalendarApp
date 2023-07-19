package cs3500.pa05;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.model.Task;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for JournalWeek class
 */
public class JournalWeekTest {

  private JournalWeek journalWeek2;


  private JournalWeek journalWeek;
  private Task task;
  private Task task2;
  private Event event;

  /**
   * Creates varies task, event, and journal week instances to be used during testing
   */
  @BeforeEach
  public void setUp() {
    task = new Task("Test Task", "Task Description", DayType.MONDAY.rep, false);
    task2 = new Task("Test Task 2", "Task Description 2", DayType.TUESDAY.rep, true);
    event = new Event("Test Event", "Test Event Description", DayType.TUESDAY.rep, "10:00", "60");
    journalWeek = new JournalWeek("Default Theme");
    journalWeek2 = new JournalWeek("Default Theme");
    journalWeek.addTask(DayType.MONDAY.rep, task);
    journalWeek.addTask(DayType.TUESDAY.rep, task2);
    journalWeek.addEvent(DayType.TUESDAY.rep, event);
  }

  @Test
  public void testGetDayTaskInfo() {
    int[] mondayInfo = journalWeek.getDayTaskInfo(DayType.MONDAY.ordinal());
    assertEquals(0, mondayInfo[0]); // total tasks
    assertEquals(0, mondayInfo[1]); // completed tasks
    assertEquals(0, mondayInfo[2]); // remaining tasks

    int[] tuesdayInfo = journalWeek.getDayTaskInfo(DayType.TUESDAY.ordinal());
    assertEquals(1, tuesdayInfo[0]); // total tasks
    assertEquals(0, tuesdayInfo[1]); // completed tasks
    assertEquals(1, tuesdayInfo[2]); // remaining tasks
  }

  @Test
  public void testGetTaskList() {
    ArrayList<String> tasks = journalWeek.getTaskList();
    assertEquals(2, tasks.size());
    assertTrue(tasks.stream().anyMatch(task -> task.contains("Test Task")));
    assertTrue(tasks.stream().anyMatch(task -> task.contains("Test Task 2")));
  }

  @Test
  public void testGetTotalTasks() {
    assertEquals(2, journalWeek.getTotalTasks());
  }

  @Test
  public void testGetCompletedTasks() {
    assertEquals(1, journalWeek.getCompletedTasks());
  }

  @Test
  public void testGetTotalEvents() {
    assertEquals(1, journalWeek.getTotalEvents());
  }

  @Test
  public void testRemoveTask() {
    journalWeek.removeTask(task);
    assertEquals(1, journalWeek.getTotalTasks());
    assertTrue(
        journalWeek.getTaskList().stream().anyMatch(taskStr -> taskStr.contains("Test Task")));
  }

  @Test
  public void testSetMaxEvents() {
    journalWeek.setMaxEvents(10);
    assertEquals(10, journalWeek.getMaxEvents());
  }

  @Test
  public void testSetMaxTasks() {
    journalWeek.setMaxTasks(20);
    assertEquals(20, journalWeek.getMaxTasks());
  }

  @Test
  public void testSetTitle() {
    String title = "Test Title";
    journalWeek.setTitle(title);
    assertEquals(title, journalWeek.getTitle());
  }

  @Test
  public void testAddEvent() {
    Event
        event =
        new Event("Test Event", "Test Event Description", DayType.TUESDAY.rep, "10:00", "60");
    journalWeek.addEvent(DayType.TUESDAY.rep, event);
    assertEquals(2, journalWeek.getDaysEvents(DayType.TUESDAY.rep));
  }

  @Test
  public void testSetAndGetTheme() {
    String theme = "Test Theme";
    journalWeek.setTheme(theme);
    assertEquals(theme, journalWeek.getTheme());
  }

  @Test
  public void testClear() {
    journalWeek.clear();
    assertNull(journalWeek.getTitle());
    assertEquals(Integer.MAX_VALUE, journalWeek.getMaxEvents());
    assertEquals(Integer.MAX_VALUE, journalWeek.getMaxTasks());
  }

  @Test
  public void testSaveToBujoIoException() {
    journalWeek.setTitle("Invalid/Directory");

    assertThrows(IOException.class, () -> {
      journalWeek.saveToBujo();
    });
  }

  @Test
  public void testAddAndGetDaysTasks() {
    journalWeek2.addTask(DayType.MONDAY.rep, task);

    assertEquals(1, journalWeek2.getDaysTasks(DayType.MONDAY.rep));

    journalWeek2.addTask(DayType.MONDAY.rep, task);
    journalWeek2.addTask(DayType.MONDAY.rep, task);

    assertEquals(3, journalWeek2.getDaysTasks(DayType.MONDAY.rep));

    assertEquals(0, journalWeek2.getDaysTasks(DayType.TUESDAY.rep));
  }

  @Test
  public void testRemoveEvent() {
    journalWeek.removeEvent(event);
    assertEquals(0, journalWeek.getTotalEvents());
  }

}


