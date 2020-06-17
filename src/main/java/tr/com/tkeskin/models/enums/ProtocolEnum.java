package tr.com.tkeskin.models.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ProtocolEnum {

  FTP(1, "FTP"),
  SFTP(2, "SFTP");

  private final short value;
  private final String text;

  ProtocolEnum(int value, String text) {

    this.value = (short) value;
    this.text = text;
  }

  public short getValue() {

    return value;
  }

  public String getText() {

    return text;
  }

  /**
   * Gets the short value and returns the equivalent ProtocolEnum.
   *
   * @param value value of enum
   * @return Optional of ProtocolEnum
   */
  public static Optional<ProtocolEnum> resolve(short value) {

    Optional<ProtocolEnum> val = Arrays.stream(ProtocolEnum.values())
        .filter(ProtocolEnum -> ProtocolEnum.getValue() == value)
        .findFirst();

    return val;
  }

  /**
   * Gets the string value and returns the equivalent ProtocolEnum.
   *
   * @param value value of enum
   * @return Optional of ProtocolEnum
   */
  public static Optional<ProtocolEnum> resolve(String value) {

    try {
      short val = Short.parseShort(value);
      return resolve(val);
    } catch (NumberFormatException ex) {
      return Optional.empty();
    }
  }
}
