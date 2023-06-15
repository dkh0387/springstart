package com.example.demo


import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RoomTest {

    @Test
    fun `name should return the right name`() {
        // given
        val result = Room(name = "A", stuff = Stuff.NONE).name

        // when


        // then
        assertTrue(result == "A")

    }

    @Test
    fun `stuff should return key, if room contains key`() {

        Stuff.values().forEach {
            val result = Room(name = "A", stuff = it).stuff
            assertTrue(result == it)
        }

    }

}