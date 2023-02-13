package com.example.haruto

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment2: DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity,2,this,hour,minute,true)
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val tv: TextView? = activity?.findViewById(R.id.edtTime)
        val minuteNew:String=if(minute<10)"0${minute.toString()}" else minute.toString()
        if (tv != null) {
            tv.text = "$hourOfDay:$minuteNew"
        }
    }
    override fun onCancel(dialog: DialogInterface) {
        Toast.makeText(activity,"Please select a Time.", Toast.LENGTH_SHORT).show()
        super.onCancel(dialog)
    }
}