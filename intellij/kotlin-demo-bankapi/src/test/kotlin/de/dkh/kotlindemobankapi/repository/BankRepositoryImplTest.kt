package de.dkh.kotlindemobankapi.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankRepositoryImplTest {

    private val bankRepository = BankRepositoryImpl()

    @Test
    fun `should provide a list of banks`() {
        // given


        // when
        val banks = bankRepository.findAll()

        // then
        assertThat(banks).isNotEmpty
    }

    @Test
    fun `should all have a proper account number`() {
        // given

        // when
        val banks = bankRepository.findAll()

        // then
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }

    }
}