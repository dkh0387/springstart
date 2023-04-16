package de.dkh.kotlindemobankapi.service

import de.dkh.kotlindemobankapi.entity.Bank
import de.dkh.kotlindemobankapi.repository.BankRepository
import org.springframework.stereotype.Service

@Service
class BankService(val bankRepository: BankRepository) {

    fun save(bank: Bank) {
        bankRepository.save(bank)
    }

    fun findAll(): List<Bank> = bankRepository.findAll().toList()
}