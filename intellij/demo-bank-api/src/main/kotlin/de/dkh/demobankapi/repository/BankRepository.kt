package de.dkh.demobankapi.repository

import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface BankRepository : CrudRepository<Bank, Int> {

    @Query("SELECT * FROM BANKS")
    fun findBanks(): List<Bank>

    /**
     * Example of usage method params inside of queries.
     */
    @Query("SELECT * FROM BANKS b WHERE b.ACCOUNT_NUMBER =: account")
    fun findByAccountNumber(@Param("account") accountNumber: String): Bank

    @Query("UPDATE BANKS b SET b.BANK_NAME =: 'newBankName' WHERE b.ACCOUNT_NUMBER =: 'accountNumber'")
    fun updateBankNameByAccountNumber(
        @Param("accountNumber") accountNumber: String,
        @Param("newBankName") newBankName: String
    ): Bank =
        findBanks().first { accountNumber == it.accountNumber }

    /**
     * Alternatively we could use the whole list and filter it per stream.
     */
    /*    fun findByAccountNumber(@Param("account") theAccountNumber: String): Bank {
            findBanks().firstOrNull { theAccountNumber == it.accountNumber }
            ?: throw NoSuchElementException("There is no bank for the account number $theAccountNumber in the list!")
        }*/

}