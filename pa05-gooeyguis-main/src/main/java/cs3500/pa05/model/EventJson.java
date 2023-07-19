package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the JSON structure of an event. It includes properties such as name, description, day,
 * start time, and duration of the event.
 */
public record EventJson(
    @JsonProperty("Name") String name,
    @JsonProperty("Description") String description,
    @JsonProperty("Day") String day,
    @JsonProperty("Start") String start,
    @JsonProperty("Duration") String duration
) {
}
