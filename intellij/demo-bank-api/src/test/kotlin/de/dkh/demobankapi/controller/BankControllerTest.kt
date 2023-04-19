package de.dkh.demobankapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jayway.jsonpath.JsonPath
import de.dkh.demobankapi.repository.BankRepository
import de.dkh.kotlindemobankapi.entity.Bank
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import javax.management.Query
import javax.management.Query.value


private const val BASE_URL = "http://localhost:8080/bank"

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
     * ObjectMapper from jackson to convert JSON-POJO and reversed.
     */
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var bankRepository: BankRepository

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

            val resultActionsDsl: ResultActionsDsl = mockMvc.get("$BASE_URL/banks")

            resultActionsDsl
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].bankName") { value("Testbank") }
                }
        }

    }

    @Nested
    @DisplayName("getBankByAccountNumber()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBankByAccountNumber {

        /**
         * Example of usage parameters in REST URLs!
         */
        @Test
        fun `should return a bank by given account number`() {
            // given
            val accountNumber: String = "23Fr"

            val resultActionsDsl: ResultActionsDsl = mockMvc.get("$BASE_URL/account/$accountNumber")

            resultActionsDsl
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value(accountNumber) }
                }
        }

        /**
         * Example of testing HTTP_STATUS 404 (not found).
         * We need an exception handling in controller (see {@code BankController}), otherwise test remains red.
         */
        @Test
        fun `should return NOT FOUND for not existing given account number`() {
            // given
            val accountNumber: String = "1234"

            val resultActionsDsl: ResultActionsDsl = mockMvc.get("$BASE_URL/account/$accountNumber")

            resultActionsDsl
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("saveBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class SaveBank {

        @Test
        fun `should save a new bank in the database`() {

            val numberOFBanksBeforePOST = bankRepository.findBanks().count()

            val resultActionsDsl = mockMvc.post("$BASE_URL/post") {
                contentType = MediaType.APPLICATION_JSON
                val newBank = Bank("Testbank2", "dkh0387", 45.6, 12)
                content = objectMapper.writeValueAsString(newBank)
            }
            resultActionsDsl
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.bankName") { value("Testbank2") }
                    jsonPath("$.accountNumber") { value("dkh0387") }
                    jsonPath("$.trust") { value(45.6) }
                    jsonPath("$.transactionFee") { value(12) }
                }

            val numberOFBanksAfterPOST = bankRepository.findBanks().count()

            assert(numberOFBanksAfterPOST == numberOFBanksBeforePOST + 1)
        }

        @Test
        fun `should return BAD REQUEST if a bank without bank name trying to save`() {

            val resultActionsDsl = mockMvc.post("$BASE_URL/post") {
                contentType = MediaType.APPLICATION_JSON
                val newBank = Bank(null, "dkh0387", 45.6, 12)
                content = objectMapper.writeValueAsString(newBank)
            }
            resultActionsDsl
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }

    }

}