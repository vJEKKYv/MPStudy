package com.example.a16_2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.ListFormatter.Width
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.Data
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a16_2.databinding.ActivityMainBinding
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            try{
                val calRatio = calculateInSampleSize(it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize))
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio
                //이미지 로딩
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream,null,option)
                inputStream!!.close()
                if(bitmap !=null)   binding.userImageView.setImageBitmap(bitmap)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        binding.galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }
        val requestCameraThumbnailLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val bitmap = it?.data?.extras?.get("data") as Bitmap
            binding.userImageView.setImageBitmap(bitmap)
        }
        binding.cameraButtonByData.setOnClickListener {
            requestCameraThumbnailLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        lateinit var filePath: String
        val requetCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let{
                binding.userImageView.setImageBitmap(bitmap)
            }
        }
        binding.cameraButtonByFile.setOnClickListener {
            val timeStamp:String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir:File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JEPG_${timeStamp}",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI:Uri = FileProvider.getUriForFile(
                this,
                "com.example.ch16_2_gellery.fileprovider", file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            requetCameraFileLauncher.launch(intent)
        }
    }
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int):Int{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try{
            var inputStream = contentResolver.openInputStream(fileUri)

            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
        }catch (e:Exception){
            e.printStackTrace()
        }
        val (height:Int, width:Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 계산.
        if (height > reqHeight || width > reqWidth){
            val halfHeight:Int = height/2
            val halfWidth:Int = width/2
            while (halfHeight / inSampleSize >=reqHeight && halfWidth / inSampleSize >=reqWidth){
                inSampleSize *=2
            }
        }
        return inSampleSize
    }
}