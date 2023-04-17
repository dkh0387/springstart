package de.dkh.kotlindemobankapi.repository

import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class BankRepositoryImpl : BankRepository {

    private val banks: List<Bank> = listOf(Bank(1, "Testbank", "23Fr", 34.5, 12))

    /**
     * NOTE: for testing only!
     */
    //@Query("SELECT * FROM BANKS")
    fun getBanks(): List<Bank> = banks


    @Query("SELECT * FROM BANKS")
    override fun findAll(): List<Bank> = findAll()


    override fun <S : Bank> save(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : Bank?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableIterable<Bank> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Bank) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Bank>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<Bank> {
        TODO("Not yet implemented")
    }
}