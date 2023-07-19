package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A record that represents a JSON representation of a Week object, including properties for the
 * week's title, maximum number of daily tasks, maximum number of daily events, theme,
 */
public record WeekJson(
    @JsonProperty("Title") String title,
    @JsonProperty("Max-Daily-Tasks") int maxTasks,
    @JsonProperty("Max-Daily-Events") int maxEvents,
    @JsonProperty("Theme") String theme,
    @JsonProperty("Days") List<DayJson> days
) {
}
