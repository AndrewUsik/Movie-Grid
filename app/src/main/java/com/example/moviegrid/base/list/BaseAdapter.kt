package com.example.moviegrid.base.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import no.multimedianordic.roomr.platform.list.ItemModel

abstract class BaseAdapter<T1, out T2, T3 : BaseViewHolder<T2>> :
    RecyclerView.Adapter<T3>() where T2 : View, T2 : ItemModel<T1> {
    private var content: MutableList<T1> = ArrayList()

    override fun onBindViewHolder(holder: T3, position: Int) {
        val view: T2 = holder.view
        view.setData(content[position])
    }

    fun setData(data: List<T1>) {
        content.clear()
        content.addAll(data)
        notifyDataSetChanged()
    }

    fun getModel(position: Int): T1? =
        if (content.size - 1 >= position) content[position] else null

    fun addData(data: T1) {
        content.add(data)
        notifyItemInserted(content.size - 1)
    }

    fun getData(position: Int): T1 = content[position]

    fun insertDataToStart(data: T1) {
        content.add(0, data)
        notifyItemInserted(0)
    }

    fun addData(data: List<T1>) {
        content.addAll(data)
        notifyItemRangeInserted(content.size - 1 - data.size, data.size)
    }

    fun updateData(position: Int, data: T1) {
        content[position] = data
    }

    fun removeData(position: Int) {
        if (itemCount > position) {
            content.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, content.size + 1)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun clearData() {
        content.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = content.size

}