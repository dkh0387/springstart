package de.dkh.demobankapi.service

import de.dkh.demobankapi.repository.BankRepository
import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class BankService(private val bankRepository: BankRepository) {

    fun saveBank(bank: Bank) {
        val valid = validate(bank)

        if (!valid)
            throw IllegalArgumentException("A bank without name or without account can not be saved!")

        bankRepository.save(bank)
    }

    private fun validate(bank: Bank): Boolean = bank.bankName != null && bank.accountNumber != null


    fun findBanks(): List<Bank> = bankRepository.findBanks()
    fun findBankById(id: Int): Bank = bankRepository.findById(id).get()
    fun findBankByAccountNumber(accountNumber: String): Bank = bankRepository.findByAccountNumber(accountNumber)


}