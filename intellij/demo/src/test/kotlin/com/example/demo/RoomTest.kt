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
    fun `stuff should return none, if room is empty`() {
        // given
        val result = Room(name = "A", stuff = Stuff.NONE).stuff

        // when


        // then
        assertTrue(result == Stuff.NONE)

    }

    @Test
    fun `stuff should return key, if room contains key`() {
        // given
        val result = Room(name = "A", stuff = Stuff.KEY).stuff

        // when


        // then
        assertTrue(result == Stuff.KEY)

    }

}