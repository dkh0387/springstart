package de.dkh.kotlindemobankapi.service

import de.dkh.kotlindemobankapi.entity.Bank
import de.dkh.kotlindemobankapi.repository.BankRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class BankService(private val bankRepository: BankRepository) {

    fun saveBank(bank: Bank) {
        bankRepository.save(bank)
    }

    fun findBanks(): List<Bank> = bankRepository.findBanks()
    fun findBankById(id: Int): Bank = bankRepository.findById(id).get()
    fun findBankByAccountNumber(accountNumber: String): Bank = bankRepository.findByAccountNumber(accountNumber)


}