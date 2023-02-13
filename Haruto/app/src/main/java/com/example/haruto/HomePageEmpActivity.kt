package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haruto.databinding.ActivityHomePageEmpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageEmpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageEmpBinding
    var ReserveList = arrayListOf<Reserve>()
    val createClient = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageEmpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("การจองทั้งหมด");
        var data = intent.extras
        var Data: Data? = data?.getParcelable("Data")
//        Toast.makeText(applicationContext, "${Data?.username}", Toast.LENGTH_SHORT).show()
        binding.bottomNavigation.selectedItemId = R.id.home_page_emp
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
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
    fun callReserve() {
        ReserveList.clear()
        createClient.showReserve2()
            .enqueue(object : Callback<List<Reserve>> {
                override fun onResponse(
                    call: Call<List<Reserve>>,
                    response: Response<List<Reserve>>
                ) {
                    response.body()?.forEach {
                        ReserveList.add(Reserve(it.reserve_id, it.reserve_date,it.reserve_time,it.total_price,it.count,it.customer_username,it.employee_username,it.status_id))
                    }
                    binding.recyclerView.adapter =
                        ReserveAdater2(ReserveList, applicationContext)
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
//    fun clickSearch(view: View) {
//        ReserveList.clear()
//        if (binding.edtSearch.text!!.isEmpty()) {
//            callReserve()
////            Toast.makeText(applicationContext, "aaa", Toast.LENGTH_LONG).show()
//        } else {
//            createClient.search(binding.edtSearch.text.toString().toInt())
//                .enqueue(object : Callback<Reserve> {
//                    override fun onResponse(call: Call<Reserve>, response: Response<Reserve>) {
//                        if(response.isSuccessful){
//                            ReserveList.add(
//                                Reserve(
//                                    response.body()?.reserve_id.toString(),
//                                    response.body()?.reserve_date.toString(),
//                                    response.body()?.reserve_time.toString(),
//                                    response.body()?.total_price.toString().toInt(),
//                                    response.body()?.count.toString().toInt(),
//                                    response.body()?.customer_username.toString(),
//                                    response.body()?.employee_username.toString(),
//                                    response.body()?.status_id.toString().toInt(),
//                                )
//                            )
//                            binding.recyclerView.adapter =
//                                ReserveAdater2(ReserveList, applicationContext)
//                        }else{
//                            Toast.makeText(applicationContext, "Not Found", Toast.LENGTH_LONG).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Reserve>, t: Throwable) = t.printStackTrace()
//                })
//        }
//    }
}
