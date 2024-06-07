package com.example.a18_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a18_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Volley"

        val queue = Volley.newRequestQueue(this)
        binding.stringRequest.setOnClickListener {
            val url = "https://www.tukorea.ac.kr"
            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { setVisibility(binding.resultString)
                binding.resultString.text = "Message: $it"},
                Response.ErrorListener { setvisibility(binding.resultString)
                binding.resultString.text = "Error: $it"})
        }
    }
}