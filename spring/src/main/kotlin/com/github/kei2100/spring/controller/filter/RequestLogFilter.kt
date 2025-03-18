package com.github.kei2100.spring.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.system.measureTimeMillis

class RequestLogFilter : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(RequestLogFilter::class.java)

    private val contexts = mapOf(
        "request_id" to { RequestContext.requestID },
    )

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        before()
        val durationMillis = measureTimeMillis {
            chain.doFilter(req, res)
        }
        after(req, res, durationMillis)
    }

    private fun before() {
        contexts.forEach { (k, v) ->
            MDC.put(k, v())
        }
    }

    private fun after(req: HttpServletRequest, res: HttpServletResponse, durationMillis: Long) {
        log.atInfo()
            .addKeyValue("method", req.method)
            .addKeyValue("path", req.requestURI + (req.queryString?.let { "?$it" } ?: ""))
            .addKeyValue("status", res.status)
            .addKeyValue("duration_ms", durationMillis)
            .log("request processed")

        contexts.forEach { (k) -> MDC.remove(k) }
    }
}