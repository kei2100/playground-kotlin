package com.github.kei2100.spring.controller.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import java.util.*

var RequestContext.requestID: String
    get() = tl.get()
    private set(value) {
        tl.set(value)
    }

private val tl = ThreadLocal<String>()

@Component
class RequestIDFilter : Filter {
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        try {
            (req as? HttpServletRequest)?.let {
                // get X-Request-ID from request header
                RequestContext.requestID = it.getHeader("X-Request-ID") ?: UUID.randomUUID().toString()
            }
            (res as? HttpServletResponse)?.let {
                // set X-Request-ID to response header
                it.setHeader("X-Request-ID", RequestContext.requestID)
            }
            chain.doFilter(req, res)
        } finally {
            tl.remove()
        }
    }
}