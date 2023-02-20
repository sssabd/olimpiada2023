package com.example.vkservicesabdusalyamova.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.vkservicesabdusalyamova.presentation.recyclerview.ServiceListAdapter
import com.example.vkservicesabdusalyamova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var serviceListAdapter: ServiceListAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
        clickServicesListener()
        clickReplyListener()
        observerLiveDataOfServices()

        loadData()

    }

    private fun observerLiveDataOfServices() {
        viewModel.listServicesLiveData.observe(this) {
            if (it!=null) {
                serviceListAdapter.submitList(it)
                binding.btnReply.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                with(binding.btnReply) {
                    visibility = View.VISIBLE
                }
            }
        }
    }

    private fun clickReplyListener() {
        with(binding.btnReply) {
            setOnClickListener {
                loadData()
            }
            visibility = View.INVISIBLE
        }
    }

    private fun loadData() {
        viewModel.getListServices()
    }

    private fun clickServicesListener() {
        serviceListAdapter.showDetailsAboutTheService = {
            val intent = ServiceCardActivity.newIntent(this, it)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecycleView() {
        serviceListAdapter = ServiceListAdapter()
        binding.rvServices.adapter = serviceListAdapter
    }
}