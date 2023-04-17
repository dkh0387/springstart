package de.dkh.kotlindemobankapi.controller

import de.dkh.kotlindemobankapi.entity.Bank
import de.dkh.kotlindemobankapi.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BankController(val bankService: BankService) {

    @GetMapping("/")
    fun index(): String = "Hello world!"

    @GetMapping("/testGetBanks")
    fun showBanksForTest(): List<Bank> = bankService.getBanks()

    @GetMapping("/banks")
    fun showBanks(): List<Bank> = bankService.findAll()

    @PostMapping("/post")
    fun saveBank(@RequestBody bank: Bank) {
        bankService.save(bank)
    }
}