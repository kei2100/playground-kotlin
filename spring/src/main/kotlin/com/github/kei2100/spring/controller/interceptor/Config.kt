package com.github.kei2100.spring.controller.interceptor

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class Config(private val myAsyncHandlerInterceptor: MyAsyncHandlerInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(myAsyncHandlerInterceptor)
    }
}