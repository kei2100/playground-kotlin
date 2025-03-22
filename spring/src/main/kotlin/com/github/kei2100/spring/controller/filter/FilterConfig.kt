package com.github.kei2100.spring.controller.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.filter.ForwardedHeaderFilter

@Configuration
class FilterConfig {
    @Bean
    fun firstFilter() = FirstFilter()

    @Bean
    fun secondFilter() = SecondFilter()

    @Bean
    fun forwardedHeaderFilter() = ForwardedHeaderFilter()

    @Bean
    fun correlationIdFilter() = CorrelationIdFilter()

    @Bean
    fun accessLogFilter() = AccessLogFilter()
}

// @Component で指定したフィルターが優先される
@Component
class PrioritizedFilter : Filter {
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        (res as? HttpServletResponse)?.let {
            it.addHeader("X-Filter-Order", "PrioritizedFilter")
        }
        chain.doFilter(req, res)
    }
}

// @Bean で指定した順番に適用される
// ちなみに @WebMvcTest では @Bean で指定したリソースは登録されない状態でテストが走るので、
// @SpringBootTest でテストする必要がある
class FirstFilter : Filter {
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        (res as? HttpServletResponse)?.let {
            it.addHeader("X-Filter-Order", "FirstFilter")
        }
        chain.doFilter(req, res)
    }
}

// @Bean で指定した順番に適用される
// ちなみに @WebMvcTest では @Bean で指定したリソースは登録されない状態でテストが走るので、
// @SpringBootTest でテストする必要がある
class SecondFilter : Filter {
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        (res as? HttpServletResponse)?.let {
            it.addHeader("X-Filter-Order", "SecondFilter")
        }
        chain.doFilter(req, res)
    }
}
