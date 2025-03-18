package com.github.kei2100.spring.controller.filter

import jakarta.annotation.Resource
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.annotation.RequestScope
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

class RequestIDFilter : OncePerRequestFilter() {
    @Resource
    lateinit var requestID: RequestID

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        requestID.id = req.getHeader("X-Request-ID") ?: UUID.randomUUID().toString()
        res.setHeader("X-Request-ID", requestID.id)
        chain.doFilter(req, res)
    }
}

open class RequestID() {
    lateinit var id: String
        internal set
}

@Configuration
class RequestIDConfig {
    @Bean
    @RequestScope
    fun requestID(): RequestID {
        return RequestID()
    }
}