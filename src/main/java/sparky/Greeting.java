package sparky;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Greeting {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String greeting;
}
