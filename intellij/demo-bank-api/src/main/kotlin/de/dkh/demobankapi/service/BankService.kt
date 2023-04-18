package de.dkh.demobankapi.service

import de.dkh.demobankapi.repository.BankRepository
import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val bankRepository: BankRepository) {

    fun saveBank(bank: Bank) {
        bankRepository.save(bank)
    }

    fun findBanks(): List<Bank> = bankRepository.findBanks()
    fun findBankById(id: Int): Bank = bankRepository.findById(id).get()
    fun findBankByAccountNumber(accountNumber: String): Bank = bankRepository.findByAccountNumber(accountNumber)


}