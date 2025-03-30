package com.github.kei2100.spring

import org.slf4j.MDC
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskDecorator
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@EnableAsync
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Configuration
class AsyncConfiguration {
    @Bean
    fun asyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 2
        executor.maxPoolSize = 10
        executor.queueCapacity = 20
        executor.setThreadNamePrefix("AsyncTask-")
        executor.setTaskDecorator(InheritMDCDecorator())
        executor.initialize()
        return executor
    }

    class InheritMDCDecorator : TaskDecorator {
        override fun decorate(runnable: Runnable): Runnable {
            val contextMap = MDC.getCopyOfContextMap()

            return Runnable {
                // Inherit MDC context from the parent thread
                if (contextMap != null) {
                    MDC.setContextMap(contextMap)
                }
                try {
                    runnable.run()
                } finally {
                    // Clear MDC context after execution
                    MDC.clear()
                }
            }
        }
    }
}
