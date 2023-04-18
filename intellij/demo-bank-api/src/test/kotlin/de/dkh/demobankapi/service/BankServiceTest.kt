package de.dkh.demobankapi.service

import de.dkh.demobankapi.repository.BankRepository
import de.dkh.kotlindemobankapi.entity.Bank
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


/**
 * Test example with mocking and stubbing.
 */
@SpringBootTest
class BankServiceTest {
    /**
     * NOTE: {@code relaxed = true} meaning: if we call a method on mock it returns automatically some default value,
     * so no stubbing needed!
     */
    //private val bankRepository: BankRepository = mockk(relaxed = true)
    private val bankRepository: BankRepository = mockk()
    private val bankService = BankService(bankRepository)

    @BeforeEach
    fun setUp() {
        /**
         * given, that if the mock is being called, that we control the behavior
         * Either this stubbing or {@code relaxed = true} above.
         */

        every { bankRepository.findBanks() }.returns(getBankList())
    }

    @Test
    fun `should provide a list of banks`() {
        // given


        // when
        val banks = bankService.findBanks()

        // then
        assertTrue(banks.isNotEmpty())
    }

    @Test
    fun `should all have a proper account number`() {
        // given

        // when
        val banks = bankService.findBanks()

        // then
        assertTrue(banks.all { it.accountNumber.isNotBlank() })

            //.allMatch { it.accountNumber.isNotBlank() })

    }

    @Test
    fun `should call its repository and retrieve banks`() {

        // when
        bankService.findBanks()

        // then verify, that repository is being called
        verify(exactly = 1) { bankRepository.findBanks() }

    }

    private fun getBankList(): List<Bank> = listOf(Bank(1, "Testbank", "23Fr", 34.5, 12))
}