package com.epam.poorstack

import com.epam.stack.MutableStack
import com.epam.stack.Stack
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class MutableStackTest {

    @Test
    fun `whenGivenStack pushElements thenPop`() {
        val stack: MutableStack<String> = Stack()
        stack.apply {
            add("hello")
            add("world")
        }
        assertEquals("world", stack.pop())
        assertEquals("hello", stack.pop())
    }

    @Test
    fun `whenGivenStack pushElements thenPeek`() {
        val stack: MutableStack<String> = Stack()
        stack.apply {
            add("hello")
            add("world")
        }
        assertEquals("world", stack.peek())
    }

    @Test
    fun `whenGivenStack popElement thenThrowException`() {
        val stack: MutableStack<String> = Stack()
        try {
            stack.pop()
            fail()
        } catch (e: Exception) {
            Assert.assertTrue(true)
        }
    }

    @Test
    fun `whenGivenStack iterateIt checkElements`() {
        val stack: MutableStack<String> = Stack()
        stack.apply {
            add("ne")
            add("rugaite")
            add("za")
            add("!!")
        }
        val list = mutableListOf<String>()
        for (el in stack) {
            list.add(el)
        }
        val expected = arrayOf("!!", "za", "rugaite", "ne")
        assertArrayEquals(expected, list.toTypedArray())
    }
}