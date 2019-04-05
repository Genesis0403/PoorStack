package com.epam.stack

import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException

interface ImmutableStack<T> : Collection<T> {

    override val size: Int

    fun peek(): T?

    override fun contains(element: T): Boolean

    override fun containsAll(elements: Collection<T>): Boolean

    override fun isEmpty(): Boolean

    override fun iterator(): Iterator<T>

}

interface MutableStack<T> : ImmutableStack<T>, MutableCollection<T> {

    fun pop(): T

    override fun add(element: T): Boolean

    override fun addAll(elements: Collection<T>): Boolean

    override fun clear()

    override fun remove(element: T): Boolean

    override fun removeAll(elements: Collection<T>): Boolean

    override fun retainAll(elements: Collection<T>): Boolean
}

class Stack<T> : MutableStack<T>, MutableCollection<T> {

    operator fun get(index: Int): T? {
        if (index < 0 || index > size) throw IndexOutOfBoundsException()
        val ind = size - index
        var element = top
        for (index in size-1 downTo ind) {
            element = element?.prev
        }
        return element?.el
    }

    private var _size = 0

    override val size: Int
        get() { return _size}

    private var top: Node<T>? = null

    class Node<T>(val el: T, var prev: Node<T>?)

    override fun add(element: T): Boolean {
        top = Node(element, top)
        _size++
        return true
    }

    override fun pop(): T {
        if (top == null) throw IllegalArgumentException()
        val prev = top!!.prev
        val el = top!!.el
        top!!.prev = null
        top = prev
        _size--
        return el
    }

    override fun peek(): T? {
        return top?.el
    }

    override fun clear() {
        while (top != null) {
            val prev = top?.prev
            top?.prev = null
            top = prev
        }
        _size = 0
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (el in elements) {
            add(el)
            _size++
        }
        return true
    }

    override fun isEmpty() = size == 0

    override fun contains(element: T) = iterator().asSequence().contains(element)

    override fun containsAll(elements: Collection<T>)
            = iterator().asSequence().all { el -> elements.contains(el)}

    override fun remove(element: T): Boolean {
        throw UnsupportedOperationException()
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    inner class Itr : MutableIterator<T> {

        private var cursor = top

        override fun hasNext() = cursor != null

        override fun next(): T {
            val temp = cursor
            cursor = cursor?.prev
            return temp!!.el
        }

        override fun remove() {
            throw UnsupportedOperationException()
        }
    }

    override fun iterator(): MutableIterator<T> = Itr()
}