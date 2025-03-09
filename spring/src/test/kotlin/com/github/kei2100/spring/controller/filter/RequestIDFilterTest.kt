package com.github.kei2100.spring.controller.filter

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.util.*

@WebMvcTest
class RequestIDFilterTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `レスポンスヘッダーにX-Request-IDが含まれること`() {
        mockMvc.get("/")
            .andExpect {
                header { exists("X-Request-ID") }  // ヘッダーが存在することを確認
            }
    }

    @Test
    fun `レスポンスヘッダーにリクエストヘッダーで指定した値のX-Request-IDが含まれること`() {
        val rid = UUID.randomUUID().toString()
        mockMvc.get("/") {
            header("X-Request-ID", rid)
        }
            .andExpect {
                header { string("X-Request-ID", rid) }
            }
    }
}
