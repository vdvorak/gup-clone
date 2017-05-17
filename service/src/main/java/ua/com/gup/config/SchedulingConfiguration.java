package ua.com.gup.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        // use the Spring ThreadPoolTaskScheduler
        ThreadPoolTaskScheduler scheduledExecutorService = new ThreadPoolTaskScheduler();
        // always set the pool size
        scheduledExecutorService.setPoolSize(10);
        // for logging add a threadNamePrefix
        scheduledExecutorService.setThreadNamePrefix("ServiceTaskScheduler-");
        // do not wait for completion of the task
        scheduledExecutorService.setWaitForTasksToCompleteOnShutdown(false);

        return scheduledExecutorService;
    }

}
