package de.dkh.demobankapi.controller

import de.dkh.demobankapi.service.BankService
import de.dkh.kotlindemobankapi.entity.Bank
import jakarta.websocket.server.PathParam
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bank")
class BankController(private val bankService: BankService) {

    /**
     * Internal handling of NOT_FOUND exceptions by calling a mapping for elements, which do not exist.
     */
    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleNotFound(e: EmptyResultDataAccessException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    /**
     * Internal handling of BAD_REQUEST exceptions by calling a mapping for elements, which do not exist.
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun index(): String = "Hello world!"

    @GetMapping("/banks")
    fun getBanks(): List<Bank> = bankService.findBanks()

    /**
     * Example of POST endpoint.
     * We save a new bank by providing it as a JSON into request body.
     * To make sure the method returns the right HTTP status we explicitly annotate it.
     */
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveBank(@RequestBody bank: Bank): Bank {
        bankService.saveBank(bank)
        return bank
    }

    @GetMapping("/id/{id}")
    fun getBankById(@PathVariable id: Int): Bank = bankService.findBankById(id)

    @GetMapping("/account/{accountNumber}")
    fun getBankByAccount(@PathVariable accountNumber: String): Bank =
        bankService.findBankByAccountNumber(accountNumber)

    /**
     * Example of PATCH endpoint: updating a bank.
     * Difference between PUT and PATCH:
     * PATCH allows updating of partial properties
     * PUT requires the whole object being passed
     */
    @PatchMapping("/update/{id}")
    fun updateBankById(@RequestBody bank: Bank, @PathVariable id: Int): Bank? = bankService.updateBankById(bank, id)

}