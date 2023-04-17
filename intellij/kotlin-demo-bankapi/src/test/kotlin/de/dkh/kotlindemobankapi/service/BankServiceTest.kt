package de.dkh.kotlindemobankapi.service

import de.dkh.kotlindemobankapi.repository.BankRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

/**
 * Test example with mocking and stubbing.
 */
class BankServiceTest {
    /**
     * NOTE: {@code relaxed = true} meaning: if we call a method on mock it returns automatically some default value,
     * so no stubbing needed!
     */
    private val bankRepository: BankRepositoryImpl = mockk(relaxed = true)
    private val bankService = BankService(bankRepository)

    @Test
    fun `should call its repository and retrieve banks`() {
        /**
         * given, that if the mock is being called, that we control the behavior
         * Either this stubbing or {@code relaxed = true} above.
         */

        //every { bankRepository.getBanks() }.returns(emptyList())

        // when
        bankService.getBanks()

        // then verify, that repository is being called
        verify(exactly = 1) { bankRepository.getBanks() }

    }

}