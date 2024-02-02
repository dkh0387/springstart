package de.dkh.demobankapi.repository

import de.dkh.kotlindemobankapi.entity.Bank
import java.util.*

interface BankRepositoryCustom {

    fun updateBankById(existingBank: Bank, newBank: Bank, bankId: Int): Bank?
}