package tr.com.tkeskin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import tr.com.tkeskin.util.Const;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "downloadTaskExecutor")
    public Executor downloadTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Const.DOWNLOAD_POOL_SIZE);
        executor.setMaxPoolSize(Const.DOWNLOAD_POOL_SIZE);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("download-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "uploadTaskExecutor")
    public Executor uploadTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Const.DOWNLOAD_POOL_SIZE);
        executor.setMaxPoolSize(Const.DOWNLOAD_POOL_SIZE);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("upload-");
        executor.initialize();
        return executor;
    }
}