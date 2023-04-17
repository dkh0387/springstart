package de.dkh.kotlindemobankapi.service

import de.dkh.kotlindemobankapi.entity.Bank
import de.dkh.kotlindemobankapi.repository.BankRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(private val bankRepository: BankRepository) {

    fun saveBank(bank: Bank) {
        bankRepository.save(bank)
    }

    fun findBanks(): List<Bank> = bankRepository.findBanks()

}