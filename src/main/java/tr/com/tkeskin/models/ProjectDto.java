package tr.com.tkeskin.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProjectDto {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String downloadPath;
}