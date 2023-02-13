package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.haruto.databinding.ActivityForgotBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotBinding
    val createCliend = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("ลืมรหัสผ่าน");
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun ok(view: View) {
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.apply {
            setTitle("Warning")
            setMessage("Do you want to update")
            setNegativeButton("Yes"){dialog,which ->
                createCliend.forgot(
                    binding.usernameEDT.text.toString(),
                    binding.EmailEDT.text.toString(),
                    binding.NewPasswordeEDT.text.toString()
                )
                    .enqueue(object : Callback<Customer> {
                        override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                            if(response.isSuccessful){
                                Toast.makeText(applicationContext,"Successful", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(applicationContext, "Failure", Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<Customer>, t: Throwable) {
                            Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                finish()
            }
            setPositiveButton("No"){dialog,which->dialog.cancel()}
            show()
        }
    }
}