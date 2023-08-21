package com.example.aritify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aritify.adapter.AllProductAdapter
import com.example.aritify.databinding.ActivityAllProductBinding
import com.example.aritify.mvvm.ViewModel

class AllProduct : AppCompatActivity() {

    private lateinit var binding: ActivityAllProductBinding
    private lateinit var vm : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.transparent)


        vm = ViewModelProvider(this).get(ViewModel::class.java)
        val allProductAdapter = AllProductAdapter()


        vm.myItem.observe(this, Observer {
            allProductAdapter.setItemList1(it)
            Toast.makeText(this, "${it.price[0]}", Toast.LENGTH_SHORT).show()
        })

        binding.productRecylerview.layoutManager = LinearLayoutManager(this)
        binding.productRecylerview.adapter = allProductAdapter


    }
}