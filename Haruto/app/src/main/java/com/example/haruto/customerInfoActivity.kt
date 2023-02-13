package com.example.haruto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.haruto.databinding.ActivityCustomerInfoBinding
import com.example.haruto.databinding.ActivityRepasswordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class customerInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerInfoBinding
    val createCliend = harutoAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle("จัดบัญชี");
        var data = intent.extras
        var newData: Data? = data?.getParcelable<Data>("Data")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.usernameEDT.isEnabled = false

        val userTxt: EditText = findViewById(R.id.usernameEDT)
        val nameTxt: EditText = findViewById(R.id.nameEDT)
        val addressTxt: EditText = findViewById(R.id.addressEDT)
        val telTxt: EditText = findViewById(R.id.telEDT)
        val emailTxt: EditText = findViewById(R.id.emailEDT)



        userTxt.setText(newData?.username)
        nameTxt.setText(newData?.name)
        addressTxt.setText(newData?.address)
        telTxt.setText(newData?.tel)

        val gender: String? = newData?.gender
        val male: RadioButton = findViewById(R.id.male)
        val female: RadioButton = findViewById(R.id.female)

        if(gender == "Male"){
            male.isChecked = true
        }
        else if(gender == "Female"){
            female.isChecked = true
        }

        emailTxt.setText(newData?.email)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveInfo(view: View) {
        var selectRadio: Int = binding.radioGroup.checkedRadioButtonId
        var radioChecked: RadioButton = findViewById(selectRadio)
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.apply {
            setTitle("Warning")
            setMessage("Do you want to update")
            setNegativeButton("Yes"){dialog,which ->
                createCliend.updateCustomer(
                    binding.usernameEDT.text.toString(),
                    binding.nameEDT.text.toString(),
                    radioChecked.text.toString(),
                    binding.telEDT.text.toString(),
                    binding.emailEDT.text.toString(),
                    binding.addressEDT.text.toString()
                )
                    .enqueue(object : Callback<Customer> {
                        override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                            if(response.isSuccessful){
                                Toast.makeText(applicationContext,"Successful", Toast.LENGTH_LONG).show()
                                val intent = Intent(this@customerInfoActivity, MainActivity::class.java)
                                startActivity(intent)
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