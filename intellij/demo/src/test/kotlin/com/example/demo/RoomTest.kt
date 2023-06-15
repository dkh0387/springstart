package com.example.demo


import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RoomTest {

    @Test
    fun `name should return the right name`() {
        // given
        val result = Room(name = "A", stuff = Stuff.NONE, location = Location.OUTSIDE).name

        // when


        // then
        assertTrue(result == "A")

    }

    @Test
    fun `stuff should return the right item, if room contains the item`() {

        Stuff.values().forEach {
            val result = Room(name = "A", stuff = it, location = Location.INSIDE).stuff
            assertTrue(result == it)
        }

    }

    @Test
    fun `location should return inside, outside, if the room is inside or outside of the labyrinth`() {
        // given
        Location.values().forEach {
            val result = Room(name = "A", stuff = Stuff.NONE, location = it).location
            assertTrue(result == it)
        }

        // when


        // then


    }

}