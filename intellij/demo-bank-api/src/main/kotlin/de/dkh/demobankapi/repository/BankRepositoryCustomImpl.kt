package de.dkh.demobankapi.repository

import de.dkh.kotlindemobankapi.entity.Bank
import java.util.*

/**
 * The implementation of a custom interface containing DAO methods,
 * which are not for being interpreted as object properties!
 */
class BankRepositoryCustomImpl : BankRepositoryCustom {
    /**
     * The process by updating an object ist:
     * 1. load the original object using provided id
     * 2. validate the properties from new provided object
     * 3. set all properties on original object and save it
     * 4. return saved object
     */
    override fun updateBankById(existingBank: Bank, bank: Bank, bankId: Int): Bank? {

        if (Objects.nonNull(bank.bankName) && !"".equals(
                bank.bankName,
                ignoreCase = true
            )
        ) {
            existingBank.bankName = bank.bankName
        }
        if (Objects.nonNull(bank.accountNumber) && !"".equals(
                bank.accountNumber,
                ignoreCase = true
            )
        ) {
            existingBank.accountNumber = bank.accountNumber
        }
        if (Objects.nonNull(bank.trust) && bank.trust!! >= 0) {
            existingBank.trust = bank.trust
        }
        if (Objects.nonNull(bank.transactionFee) && bank.transactionFee!! >= 0) {
            existingBank.transactionFee = bank.transactionFee
        }
        return existingBank
    }
}