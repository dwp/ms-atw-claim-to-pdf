package uk.gov.dwp.health.atw.msclaimtopdf.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeneratedResponse {

  @JsonProperty("fileId")
  @NonNull
  private String fileId;
}
