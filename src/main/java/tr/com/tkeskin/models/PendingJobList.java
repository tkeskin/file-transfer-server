package tr.com.tkeskin.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PendingJobList {

    private List<JobDto> pendingJobList;
}