package tr.com.tkeskin.config.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPoint
    extends BasicAuthenticationEntryPoint {

  private static final String REALM_NAME = "AA-FILE-TRANSFER-API";

  @Override
  public void afterPropertiesSet() {

    setRealmName(REALM_NAME);
    super.afterPropertiesSet();
  }
}
