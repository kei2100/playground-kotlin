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
}