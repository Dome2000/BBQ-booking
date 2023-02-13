package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.haruto.databinding.ActivityCustomerLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerLoginBinding
    private val createClient = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("เข้าสู่ระบบ");
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reg = findViewById(R.id.register) as TextView
        reg.setOnClickListener {
            val regiter_page = Intent(this, RegisterActivity::class.java)
            startActivity(regiter_page)
        }
        val forgot = findViewById(R.id.forgetPassword) as TextView
        forgot.setOnClickListener{
            val  FG = Intent(this, ForgotActivity::class.java)
            startActivity(FG)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    fun login(v: View) {
        createClient.loginCustomer(
            binding.usernameEDTCus.text.toString(),
            binding.passwordEDTCus.text.toString(),
        )
            .enqueue(object : Callback<Customer> {
                override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                    val username = findViewById(R.id.usernameEDT_cus) as EditText
                    val password = findViewById(R.id.passwordEDT_cus) as EditText
                    if (username.text.isEmpty() and password.text.isEmpty() ) {
                        Toast.makeText(this@CustomerLoginActivity, "กรุณาใส่ไอดีและพาสเวิร์ด", Toast.LENGTH_LONG).show()
                    }
                    else if (username.text.isEmpty()){
                        Toast.makeText(this@CustomerLoginActivity, "กรุณากรอกช่อง Username", Toast.LENGTH_LONG).show()
                    }
                    else if (password.text.isEmpty()) {
                        Toast.makeText(this@CustomerLoginActivity, "กรุณากรอกช่อง Password", Toast.LENGTH_LONG).show()
                    }
                    else if (response.isSuccessful) {
                        val intent = Intent(this@CustomerLoginActivity, HomePageActivity::class.java)
                        val username = response.body()?.customer_username.toString()
                        val password = response.body()?.customer_password.toString()
                        val name = response.body()?.customer_name.toString()
                        val gender = response.body()?.customer_gender.toString()
                        val tel = response.body()?.customer_tel.toString()
                        val email = response.body()?.customer_email.toString()
                        val address = response.body()?.customer_address.toString()
                        intent.putExtra("Data",Data(username, password, name, gender, tel, email, address))

                        Toast.makeText(applicationContext, "Successfully login", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finish()

                    }
                    else {
                        Toast.makeText(applicationContext, "login Fail", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                override fun onFailure(call: Call<Customer>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}