package com.example.haruto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.haruto.databinding.ActivityRepasswordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepasswordActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRepasswordBinding
    val createCliend = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepasswordBinding.inflate(layoutInflater)
        this.setTitle("เปลี่ยนรหัสผ่าน");
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var data = intent.extras
        var newData: Data? = data?.getParcelable<Data>("Data")

        val userTxt:EditText = findViewById(R.id.edtUser)
        binding.edtUser.isEnabled = false
        userTxt.setText(newData?.username)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun savePW(view: View) {
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.apply {
            setTitle("Warning")
            setMessage("Do you want to update")
            setNegativeButton("Yes"){dialog,which ->
                createCliend.updateCustomerPW(
                    binding.edtUser.text.toString(),
                    binding.oldPassword.text.toString(),
                    binding.rePassword.text.toString()
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