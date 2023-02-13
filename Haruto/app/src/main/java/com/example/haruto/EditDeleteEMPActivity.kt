package com.example.haruto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.haruto.databinding.ActivityEditDeleteBinding
import com.example.haruto.databinding.ActivityEditDeleteEmpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDeleteEMPActivity : AppCompatActivity() {
    private  lateinit var  binding : ActivityEditDeleteEmpBinding
    val createCliend = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("แก้ไขการจอง");
        binding = ActivityEditDeleteEmpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var data = intent.extras

        val mID = intent.getStringExtra("mId")
        val mUsername = intent.getStringExtra("mUsername")
        val mDate = intent.getStringExtra("mDate")
        val mTime = intent.getStringExtra("mTime")
        val mCount = intent.getStringExtra("mCount")
        val mStatus = intent.getStringExtra("mStatus")

        binding.edtId2.setText(mID)
        binding.edtId2.isEnabled = false
        binding.edtUser2.setText(mUsername)
        binding.edtUser2.isEnabled = false
        binding.edtTime2.setText(mTime)
        binding.edtTime2.isEnabled = false
        binding.edtDate2.setText(mDate)
        binding.edtDate2.isEnabled = false
        binding.edtCount2.setText(mCount)
        binding.edtStatus2.setText(mStatus)


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    fun save(view: View) {
        var data = intent.extras

        val mEmployee_username = "admin"
        var newData: Data? = data?.getParcelable("Data")
        var total_price: Int = binding.edtCount2.text.toString().toInt()*149
        createCliend.updateReserve2(
            binding.edtId2.text.toString(),
            binding.edtDate2.text.toString(),
            binding.edtTime2.text.toString(),
            total_price,
            binding.edtCount2.text.toString().toInt(),
            mEmployee_username,
            binding.edtStatus2.text.toString().toInt()
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
    fun delete(view: View) {
        var data = intent.extras

        val mEmployee_username = "admin"
        var newData: Data? = data?.getParcelable("Data")
        var total_price: Int = binding.edtCount2.text.toString().toInt()*149
        createCliend.updateReserve2(
            binding.edtId2.text.toString(),
            binding.edtDate2.text.toString(),
            binding.edtTime2.text.toString(),
            total_price,
            binding.edtCount2.text.toString().toInt(),
            mEmployee_username,
            4
        )
            .enqueue(object : Callback<Reserve> {
                override fun onResponse(call: Call<Reserve>, response: Response<Reserve>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Successfully",
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