package com.example.a16_1

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.a16_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestContractLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                Toast.makeText(this, "${it.data?.data!!}", Toast.LENGTH_SHORT).show()
                val cursor = contentResolver.query(it.data?.data!!,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER),
                        null,
                        null,
                        null)

                if (cursor!!.moveToFirst()){
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContect.text = "name: $name, phone: $phone"
                }
            }
        }
        binding.addressBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            requestContractLauncher.launch(intent)
        }
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){}
        requestPermissionLauncher.launch("android.permission.READ_CONTACTS")
    }
}