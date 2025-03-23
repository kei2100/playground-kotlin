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
class FilterConfigTest {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @LocalServerPort
    private var port: Int = 0

    @Test
    fun `X-Filter-Orderの値が期待する順番で設定されていること`() {
        val url = "http://localhost:$port/"
        val requestEntity = RequestEntity<Any>(HttpHeaders(), HttpMethod.GET, URI(url))
        // API 呼び出し
        val response = restTemplate.exchange(requestEntity, String::class.java)
        // レスポンスヘッダーの確認
        Assertions.assertThat(response.headers["X-Filter-Order"])
            .contains("PrioritizedFilter", "FirstFilter", "SecondFilter")
    }
}
