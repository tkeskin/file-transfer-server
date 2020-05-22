package tr.com.aa.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

  private final int POOL_SIZE = 5;

  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(POOL_SIZE);
    executor.setMaxPoolSize(POOL_SIZE);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("file-thread-");
    executor.initialize();
    return executor;
  }
}