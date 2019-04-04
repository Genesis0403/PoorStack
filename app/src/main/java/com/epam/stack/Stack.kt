package com.epam.stack

import java.lang.IllegalArgumentException

interface ImmutableStack<T> : Collection<T> {
    override var size: Int

    override fun contains(element: T): Boolean

    override fun containsAll(elements: Collection<T>): Boolean

    override fun isEmpty(): Boolean

    override fun iterator(): Iterator<T>
}

interface MutableStack<T> : ImmutableStack<T>, MutableCollection<T> {

    fun pop(): T

    fun peek(): T?

    override fun add(element: T): Boolean

    override fun addAll(elements: Collection<T>): Boolean

    override fun clear()

    override fun remove(element: T): Boolean

    override fun removeAll(elements: Collection<T>): Boolean

    override fun retainAll(elements: Collection<T>): Boolean
}

class Stack<T> : MutableStack<T>, MutableCollection<T> {

    override var size: Int = 0

    private var top: Node<T>? = null

    class Node<T>(val el: T, var prev: Node<T>?)

    override fun add(element: T): Boolean {
        top = Node(element, top)
        return true
    }

    override fun pop(): T {
        if (top == null) throw IllegalArgumentException()
        val prev = top!!.prev
        val el = top!!.el
        top!!.prev = null
        top = prev
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
        size = 0
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (el in elements) {
            add(el)
        }
        return true
    }

    inner class Itr : MutableIterator<T> {

        private var cursor = top

        override fun hasNext() = cursor?.prev != null

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

    override fun contains(element: T) = iterator().asSequence().contains(element)

    override fun containsAll(elements: Collection<T>)
            = iterator().asSequence().all { el -> elements.contains(el)}

    override fun isEmpty() = size == 0

    override fun remove(element: T): Boolean {
        throw UnsupportedOperationException()
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    //TODO implement toStack()
}