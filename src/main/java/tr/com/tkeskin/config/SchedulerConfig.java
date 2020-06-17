package tr.com.tkeskin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

  @Value("${aa.download.thread}")
  public String DOWNLOAD_POOL_SIZE;

  @Override
  public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(Integer.parseInt(DOWNLOAD_POOL_SIZE));
    threadPoolTaskScheduler.setThreadNamePrefix("scheduler-");
    threadPoolTaskScheduler.initialize();
    scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
  }
}