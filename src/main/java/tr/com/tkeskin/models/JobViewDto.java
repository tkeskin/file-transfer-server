package tr.com.tkeskin.models;

import java.util.UUID;
import lombok.Data;

@Data
public class JobViewDto {

  private UUID id;
  private UUID createdById;
  private String createdBy;
  private String createdDateTime;
  private Integer jobStatus;
}