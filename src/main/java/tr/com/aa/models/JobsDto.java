package tr.com.aa.models;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobsDto {

  @NotNull(message = "Please provide a jobDto")
  private JobDto jobDto;
  @NotNull(message = "Please provide a ftpServerDestination")
  private List<FtpServerDestination> ftpServerDestination;
  @NotNull(message = "Please provide a fileList")
  private List<FileList> fileList;
}