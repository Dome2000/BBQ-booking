package com.example.haruto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.haruto.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    private  lateinit var bindingInsert: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingInsert = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingInsert.root)
        this.setTitle("ลงทะเบียน");

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun btn_reg(view: View) {
        var selectRadio: Int = bindingInsert.radioGroup.checkedRadioButtonId
        var radioChecked: RadioButton = findViewById(selectRadio)
        val api: harutoAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(harutoAPI::class.java)
        api.insertCus(
            bindingInsert.usernameEDT.text.toString(),
            bindingInsert.passwordEDT.text.toString(),
            bindingInsert.nameEDT.text.toString(),
            radioChecked.text.toString(),
            bindingInsert.telEDT.text.toString(),
            bindingInsert.emailEDT.text.toString(),
            bindingInsert.addressEDT.text.toString()
        ).enqueue(object : Callback<Customer> {
            override fun onResponse(
                call: Call<Customer>,
                response: retrofit2.Response<Customer>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}