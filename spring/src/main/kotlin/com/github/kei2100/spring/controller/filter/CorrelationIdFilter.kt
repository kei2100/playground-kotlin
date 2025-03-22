package com.github.kei2100.spring.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

private const val CORRELATION_ID_HEADER_KEY = "X-Correlation-Id"
private const val CORRELATION_ID_MDC_KEY = "correlation_id"

/**
 * CorrelationIdFilter は、リクエストヘッダーから x-correlation-id を取得または生成し、
 * MDCとレスポンスヘッダーに設定します。
 */
class CorrelationIdFilter : OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val correlationId = getOrGenerateCorrelationId(req)
        res.setHeader(CORRELATION_ID_HEADER_KEY, correlationId)
        try {
            MDC.put(CORRELATION_ID_MDC_KEY, correlationId)
            chain.doFilter(req, res)
        } finally {
            MDC.remove(CORRELATION_ID_MDC_KEY)
        }
    }

    private fun getOrGenerateCorrelationId(req: HttpServletRequest): String {
        return req.getHeader(CORRELATION_ID_HEADER_KEY) ?: UUID.randomUUID().toString()
    }
}