package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A record that represents a JSON representation of a Day object, including properties for the
 * day's name, list of events, and list of tasks.
 */
public record DayJson(
    @JsonProperty("Day") String day,
    @JsonProperty("Events") List<EventJson> events,
    @JsonProperty("Tasks") List<TaskJson> tasks
) {
}
