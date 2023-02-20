package com.example.vkservicesabdusalyamova.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.vkservicesabdusalyamova.presentation.recyclerview.ServiceListAdapter
import com.example.vkservicesabdusalyamova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var serviceListAdapter: ServiceListAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        servicesOnClickListener()
        binding.bReply.setOnClickListener(this)

        observerLiveDataOfServices()

        getData()

    }

    override fun onClick(view: View?) {
        when (view){
            binding.bReply -> {
                getData()
                binding.bReply.visibility = View.INVISIBLE
            }
        }
    }

    private fun observerLiveDataOfServices() {
        viewModel.listServicesLiveData.observe(this) {
            if (it!=null) {
                serviceListAdapter.submitList(it)
                binding.bReply.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "При загрузке данных произошла ошибка",
                    Toast.LENGTH_SHORT).show()
                with(binding.bReply) {
                    visibility = View.VISIBLE
                }
            }
        }
    }

    private fun servicesOnClickListener() {
        serviceListAdapter.showServiceCard = {
            val intent = ServiceCardActivity.newIntent(this, it)
            startActivity(intent)
        }
    }

    private fun getData() {
        viewModel.getListServices()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initRecyclerView() {
        serviceListAdapter = ServiceListAdapter()
        binding.rvServices.adapter = serviceListAdapter
    }
}