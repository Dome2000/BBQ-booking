package com.example.haruto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.haruto.databinding.ActivityEditDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDeleteActivity : AppCompatActivity() {
    private  lateinit var  bindingEditDelete : ActivityEditDeleteBinding
    val createCliend = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("แก้ไขการจอง");
        bindingEditDelete = ActivityEditDeleteBinding.inflate(layoutInflater)
        setContentView(bindingEditDelete.root)

        val mID = intent.getStringExtra("mId")
        val mDate = intent.getStringExtra("mDate")
        val mTime = intent.getStringExtra("mTime")
        val mCount = intent.getStringExtra("mCount")

        bindingEditDelete.edtId.setText(mID)
        bindingEditDelete.edtId.isEnabled = false
        bindingEditDelete.edtTime.setText(mTime)
        bindingEditDelete.edtDate.setText(mDate)
        bindingEditDelete.edtCount.setText(mCount)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun btn_date(view: android.view.View) {
        val newDateFragment = DatePickerFragment2()
        newDateFragment.show(supportFragmentManager,"Date Picker")
    }
    fun btn_clock(view: android.view.View) {
        val newTimePickerFragment = TimePickerFragment2()
        newTimePickerFragment.show(supportFragmentManager,"Time Picker")
    }
    fun deleteReserve(view: android.view.View) {
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.apply {
            setTitle("Warning")
            setMessage("คุณต้องการยกเลิกการจอง?")
            setNegativeButton("Yes"){dialog,which ->
                createCliend.deleteReserve(
                    bindingEditDelete.edtId.text.toString()
                )
                    .enqueue(object : Callback<Reserve> {
                        override fun onResponse(call: Call<Reserve>, response: Response<Reserve>) {
                            if(response.isSuccessful){
                                Toast.makeText(applicationContext,"Successful Delete", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(applicationContext, " Delete Failure", Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<Reserve>, t: Throwable) {
                            Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                finish()
            }
            setPositiveButton("No"){dialog,which->dialog.cancel()}
            show()
        }
    }
    fun saveReserve(view: android.view.View) {
        var total_price: Int = bindingEditDelete.edtCount.text.toString().toInt()*149
        createCliend.updateReserve(
            bindingEditDelete.edtId.text.toString(),
            bindingEditDelete.edtDate.text.toString(),
            bindingEditDelete.edtTime.text.toString(),
            total_price,
            bindingEditDelete.edtCount.text.toString().toInt()
        )
            .enqueue(object : Callback<Reserve> {
                override fun onResponse(call: Call<Reserve>, response: Response<Reserve>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Successfully updated",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Update Failure", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Reserve>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}