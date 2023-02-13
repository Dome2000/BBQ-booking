package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.haruto.databinding.ActivityAccountEmpBinding

class AccountEmpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountEmpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountEmpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("บัญชี");
        var data = intent.extras
        var Data: Data? = data?.getParcelable("Data")
        binding.EmpName.text = Data?.name

        binding.bottomNavigation.selectedItemId = R.id.accountEmp
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_page_emp -> {
                    val menuHomeEmp = Intent(this, HomePageEmpActivity::class.java)
                    menuHomeEmp.putExtra("Data", Data)
                    startActivity(menuHomeEmp)
                }
                R.id.accountEmp -> {
                    val menuAccountEmp = Intent(this, AccountEmpActivity::class.java)
                    menuAccountEmp.putExtra("Data", Data)
                    startActivity(menuAccountEmp)
                }
            }
            true
        }
    }
    fun btn_logout(view: View) {
        val  logout = Intent(this, MainActivity::class.java)
        Toast.makeText(applicationContext, "Successfully logout", Toast.LENGTH_LONG).show()
        startActivity(logout)
    }
}