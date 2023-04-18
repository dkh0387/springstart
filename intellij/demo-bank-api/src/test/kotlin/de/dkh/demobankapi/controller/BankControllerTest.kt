package de.dkh.demobankapi.controller

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import javax.management.Query.value



/**
 * Example of "real" integration test. {@code SpringBootTest}.
 * Doing so, Spring initialize all beans and start up the app.
 * So we can test explicitly the REST endpoint in controller (get all banks as JSON from controller).
 * NOTE: makes sense for integration tests doe to performance issues.
 * We need this {@code AutoConfigureMockMvc} to mock the mockMvc.
 */
@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {
    /**
     * late initialized variable, means it will be initialized by Spring MVC framework later on,
     * we do not need to carry about it.
     */
    @Autowired
    lateinit var mockMvc: MockMvc

    /**
     * Encapsulate test groups with an inner class.
     * It makes the running process more structured.
     */
    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        /**
         * 1. Make the GET request to the endpoint
         * 2. Print the recall
         * 3. Check the status = 200
         * 4. Check the return type is JSON
         * 5. Check the first object in JSON has bankName = "Testbank"
         */
        @Test
        fun `should return all banks`() {

            val resultActionsDsl: ResultActionsDsl = mockMvc.get("http://localhost:8080/bank/banks")

            resultActionsDsl
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].bankName") { value("Testbank") }
                }
        }

    }

    /**
     * Example of usage parameters in REST URLs!
     */
    @Test
    fun `should return a bank by given account number`() {
        // given
        val accountNumber: String = "23Fr"

        val resultActionsDsl: ResultActionsDsl = mockMvc.get("http://localhost:8080/bank/account/$accountNumber")

        resultActionsDsl
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("accountNumber") { value(accountNumber) }
            }
    }
}