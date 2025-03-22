package com.github.kei2100.spring.controller.filter

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.util.*

@WebMvcTest
class CorrelationIdFilterTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `レスポンスヘッダーに X-Correlation-Id が含まれること`() {
        mockMvc.get("/")
            .andExpect {
                header { exists("X-Correlation-Id") }  // ヘッダーが存在することを確認
            }
    }

    @Test
    fun `レスポンスヘッダーにリクエストヘッダーで指定した値の X-Correlation-Id が含まれること`() {
        val rid = UUID.randomUUID().toString()
        mockMvc.get("/") {
            header("X-Correlation-Id", rid)
        }
            .andExpect {
                header { string("X-Correlation-Id", rid) }
            }
    }
}
