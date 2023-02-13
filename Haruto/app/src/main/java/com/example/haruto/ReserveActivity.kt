package com.example.haruto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.haruto.databinding.ActivityReserveBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReserveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReserveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReserveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var data = intent.extras
        var newData: Data? = data?.getParcelable("Data")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun btn_date(view: android.view.View) {
        val newDateFragment = DatePickerFragment()
        newDateFragment.show(supportFragmentManager,"Date Picker")
    }

    fun btn_clock(view: android.view.View) {
        val newTimePickerFragment = TimePickerFragment()
        newTimePickerFragment.show(supportFragmentManager,"Time Picker")
    }

    fun btn_reserve(view: android.view.View) {
        var data = intent.extras
        var newData: Data? = data?.getParcelable("Data")
        var total_price: Int = binding.num.text.toString().toInt()*149
        var employee_username: String ="null"
        val api: harutoAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(harutoAPI::class.java)
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.apply {
            setTitle("รายละเอียด")
            setMessage(
                "วันที่ : ${ binding.date.text.toString()}\n" +
                        "เวลา : ${ binding.minute.text.toString()} น.\n" +
                        "จำนวน : ${binding.num.text.toString().toInt()} คน\n" +
                        "รวม : ${total_price} บาท\n"
            )
            setNegativeButton("Yes"){dialog,which ->
                api.insertReserve(
                    binding.date.text.toString(),
                    binding.minute.text.toString(),
                    total_price,
                    binding.num.text.toString().toInt(),
                    newData?.username.toString(),
                    employee_username,
                    1

                ).enqueue(object : Callback<Reserve> {
                    override fun onResponse(
                        call: Call<Reserve>,
                        response: retrofit2.Response<Reserve>
                    ) {
                        if (response.isSuccessful()) {
                            Toast.makeText(applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Reserve>, t: Throwable) {
                        Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                    }
                })
                finish()
            }
            setPositiveButton("No"){dialog,which->dialog.cancel()}
            show()
        }
   }

}