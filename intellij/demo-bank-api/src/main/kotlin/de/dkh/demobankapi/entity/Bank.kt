package de.dkh.kotlindemobankapi.entity

import lombok.Getter
import lombok.Setter
import lombok.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * NOTE:
 * "?" means the field can be null
 *  The constructor in the class body is called "primary constructor".
 *  Any field with {@code val} is {@code final}!
 *  Further all of them are in {@code toString, equals(), hashCode()},
 *  because data classes implement those methods by default
 */
@Table("BANKS")
@ToString
data class Bank(
    @Getter
    @Setter
    var bankName: String?,
    @Getter
    @Setter
    var accountNumber: String?,
    @Getter
    @Setter
    var trust: Double?,
    @Getter
    @Setter
    var transactionFee: Int?
) {
    @Id
    private var id: Int = 0



}
