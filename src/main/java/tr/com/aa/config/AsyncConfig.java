package tr.com.aa.config;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

  @Value("${aa.upload.thread}")
  public String UPLOAD_POOL_SIZE;
  @Value("${aa.download.thread}")
  public String DOWNLOAD_POOL_SIZE;

  @Bean(name = "downloadTaskExecutor")
  public Executor downloadTaskExecutor() {

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(Integer.parseInt(DOWNLOAD_POOL_SIZE));
    executor.setMaxPoolSize(Integer.parseInt(DOWNLOAD_POOL_SIZE));
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("download-");
    executor.initialize();
    return executor;
  }

  @Bean(name = "uploadTaskExecutor")
  public Executor uploadTaskExecutor() {

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(Integer.parseInt(UPLOAD_POOL_SIZE));
    executor.setMaxPoolSize(Integer.parseInt(UPLOAD_POOL_SIZE));
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("upload-");
    executor.initialize();
    return executor;
  }
}