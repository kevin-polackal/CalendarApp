package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Day class
 */
public class DayTest {

  private Day day;

  @BeforeEach
  public void setUp() {
    day = new Day(DayType.MONDAY.rep);
  }

  @Test
  public void testAddTask() {
    Task task = new Task("Test Task", "Test Task Description", DayType.MONDAY.rep, false);
    day.addTask(task);
    assertEquals(1, day.getTasks());
  }

  @Test
  public void testAddEvent() {
    Event
        event =
        new Event("Test Event", "Test Event Description", DayType.MONDAY.rep, "10:00", "60");
    day.addEvent(event);
    assertEquals(1, day.getEvents());
  }

  @Test
  public void testToJson() {
    Task task1 = new Task("Task 1", "Description 1", "Monday", false);
    Task task2 = new Task("Task 2", "Description 2", "Monday", false);

    Event event1 = new Event("Event 1", "Description 1", "Monday", "10:00", "60");
    Event event2 = new Event("Event 2", "Description 2", "Monday", "12:00", "120");

    day.addTask(task1);
    day.addTask(task2);
    day.addEvent(event1);
    day.addEvent(event2);

    DayJson dayJson = day.toJson();
    assertEquals(day.getDay(), dayJson.day());
    assertEquals(2, dayJson.tasks().size());
    assertEquals(2, dayJson.events().size());
    assertEquals(task1.toJson(), dayJson.tasks().get(0));
    assertEquals(task2.toJson(), dayJson.tasks().get(1));
  }
}

