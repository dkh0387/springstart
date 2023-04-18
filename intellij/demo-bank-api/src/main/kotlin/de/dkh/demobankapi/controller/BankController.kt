package de.dkh.demobankapi.controller

import de.dkh.demobankapi.service.BankService
import de.dkh.kotlindemobankapi.entity.Bank
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bank")
class BankController(private val bankService: BankService) {

    @GetMapping
    fun index(): String = "Hello world!"

    @GetMapping("/banks")
    fun getBanks(): List<Bank> = bankService.findBanks()

    @PostMapping("/post")
    fun saveBank(@RequestBody bank: Bank) {

        try {
            bankService.saveBank(bank)
        } catch (e: Exception) {
            println(e.localizedMessage)
        }

    }

    @GetMapping("/id/{id}")
    fun getBankById(@PathVariable id: Int): Bank = bankService.findBankById(id)

    @GetMapping("/account/{accountNumber}")
    fun getBankByAccount(@PathVariable accountNumber: String): Bank = bankService.findBankByAccountNumber(accountNumber)

}