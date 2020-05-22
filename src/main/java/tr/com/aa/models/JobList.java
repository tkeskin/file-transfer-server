package tr.com.aa.models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobList {

  private List<JobViewDto> jobList;
}