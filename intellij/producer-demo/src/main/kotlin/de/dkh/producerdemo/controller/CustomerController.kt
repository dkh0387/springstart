package de.dkh.producerdemo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {

    @GetMapping("/")
    fun showIndex(): String = "Hello World!"
}