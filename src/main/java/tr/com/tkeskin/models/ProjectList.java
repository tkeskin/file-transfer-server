package tr.com.tkeskin.models;

import lombok.Data;

import java.util.List;

@Data
public class ProjectList {

    private List<ProjectDto> projectList;
}