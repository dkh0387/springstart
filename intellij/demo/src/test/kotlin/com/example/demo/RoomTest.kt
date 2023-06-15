package com.example.demo


import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RoomTest {

    @Test
    fun `should return the right name`() {
        // given
        val result = Room(name = "A").name

        // when


        // then
        assertTrue(result == "A")

    }

}