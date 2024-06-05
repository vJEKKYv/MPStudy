package com.example.a17_2_saveinfile

import android.content.ContentUris
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.a17_2_saveinfile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            if (it.any{it.value}){
                Toast.makeText(this, "권한 승인", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "권한 거부", Toast.LENGTH_SHORT).show()
            }
        }

        requestPermissionLauncher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_MEDIA_IMAGES))

        binding.showImage.setOnClickListener {
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
            )
            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,null,null,null)
            cursor?.let {
                while (cursor.moveToNext()){
                    Log.d("kkang", "_id : ${cursor.getLong(0)}, name : ${cursor.getString(1)}" )
                }
            }

            if (cursor?.count!! > 0){
                cursor?.moveToFirst()
                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    cursor!!.getLong(0)
                )

                val stream = contentResolver.openInputStream(contentUri)
                val option = BitmapFactory.Options()
                option.inSampleSize = 1
                val bitmap = BitmapFactory.decodeStream(stream, null, option)
                binding.resultImageView.setImageBitmap(bitmap)
            }
        }
    }
}