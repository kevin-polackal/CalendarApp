package cs3500.pa05;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class containing tests for the Event class
 */
public class EventTest {

  private Event event;
  private Event sameEvent;
  private Event differentEvent;


  /**
   * Creates different event instances to be used for testing
   */
  @BeforeEach
  public void setUp() {
    event = new Event("Test Event", "Test Event Description", DayType.MONDAY.rep, "10:00", "60");
    sameEvent =
        new Event("Test Event", "Test Event Description", DayType.MONDAY.rep, "10:00", "60");
    differentEvent =
        new Event("Different Event", "Different Event Description", DayType.TUESDAY.rep, "11:00",
            "120");
  }

  @Test
  public void testGetStartTime() {
    assertEquals("10:00", event.getStartTime());
  }

  @Test
  public void testGetDuration() {
    assertEquals("60", event.getDuration());
  }

  @Test
  public void testEquals() {
    assertTrue(event.equals(sameEvent));
    assertFalse(event.equals(differentEvent));
  }

  @Test
  public void testToString() {
    String expectedString = "Event Name: Test Event\n"
        + "Description: Test Event Description\n"
        + "Time: 10:00\n"
        + "Duration: 60 minutes";
    assertEquals(expectedString, event.toString());
  }

  @Test
  public void testEqualsNotInstanceOf() {
    String notAnEvent = "Not an Event";
    assertFalse(event.equals(notAnEvent));
  }

  @Test
  public void testEqualsDifferentAttributes() {
    Event differentNameEvent = new Event("Different Event",
        "Test Event Description", DayType.MONDAY.rep, "10:00", "60");
    assertFalse(event.equals(differentNameEvent));

    Event differentDescriptionEvent = new Event("Test Event",
        "Different Description", DayType.MONDAY.rep, "10:00", "60");
    assertFalse(event.equals(differentDescriptionEvent));

    Event differentDayEvent = new Event("Test Event",
        "Test Event Description", DayType.TUESDAY.rep, "10:00", "60");
    assertFalse(event.equals(differentDayEvent));

    Event differentStartTimeEvent = new Event("Test Event",
        "Test Event Description", DayType.MONDAY.rep, "11:00", "60");
    assertFalse(event.equals(differentStartTimeEvent));

    Event differentDurationEvent = new Event("Test Event",
        "Test Event Description", DayType.MONDAY.rep, "10:00", "120");
    assertFalse(event.equals(differentDurationEvent));
  }

}


