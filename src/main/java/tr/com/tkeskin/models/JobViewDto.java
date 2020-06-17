package tr.com.tkeskin.models;

import lombok.Data;

import java.util.UUID;

@Data
public class JobViewDto {

    private UUID id;
    private UUID createdById;
    private String createdBy;
    private String createdDateTime;
    private Integer jobStatus;
}