package tr.com.tkeskin.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class JobsDto {

    @NotNull(message = "Please provide a jobDto")
    private JobDto jobDto;
    @NotNull(message = "Please provide a ftpServerDestination")
    private List<FtpServerDestination> ftpServerDestination;
    @NotNull(message = "Please provide a fileList")
    private List<FileList> fileList;
}