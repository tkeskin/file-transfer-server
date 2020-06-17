package tr.com.tkeskin.models;

import lombok.Data;

import java.util.UUID;

@Data
public class FtpServerDestination {

    private UUID id;
    private String name;
}