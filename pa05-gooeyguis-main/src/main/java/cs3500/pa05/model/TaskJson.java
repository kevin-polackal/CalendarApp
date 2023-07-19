package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A record that represents a JSON representation of a Task object, including properties for
 * the task's name, description, the day it's assigned to, and its completion status.
 */
public record TaskJson(
    @JsonProperty("Name") String name,
    @JsonProperty("Description") String description,
    @JsonProperty("Day") String day,
    @JsonProperty("Completion") boolean bool
) {
}
