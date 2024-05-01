package com.example.mpstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.mpstudy.databinding.ActivityOneFragmentBinding

class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ActivityOneFragmentBinding.inflate(layoutInflater, container, false)
        binding.button.setOnClickListener{
            Toast.makeText(context, "눌렀다", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}