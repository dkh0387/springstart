package de.dkh.kotlindemobankapi.repository

import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BankRepository : CrudRepository<Bank, Int> {

    @Query("SELECT * FROM BANKS")
    fun findBanks(): List<Bank>

}