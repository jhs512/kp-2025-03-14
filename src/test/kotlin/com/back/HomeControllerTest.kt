package com.back

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class HomeControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @DisplayName("GET /buckets")
    fun t1() {
        val resultActions = mvc
            .perform(
                get("/buckets")
            )
            .andDo(print())

        resultActions
            .andExpect {
                status().isOk()
            }
    }
}
