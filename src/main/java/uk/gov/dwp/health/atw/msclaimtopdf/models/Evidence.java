package uk.gov.dwp.health.atw.msclaimtopdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@SuperBuilder
public class Evidence {

  @JsonProperty(value = "fileId")
  @NonNull
  String fileId;

  @JsonProperty(value = "fileName")
  @NonNull
  String fileName;
}
