package de.dkh.demobankapi.repository

import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


/**
 * Basis repository for handling bank data.
 * This one HAS to extend both, {@code CrudRepository} and {@code BankRepositoryCustom},
 * where the last one contains the custom DAO methods.
 * Those custom DAO methods will EXPLICITLY NOT being interpreted as object properties,
 * since they are in a separate interface!
 * See: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations
 */
@Repository
interface BankRepository : CrudRepository<Bank, Int>, BankRepositoryCustom {

    @Query("SELECT * FROM BANKS")
    fun findBanks(): List<Bank>

    //@Query("SELECT * FROM BANKS WHERE ACCOUNT_NUMBER =: account")
    fun findByAccountNumber(@Param("account") accountNumber: String): Bank =
        findBanks().first { it.accountNumber == accountNumber }

}