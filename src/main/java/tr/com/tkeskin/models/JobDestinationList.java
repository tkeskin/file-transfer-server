package tr.com.tkeskin.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDestinationList {

    private List<JobDestinationViewDto> jobDestinationViewList;

    private List<JobDestinationDto> jobDestinationList;
}