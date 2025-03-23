package com.github.kei2100.spring.controller.filter

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CorrelationIdFilterTest {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @LocalServerPort
    private var port: Int = 0

    @Test
    fun `レスポンスヘッダーに X-Correlation-Id が含まれること`() {
        val url = "http://localhost:$port/"
        val requestEntity = RequestEntity<Any>(HttpHeaders(), HttpMethod.GET, URI(url))
        val response = restTemplate.exchange(requestEntity, String::class.java)
        Assertions.assertThat(response.headers["X-Correlation-Id"]).isNotNull
    }

    @Test
    fun `レスポンスヘッダーにリクエストヘッダーで指定した値の X-Correlation-Id が含まれること`() {
        val url = "http://localhost:$port/"
        val id = java.util.UUID.randomUUID().toString()
        val headers = HttpHeaders().apply {
            set("X-Correlation-Id", id)
        }
        val requestEntity = RequestEntity<Any>(headers, HttpMethod.GET, URI(url))
        val response = restTemplate.exchange(requestEntity, String::class.java)
        Assertions.assertThat(response.headers["X-Correlation-Id"]).contains(id)
    }
}
