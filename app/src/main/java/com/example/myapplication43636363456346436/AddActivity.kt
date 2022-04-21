package com.example.myapplication43636363456346436

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication43636363456346436.db.Constant
import com.example.myapplication43636363456346436.db.DbManager

class AddActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etText: EditText

    var id=0
    var status = false
    private val dbManager = DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        etTitle = findViewById(R.id.etTitle)
        etText = findViewById(R.id.etText)
        GetIntent()

    }

    override fun onResume() {
        super.onResume()
        dbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

    fun onClickSave(view: View) {
        val myTitle = etTitle.text.toString()
        val myText = etText.text.toString()

        if (myTitle != "" && myText != ""){
            if(status){
                dbManager.updateDb(myTitle, myText, id)
            } else {
                dbManager.insertToDb(myTitle, myText)
            }
            finish()
        }
    }

    fun GetIntent() {
        val i = intent
        if(i!=null){
            if(i.getStringExtra(Constant.TITLE_KEY) != "null"){
                etTitle.setText(i.getStringExtra(Constant.TITLE_KEY))
                etText.setText(i.getStringExtra(Constant.TEXT_KEY))
                status = true
                id = i.getIntExtra(Constant.ID_KEY, 0)
            }
        }
    }


}