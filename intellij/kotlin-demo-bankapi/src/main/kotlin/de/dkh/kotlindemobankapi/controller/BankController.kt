package de.dkh.kotlindemobankapi.controller

import de.dkh.kotlindemobankapi.entity.Bank
import de.dkh.kotlindemobankapi.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BankController(private val bankService: BankService) {

    @GetMapping("/")
    fun index(): String = "Hello world!"

    @GetMapping("/banks")
    fun showBanks(): List<Bank> = bankService.findBanks()

    @PostMapping("/post")
    fun saveBank(@RequestBody bank: Bank) {

        try {
            bankService.saveBank(bank)
        } catch (e: Exception) {
            println(e.localizedMessage)
        }

    }
}