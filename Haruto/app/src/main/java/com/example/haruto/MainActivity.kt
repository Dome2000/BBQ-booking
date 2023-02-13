package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setTitle(" ");
        val cus = findViewById<Button>(R.id.logincustomer)
        cus.setOnClickListener{
            val  cus = Intent(this, CustomerLoginActivity::class.java)
            startActivity(cus)
        }
        val emp = findViewById<Button>(R.id.loginemployee)
        emp.setOnClickListener{
            val  emp = Intent(this, EmployeeLoginActivity::class.java)
            startActivity(emp)
        }
    }
}