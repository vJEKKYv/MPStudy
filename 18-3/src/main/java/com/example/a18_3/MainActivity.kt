package com.example.a18_3

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.a18_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            Glide.with(this)
                .load(it.data!!.data)
                .into(binding.resultImage)
        }

        binding.loadFromResource.setOnClickListener {
            Glide.with(this)
                .load(R.drawable.seoul)
                .into(binding.resultImage)
        }
        binding.loadFromGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            requestLauncher.launch(intent)
        }
        binding.loadFromWeb.setOnClickListener {
            Glide.with(this)
                .load("https://tukorea.ac.kr/sites/tukorea/images/common/logo_c.png")
                .override(200,200)
                .into(binding.resultImage)
        }
        binding.usingImageData.setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load("https://tukorea.ac.kr/sites/tukorea/images/common/logo_c.png")
                .override(100,100)
                .into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        binding.resultImage.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }
}