package com.github.kei2100.spring.controller

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
class AsyncController(
    private val heavyComputationService: HeavyComputationService
) {
    private val logger = LoggerFactory.getLogger(AsyncController::class.java)

    @GetMapping("/async")
    fun async(): String {
        // @Async な関数を呼び出すと、別スレッドで実行される
        heavyComputationService.heavyComputation().join()
        return "OK"
    }
}


@Service
class HeavyComputationService {
    private val logger = LoggerFactory.getLogger(HeavyComputationService::class.java)

    // NOTE: @Asyc な関数の呼び出し元とは別クラスにしないと別スレッド実行にならないので注意
    @Async
    fun heavyComputation(): CompletableFuture<String> {
        // Simulate a long-running task
        logger.info("Heavy computation started")
        Thread.sleep(5000)
        logger.info("Heavy computation completed")
        return CompletableFuture.completedFuture("Heavy computation completed")
    }
}