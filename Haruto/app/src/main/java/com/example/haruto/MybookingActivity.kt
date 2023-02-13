package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.haruto.databinding.ActivityMybookingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MybookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMybookingBinding
    var ReserveList = arrayListOf<Reserve>()
    val createClient = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMybookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("การจองทั้งหมด");
        var data = intent.extras
        var Data: Data? = data?.getParcelable("Data")

        binding.bottomNavigation.selectedItemId = R.id.mybooking
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
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

    }
    fun callReserve() {
        var data = intent.extras
        var newData: Data? = data?.getParcelable("Data")
        ReserveList.clear()
        createClient.showReserve(newData?.username.toString())
            .enqueue(object : Callback<List<Reserve>> {
                override fun onResponse(
                    call: Call<List<Reserve>>,
                    response: Response<List<Reserve>>
                ) {
                    response.body()?.forEach {
                        ReserveList.add(Reserve(it.reserve_id, it.reserve_date,it.reserve_time,it.total_price,it.count,it.customer_username,"1",it.status_id))
                    }
                    binding.recyclerView.adapter =
                        ReserveAdater(ReserveList, applicationContext)
                }

                override fun onFailure(call: Call<List<Reserve>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "Error2", Toast.LENGTH_LONG).show()
                }
            })
    }
    override fun onResume() {
        super.onResume()
        callReserve()
    }
}