package com.github.kei2100.spring.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

private const val X_CORRELATION_ID = "x-correlation-id"

/**
 * CorrelationIdFilter は、リクエストヘッダーから x-correlation-id を取得または生成し、
 * MDCとレスポンスヘッダーに設定します。
 */
class CorrelationIdFilter : OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val correlationId = getOrGenerateCorrelationId(req)
        res.setHeader(X_CORRELATION_ID, correlationId)
        try {
            MDC.put(X_CORRELATION_ID, correlationId)
            chain.doFilter(req, res)
        } finally {
            MDC.remove(X_CORRELATION_ID)
        }
    }

    private fun getOrGenerateCorrelationId(req: HttpServletRequest): String {
        return req.getHeader(X_CORRELATION_ID) ?: UUID.randomUUID().toString()
    }
}