package luckynumber.example.luckynumber

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Showresult : AppCompatActivity() {

    private val sharedPrefFile = "lucky_shared_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showresult)

        val intent = getIntent()
        val myValue = intent.getStringExtra("key")

       // val recieve:String = intent.getStringExtra("key")
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        fun getdata()
        {
            val savedate = sharedPreferences.getInt("day",0)
            val savemonth = sharedPreferences.getInt("month",0)
            val saveyear = sharedPreferences.getInt("year",0)

            Log.d("TAG" ," lucky share preference luckycookie  $savedate $savemonth $saveyear")
        }


        var setvalue = findViewById<TextView>(R.id.luckytext)
        var str = "$myValue"
        setvalue.setText(str)

        getdata()

        var changeactivity = findViewById<Button>(R.id.checkagain)
        changeactivity.setOnClickListener()
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }







    }
}
