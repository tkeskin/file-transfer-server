package tr.com.aa.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customCofiguration() {

    return new OpenAPI()
        .components(new Components())
        .info(new Info().title("File Transfer API Docs")
            .description("AA File Transfer Server REST API documentation"));
  }
}
