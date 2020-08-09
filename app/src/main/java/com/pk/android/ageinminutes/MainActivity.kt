package com.pk.android.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            txtSelectedDate.text = selectedDate

           val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
           val theDate = sdf.parse(selectedDate)
           val selectedDateInMinutes = theDate!!.time/60000

           val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
           val currentDateInMinutes = currentDate!!.time/60000

           val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
           txtAgeInMinutes.text = differenceInMinutes.toString()
           txtAgeInDays.text = (differenceInMinutes/1440).toString()
        }
            ,year
            ,month
            ,dayOfMonth)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}
