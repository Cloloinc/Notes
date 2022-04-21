package com.example.myapplication43636363456346436.db

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication43636363456346436.AddActivity
import com.example.myapplication43636363456346436.R

class Item(ListMain: ArrayList<ListItem>, contextm: Context) : RecyclerView.Adapter<Item.Holder>() {
    var listArray = ListMain
    var context = contextm

    class Holder(itemView: View, contexth: Context) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var context = contexth
        fun setData(item: ListItem) {
            tvTitle.text = item.title
            itemView.setOnClickListener {

                val intent = Intent(context, AddActivity::class.java).apply {
                        putExtra(Constant.TITLE_KEY, item.title)
                        putExtra(Constant.TEXT_KEY, item.text)
                        putExtra(Constant.ID_KEY, item.id)
                }
                context.startActivity(intent)
            }
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.item, parent, false), context)
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(listArray.get(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItem(listItems: List<ListItem>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }

    fun removeItem(pos:Int, dbmanager: DbManager) {
        dbmanager.removeFromDb(listArray[pos].id.toString())
        listArray.removeAt(pos)
        notifyItemRangeChanged(0,listArray.size)
        notifyItemRemoved(pos)
    }
}