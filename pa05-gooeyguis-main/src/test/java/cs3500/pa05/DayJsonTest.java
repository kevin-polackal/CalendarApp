package cs3500.pa05;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.TaskJson;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for DayJson record
 */
public class DayJsonTest {

  private DayJson dayJson;
  private EventJson eventJson;
  private TaskJson taskJson;

  /**
   * Creates an instance of and event, task, and day to be used for testing
   */
  @BeforeEach
  public void setUp() {
    eventJson = new EventJson("Event1", "Description1", "Day1", "10:00", "2 hours");
    taskJson = new TaskJson("Task1", "Description1", "Day1", true);

    List<EventJson> events = Arrays.asList(eventJson);
    List<TaskJson> tasks = Arrays.asList(taskJson);

    dayJson = new DayJson("Day1", events, tasks);
  }

  @Test
  public void testDayJsonNotNull() {
    assertNotNull(dayJson);
  }

  @Test
  public void testGetDay() {
    assertEquals("Day1", dayJson.day());
  }

  @Test
  public void testGetEvents() {
    assertEquals(1, dayJson.events().size());
    assertEquals(eventJson, dayJson.events().get(0));
  }

  @Test
  public void testGetTasks() {
    assertEquals(1, dayJson.tasks().size());
    assertEquals(taskJson, dayJson.tasks().get(0));
  }
}

