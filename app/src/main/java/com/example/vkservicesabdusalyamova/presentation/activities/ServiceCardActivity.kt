package com.example.vkservicesabdusalyamova.presentation.activities

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.domain.models.Item
import com.example.vkservicesabdusalyamova.databinding.ActivityServiceCardBinding
import com.squareup.picasso.Picasso

class ServiceCardActivity : AppCompatActivity() {
    private var _binding: ActivityServiceCardBinding? = null
    private val binding: ActivityServiceCardBinding
        get() = _binding ?: throw RuntimeException("ActivityDetailsAboutTheServiceBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityServiceCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseArgs()
        clickUrlServiceOnCLickListener()
        backBtnClickListener()
    }

    private fun clickUrlServiceOnCLickListener() {
        binding.tvServiceLink.setOnClickListener {
            val url = binding.tvServiceLink.text.toString()
            if (url.startsWith("http://") || url.startsWith("https://")) {
                val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intentBrowser)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Неверный формат URL",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun backBtnClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun parseArgs() {
        with(binding) {
            tvNameService.text = intent.getStringExtra(NAME_KEY)
            tvNameServiceInToolbar.text = intent.getStringExtra(NAME_KEY)
            Picasso.get().load(intent.getStringExtra(ICON_KEY)).into(ivIconService)
            tvDescriptionService.text =intent.getStringExtra(DESCRIPTION_KEY)
            tvServiceLink.text = intent.getStringExtra(URL_KEY)
            tvServiceLink.paintFlags = binding.tvServiceLink.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val NAME_KEY = "name_key"
        private const val ICON_KEY = "icon_key"
        private const val URL_KEY = "url_key"
        private const val DESCRIPTION_KEY = "description_key"
        fun newIntent(context: Context, item: Item): Intent {
            val intent = Intent(context, ServiceCardActivity::class.java)
            intent.apply {
                putExtra(NAME_KEY, item.name)
                putExtra(ICON_KEY, item.icon_url)
                putExtra(URL_KEY, item.service_url)
                putExtra(DESCRIPTION_KEY, item.description)
            }
            return intent
        }
    }

}