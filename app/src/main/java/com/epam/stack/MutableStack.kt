package com.epam.stack

abstract class AbstractMutableStack<T> : AbstractStack<T>(), MutableCollection<T> {

    override fun addAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }


}

class MutableStack<T> : AbstractMutableStack<T>() {

    private var top: Node<T>? = null

    class Node<T>(val el: T, var prev: Node<T>?)

    override fun add(element: T): Boolean {
        val prev = top?.prev
        top = Node(element, prev)
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

    override fun remove(element: T): Boolean {
        if (top == null) return false
        val prev = top!!.prev
        top!!.prev = null
        top = prev
        return true
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

        override fun remove() { //TODO iterator remove impl(later)
            throw UnsupportedOperationException()
        }
    }

    override fun iterator(): MutableIterator<T> = Itr()

}