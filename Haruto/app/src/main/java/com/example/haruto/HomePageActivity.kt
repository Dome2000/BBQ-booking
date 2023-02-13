package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.Toast
import com.example.haruto.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var data = intent.extras
        var Data: Data? = data?.getParcelable("Data")
        binding.bottomNavigation.selectedItemId = R.id.home_page
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
        val booking = findViewById<Button>(R.id.booked)
        booking.setOnClickListener{
            val  reserve = Intent(this, ReserveActivity::class.java)
                reserve.putExtra("Data",Data)
            startActivity(reserve)
        }
    }
}



