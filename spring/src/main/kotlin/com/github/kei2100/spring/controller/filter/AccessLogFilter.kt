package com.github.kei2100.spring.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter

class AccessLogFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(AccessLogFilter::class.java)

    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain,
    ) {
        val start = System.currentTimeMillis()
        var status = 0
        try {
            chain.doFilter(req, res)
            status = res.status
        } catch (e: Exception) {
            status = 500
            log.error("error occurred", e)
            throw e
        } finally {
            log
                .atInfo()
                .addKeyValue("method", req.method)
                .addKeyValue("path", req.requestURI + (req.queryString?.let { "?$it" } ?: ""))
                .addKeyValue("status", status)
                .addKeyValue("duration_ms", System.currentTimeMillis() - start)
                .log("request processed")
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI.startsWith("/actuator")
    }
}
