package cs3500.pa05;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cs3500.pa05.model.TaskJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TaskJson record
 */
public class TaskJsonTest {

  private TaskJson taskJson;

  @BeforeEach
  public void setUp() {
    taskJson = new TaskJson("Task1", "Description1", "Day1", true);
  }

  @Test
  public void testTaskJsonNotNull() {
    assertNotNull(taskJson);
  }

  @Test
  public void testGetName() {
    assertEquals("Task1", taskJson.name());
  }

  @Test
  public void testGetDescription() {
    assertEquals("Description1", taskJson.description());
  }

  @Test
  public void testGetDay() {
    assertEquals("Day1", taskJson.day());
  }

  @Test
  public void testGetCompletion() {
    assertEquals(true, taskJson.bool());
  }
}

