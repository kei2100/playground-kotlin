package com.github.kei2100.spring.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

var RequestContext.requestID: String
    get() = tl.get()
    private set(value) {
        tl.set(value)
    }

private val tl = ThreadLocal<String>()

class RequestIDFilter : OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        try {
            RequestContext.requestID = req.getHeader("X-Request-ID") ?: UUID.randomUUID().toString()
            res.setHeader("X-Request-ID", RequestContext.requestID)
            chain.doFilter(req, res)
        } finally {
            tl.remove()
        }
    }
}