package com.github.kei2100.spring.controller.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.AsyncHandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class MyAsyncHandlerInterceptor : AsyncHandlerInterceptor {
    private val logger = LoggerFactory.getLogger(MyAsyncHandlerInterceptor::class.java)

    override fun preHandle(req: HttpServletRequest, res: HttpServletResponse, handler: Any): Boolean {
        logger.info("preHandle")
        return true
    }

    override fun postHandle(
        req: HttpServletRequest,
        res: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        logger.info("postHandle")
    }

    override fun afterCompletion(req: HttpServletRequest, res: HttpServletResponse, handler: Any, ex: Exception?) {
        logger.info("afterCompletion")
    }

    // Controller 関数が直接 CompletableFuture などを返したりする場合、この関数が呼ばれる。
    // CompletableFuture が完了したあと、preHandle, postHandle, afterCompletion が呼ばれる。
    //
    // スレッドのイメージ
    // 1. receive request (thread-1)
    // 2. preHandle (thread-1)
    // 3. async task started (async-task-1)
    // 4. afterConcurrentHandlingStarted (thread-1)
    // 5. async task ended (async-task-1)
    // 6. preHandle (thread-2) // 別スレッド
    // 7. postHandle (thread-2) // 別スレッド
    // 8. afterCompletion (thread-2) // 別スレッド
    override fun afterConcurrentHandlingStarted(req: HttpServletRequest, res: HttpServletResponse, handler: Any) {
        logger.info("afterConcurrentHandlingStarted")
    }
}