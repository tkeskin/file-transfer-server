package tr.com.tkeskin.models;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FtpServerDto {

  private UUID id;
  @NotNull(message = "Please provide a name")
  private String name;
  @NotNull(message = "Please provide a protocol")
  private String protocol;
  @NotNull(message = "Please provide a hostAdress")
  private String hostAdress;
  @NotNull(message = "Please provide a port")
  private Integer port;
  @NotNull(message = "Please provide a username")
  private String username;
  @NotNull(message = "Please provide a password")
  private String password;
}