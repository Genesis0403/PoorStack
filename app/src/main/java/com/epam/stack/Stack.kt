package com.epam.stack

abstract class AbstractStack<T> : Collection<T> {

    override var size: Int = 0

    override fun contains(element: T) = iterator().asSequence().contains(element)

    override fun containsAll(elements: Collection<T>)
            = iterator().asSequence().all { el -> elements.contains(el)}

    override fun isEmpty() = size == 0

    abstract fun peek(): T?
}

class Stack<T> : AbstractStack<T>() {

    private var top: Node<T>? = null

    class Node<T>(val el: T, val prev: Node<T>?)

    override fun peek(): T? = top?.el

    inner class Itr : Iterator<T> {

        private var cursor = top

        override fun hasNext() = cursor?.prev != null

        override fun next(): T {
            val temp = cursor
            cursor = cursor?.prev
            return temp!!.el
        }
    }

    override fun iterator() = Itr()
}