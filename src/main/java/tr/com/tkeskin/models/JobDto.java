package tr.com.tkeskin.models;

import lombok.Data;

import java.util.UUID;

@Data
public class JobDto {

    private UUID id;
    private UUID createdById;
    private String createdBy;
    private String createdDateTime;
    private Boolean autoStart;
}