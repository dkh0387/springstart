package de.dkh.consumerdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["de.dkh.consumerdemo.entity"])
class ConsumerDemoApplication

fun main(args: Array<String>) {
    runApplication<ConsumerDemoApplication>(*args)
}
