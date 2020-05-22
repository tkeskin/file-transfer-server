package tr.com.aa.models;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectDto {

  private UUID id;
  @NotNull
  private String name;
  @NotNull
  private String downloadPath;
}