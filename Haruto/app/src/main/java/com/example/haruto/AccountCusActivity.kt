package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.haruto.databinding.ActivityAccountCusBinding


class AccountCusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountCusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountCusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("บัญชี");
        var data = intent.extras
        var Data: Data? = data?.getParcelable("Data")
        binding.username.text = Data?.username
        binding.email.text = Data?.email

        binding.bottomNavigation.selectedItemId = R.id.accountCus
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_page-> {
                    val menuHome = Intent(this, HomePageActivity::class.java)
                    menuHome.putExtra("Data",Data)
                    startActivity(menuHome)
                }
                R.id.mybooking -> {
                    val menuMybooking = Intent(this, MybookingActivity::class.java)
                    menuMybooking.putExtra("Data", Data)
                    startActivity(menuMybooking)
                }
                R.id.accountCus -> {
                    val menuAccountCus = Intent(this, AccountCusActivity::class.java)
                    menuAccountCus.putExtra("Data", Data)
                    startActivity(menuAccountCus)
                }
            }
            true
        }
        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener{
            val  out = Intent(this, MainActivity::class.java)
            Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT).show()
            startActivity(out)
        }
        val edt = findViewById<Button>(R.id.edtAcc)
        edt.setOnClickListener{
            val  acc = Intent(this, customerInfoActivity::class.java)
            acc.putExtra("Data", Data)
            startActivity(acc)
        }
        val rePW = findViewById<Button>(R.id.rePassword)
        rePW.setOnClickListener{
            val  re = Intent(this, RepasswordActivity::class.java)
            re.putExtra("Data", Data)
            startActivity(re)
        }
    }
}