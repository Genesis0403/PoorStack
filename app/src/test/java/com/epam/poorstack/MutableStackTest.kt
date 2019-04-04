package com.epam.poorstack

import com.epam.stack.MutableStack
import org.junit.Test
import org.junit.Assert.assertEquals

class MutableStackTest {

    @Test
    fun `whenGivenStack pushElements thenPop`() {
        val stack: MutableStack<String> = MutableStack()
        stack.apply {
            add("hello")
            add("world")
        }
        assertEquals("world", stack.pop())
        assertEquals("hello", stack.pop())
    }

    @Test
    fun `whenGivenStack pushElements thenPeek`() {
        val stack: MutableStack<String> = MutableStack()
        stack.apply {
            add("hello")
            add("world")
        }
        assertEquals("world", stack.peek())
    }
}