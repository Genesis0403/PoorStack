package com.epam.poorstack.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.epam.poorstack.R

class StackAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<StackAdapter.StackViewHolder>() {

    class StackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null
        init {
            textView = itemView.findViewById(R.id.element)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): StackViewHolder {
        val textView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_view, viewGroup, false) as TextView
        return StackViewHolder(textView)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        holder.textView?.text = dataSet[position]
    }
}
