package com.example.myapplication43636363456346436

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication43636363456346436.db.DbManager
import com.example.myapplication43636363456346436.db.Item


class MainActivity : AppCompatActivity() {
    //private lateinit var etTitle: EditText
    private lateinit var rcView: RecyclerView
    val dbManager = DbManager(this)
    val item = Item(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcView = findViewById(R.id.rcView)
        init()
        //etText = findViewById(R.id.etText)
    }

    override fun onResume() {
        super.onResume()
        dbManager.openDb()
        fillAdapter()
    }

    override fun onDestroy() {
    super.onDestroy()
    dbManager.closeDb()
    }

    fun onClickView(view: View) {
        val i = Intent(this, AddActivity::class.java)
        startActivity(i)
    }

    private fun init(){
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = item
        val swapHelper = Swap()
        swapHelper.attachToRecyclerView(rcView)
    }

    private fun fillAdapter(){
        val list = dbManager.readDbData()
        item.updateItem(list)
    }

    private fun Swap() : ItemTouchHelper{
        return ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                item.removeItem(viewHolder.adapterPosition, dbManager)
            }
        })
    }
}