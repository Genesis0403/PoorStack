package com.epam.stack

import java.lang.IllegalArgumentException

abstract class AbstractMutableStack<T> : AbstractStack<T>(), MutableCollection<T> {

    override fun addAll(elements: Collection<T>): Boolean {
        for (el in elements) {
            add(el)
        }
        return true
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun remove(element: T): Boolean {
        throw UnsupportedOperationException()
    }

    abstract fun pop(): T
}

class MutableStack<T> : AbstractMutableStack<T>() {

    private var top: Node<T>? = null

    class Node<T>(val el: T, var prev: Node<T>?)

    override fun add(element: T): Boolean {
        top = Node(element, top)
        return true
    }

    override fun clear() {
        while (top != null) {
            val prev = top?.prev
            top?.prev = null
            top = prev
        }
        size = 0
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

    inner class Itr : MutableIterator<T> {

        private var cursor = top

        override fun hasNext() = cursor?.prev != null

        override fun next(): T {
            val temp = cursor
            cursor = cursor?.prev
            return temp!!.el
        }

        override fun remove() {
            throw java.lang.UnsupportedOperationException();
        }
    }

    override fun iterator(): MutableIterator<T> = Itr()

    //TODO implement toStack()
}