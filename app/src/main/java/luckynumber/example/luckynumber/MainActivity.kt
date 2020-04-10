package luckynumber.example.luckynumber


import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.util.*


class MainActivity : AppCompatActivity() {

    var datevalue = 1
    var monthvalue =1
    var yearvalue = 2000
    var cal = Calendar.getInstance()

    //var mAdView: AdView? = null
    lateinit var adView : AdView
    private val sharedPrefFile = "lucky_shared_data"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//          mAdView = findViewById(R.id.adView)
//          val adRequest: AdRequest = Builder().build()
//          mAdView.loadAd(adRequest)
        adView = findViewById<View>(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        var button1 = findViewById<Button>(R.id.submitData)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)



        fun set_data()
        {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putInt("day",datevalue)
            editor.putInt("month", monthvalue)
            editor.putInt("year", yearvalue)
            editor.apply()
            editor.commit()
        }



        fun callingFinal()
        {

            var total = datevalue + monthvalue + yearvalue
            Log.d("TAG","message total lucky yearvalue $total  $yearvalue ")

            var temp = total

            while(temp > 10 )
            {

                var lsum =0
                var check = temp
                while( check>0 )
                {
                    var d = check %10
                    lsum += d
                    check /= 10

                }

                temp = lsum

            }

            // var work="test"
            var s: String = temp.toString()
            val intent = Intent(this, Showresult::class.java)
            intent.putExtra("key", s)
            startActivity(intent)


        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                //updateDateInView()

                yearvalue = year
                datevalue = dayOfMonth
                monthvalue = monthOfYear + 1

                print("message year $yearvalue")
                set_data()
                callingFinal()

            }
        }


        button1.setOnClickListener()
        {

            DatePickerDialog(this@MainActivity,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()


        }

    }
}
