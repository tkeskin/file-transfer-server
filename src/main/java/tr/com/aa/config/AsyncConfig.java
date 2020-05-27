package tr.com.aa.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import tr.com.aa.util.Const;

@Configuration
@EnableAsync
public class AsyncConfig {

  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(Const.POOL_SIZE);
    executor.setMaxPoolSize(Const.POOL_SIZE);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("up-down-");
    executor.initialize();
    return executor;
  }
}