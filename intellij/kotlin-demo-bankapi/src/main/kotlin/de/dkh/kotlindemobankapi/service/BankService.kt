package de.dkh.kotlindemobankapi.service

import de.dkh.kotlindemobankapi.entity.Bank
import de.dkh.kotlindemobankapi.repository.BankRepository
import de.dkh.kotlindemobankapi.repository.BankRepositoryImpl
import org.springframework.stereotype.Service

@Service
class BankService(private val bankRepository: BankRepositoryImpl) {

    fun save(bank: Bank) {
        bankRepository.save(bank)
    }

    fun findAll(): List<Bank> = bankRepository.findAll().toList()

    /**
     * NOTE: for testing only!
     */
    fun getBanks(): List<Bank> = bankRepository.getBanks()
}