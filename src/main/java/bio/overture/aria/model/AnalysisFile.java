package bio.overture.aria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisFile {
  private String objectId;
  private String studyId;
  private String analysisId;
  private String fileName;
  private String fileType;
  private String fileMd5sum;
  private Long fileSize;
  private String fileAccess;
  private String dataType;
}
