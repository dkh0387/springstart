package com.example.demo

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DoorTest{

    @Test
    fun `isPassable should return true, if no stuff required`(){
        // given
        val result = Door().isPassable()

        // when


        // then
        assertTrue(result)

    }
}