package de.dkh.kotlindemobankapi.entity

import lombok.EqualsAndHashCode
import lombok.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * Example of Java like style of writing classes in Kotlin
 */
//@Table("BANKS")
@ToString
@EqualsAndHashCode
class BankJavaStyle {
    @Id
    private val id: Int?
    private val bankName: String
        //Getter in Kotlin: it's going to be converted in Java byte code like getBankName()
        // NOTE we do not need this one, because Getter are automatically generated for all `val`
        get() = field

    private val accountNumber: String

    private val trust: Double

    private val transactionFee: Int

    /**
     * This field is NOT final, so we can create Setter AND Getter.
     */
    private var blz: String
        get() = blz
        set(value) {
            this.blz = value
        }

    constructor(id: Int?, bankName: String, accountNumber: String, trust: Double, transactionFee: Int) {
        this.id = id
        this.bankName = bankName
        this.accountNumber = accountNumber
        this.trust = trust
        this.transactionFee = transactionFee
    }
}
