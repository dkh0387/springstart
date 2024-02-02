package de.dkh.demobankapi.service

import de.dkh.demobankapi.repository.BankRepository
import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.*

@Service
class BankService(private val bankRepository: BankRepository) {

    fun saveBank(bank: Bank) {
        val valid = validate(bank)

        if (!valid)
            throw IllegalArgumentException("A bank without name or without account can not be saved!")

        bankRepository.save(bank)
    }

    private fun validate(bank: Bank): Boolean =
        (Objects.nonNull(bank.bankName) && !"".equals(
            bank.bankName,
            ignoreCase = true
        ))
                &&
                (Objects.nonNull(bank.accountNumber) && !"".equals(
                    bank.accountNumber,
                    ignoreCase = true
                ))


    fun findBanks(): List<Bank> = bankRepository.findBanks()
    fun findBankById(id: Int): Bank = bankRepository.findById(id).get()
    fun findBankByAccountNumber(accountNumber: String): Bank = bankRepository.findByAccountNumber(accountNumber)
    fun updateBankById(bank: Bank, id: Int): Bank? {
        val existingBank = findBankById(id)

        if (existingBank == null)
            throw NoSuchElementException("Bank with id $id does not exist and can not be updated!")

        return bankRepository.updateBankById(existingBank, bank, id)
    }

    fun deleteBankById(id: Int): Bank? {
        val existingBank = findBankById(id)

        if (existingBank == null)
            throw NoSuchElementException("Bank with id $id does not exist and can not be deleted!")

        bankRepository.delete(existingBank)

        return existingBank
    }


}