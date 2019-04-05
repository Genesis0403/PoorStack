package com.epam.poorstack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.epam.poorstack.recyclerview.StackAdapter
import com.epam.stack.Stack
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)

        val stack = generateStack()

        viewAdapter = StackAdapter(stack)
        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val pushButton = findViewById<Button>(R.id.push_button_id)
        pushButton.setOnClickListener {
            stack.add(Random.nextInt(100).toString())
            viewAdapter.notifyDataSetChanged()
        }

        val popButton = findViewById<Button>(R.id.pop_button_id)
        popButton.setOnClickListener {
            stack.pop()
            viewAdapter.notifyDataSetChanged()
        }
    }

    private fun generateStack(): Stack<String> {
        val stack: Stack<String> = Stack()
        stack.apply {
            for (alph in 1..20) {
                add(alph.toString())
            }
        }
        return stack
    }
}
