package de.dkh.kotlindemobankapi.repository

import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface BankRepository : CrudRepository<Bank, Int> {

    @Query("SELECT * FROM BANKS")
    override fun findAll(): List<Bank>
}