package tr.com.aa;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.core.env.Environment;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableCircuitBreaker
@EnableJpaAuditing
@EnableHystrixDashboard
@EnableScheduling
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@SpringBootApplication
public class AaFileTransferApplication implements CommandLineRunner {

  @Autowired
  private Environment environment;

  public static void main(String[] args) {

    SpringApplication.run(AaFileTransferApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    String[] activeProfiles = environment.getActiveProfiles();

    log.info("--- Active profiles: " + Arrays.stream(activeProfiles)
        .map(s -> "(" + s + ")")
        .reduce("", String::concat));

    final String prodProfile = "prod";
    if (Arrays.stream(activeProfiles).anyMatch(
        env -> env.equalsIgnoreCase(prodProfile))) {

      System.setProperty("spring.profiles.active", prodProfile);
      if (System.getProperty("spring.profiles.active") == null
          || !System.getProperty("spring.profiles.active").equals(prodProfile)) {
        String exMessage = "--- Uygulama profi̇li̇ 'prod' olarak set EDİLEMEDİ!!!";
        log.error(exMessage);
        throw new RuntimeException(exMessage);
      }
      log.info("--- System property of spring.profiles.active is set to prod");
    }
  }
}