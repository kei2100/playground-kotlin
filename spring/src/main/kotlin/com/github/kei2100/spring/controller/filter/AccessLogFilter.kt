package com.github.kei2100.spring.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.system.measureTimeMillis

class AccessLogFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(AccessLogFilter::class.java)

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val duration = measureTimeMillis {
            chain.doFilter(req, res)
        }
        log.atInfo()
            .addKeyValue("method", req.method)
            .addKeyValue("path", req.requestURI + (req.queryString?.let { "?$it" } ?: ""))
            .addKeyValue("status", res.status)
            .addKeyValue("duration_ms", duration)
            .addKeyValue("remote_ip", req.remoteAddr)
            .log("request processed")
    }
}
