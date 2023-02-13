package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.haruto.databinding.ActivityEmployeeLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeLoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEmployeeLoginBinding
    private val createClient = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("เข้าสู่ระบบ");
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    fun loginEmp(v: View) {
        createClient.loginEmployee(
            binding.usernameEDTEmp.text.toString(),
            binding.usernameEDTEmp.text.toString(),
        )
            .enqueue(object : Callback<Employee> {
                override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                    val username = findViewById(R.id.usernameEDT_emp) as EditText
                    val password = findViewById(R.id.passwordEDT_emp) as EditText
                    if (username.text.isEmpty() and password.text.isEmpty() ) {
                        Toast.makeText(this@EmployeeLoginActivity, "กรุณาใส่ไอดีและพาสเวิร์ด", Toast.LENGTH_LONG).show()
                    }
                    else if (username.text.isEmpty()){
                        Toast.makeText(this@EmployeeLoginActivity, "กรุณากรอกช่อง Username", Toast.LENGTH_LONG).show()
                    }
                    else if (password.text.isEmpty()) {
                        Toast.makeText(this@EmployeeLoginActivity, "กรุณากรอกช่อง Password", Toast.LENGTH_LONG).show()
                    }
                    else if (response.isSuccessful) {
                        val intent = Intent(this@EmployeeLoginActivity, HomePageEmpActivity::class.java)
                        val username = response.body()?.employee_username.toString()
                        val password = response.body()?.employee_password.toString()
                        val name = response.body()?.employee_name.toString()
                        val gender = response.body()?.employee_gender.toString()
                        val tel = response.body()?.employee_tel.toString()
                        val email = response.body()?.employee_email.toString()
                        val address = response.body()?.employee_address.toString()
                        intent.putExtra("Data",Data(username, password, name, gender, tel, email, address))

                        Toast.makeText(applicationContext, "Successfully login", Toast.LENGTH_LONG).show()
                        finish()
                        startActivity(intent)

                    }
                    else {
                        Toast.makeText(applicationContext, "login Fail", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                override fun onFailure(call: Call<Employee>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}

