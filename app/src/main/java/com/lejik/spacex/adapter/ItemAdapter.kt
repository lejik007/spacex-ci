package com.lejik.spacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lejik.spacex.R
import com.lejik.spacex.model.ResData

class ItemAdapter(
    private val context: Context,
    private val dataset: List<ResData>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.textShow1)
        val textView2: TextView = view.findViewById(R.id.textShow2)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        var adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_items, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView1.text = context.resources.getString(item.stringResourceID)
        holder.textView2.text = context.resources.getString(item.stringResourceID)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}