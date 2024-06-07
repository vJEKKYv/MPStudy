package com.example.a18_1

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.telecom.TelecomManager
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a18_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "스마트폰 정보"

        val manager = getSystemService(Context.TELECOM_SERVICE) as TelephonyManager

        binding.phoneStatus.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                manager.registerTelephonyCallback(mainExecutor,
                    object:TelephonyCallback(), TelephonyCallback.ServiceStateListener{
                        override fun onServiceStateChanged(serviceState: ServiceState) {
                            Toast.makeText(applicationContext, set, Toast.LENGTH_SHORT).show
                        }
                    })
            }else{
                val phoneStateListener = object :PhoneStateListener(){
                    override fun onServiceStateChanged(serviceState: ServiceState?) {
                        super.onServiceStateChanged(serviceState)
                        Toast.makeText(applicationContext,setPhoneServiceState(serviceState), Toast.LENGTH_SHORT).show()
                    }
                }
                manager.listen(phoneStateListener,PhoneStateListener.LISTEN_SERVICE_STATE)
            }
        }
        binding.phoneNumber.setOnClickListener {
            var phoneNumber = "unknown"

            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.TIRAMISU){
                val subscriptionManager = getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
                for (subscriptionInfo:SubscriptionInfo in subscriptionManager.activeSubscriptionInfoList){
                    val  activeDataSubscriptionId = subscriptionInfo.subscriptionId
                    phoneNumber = subscriptionManager.getPhoneNumber(activeDataSubscriptionId)
                }
            }else{
                phoneNumber = manager.line1Number
            }
            binding.resultText.text = "전화번호: "+phoneNumber
        }

        binding.networkStatus.setOnClickListener {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var isNetworkAvailable = false
            binding.resultText.text = "네트워크 연결 안됨"
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val nw = connectivityManager.activeNetwork?:return@setOnClickListener
                val actNw = connectivityManager.getNetworkCapabilities(nw)?:return@setOnClickListener
                isNetworkAvailable = when{
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }else{
                isNetworkAvailable = connectivityManager.activeNetworkInfo?.isConnected?:false
            }
            binding.resultText.text = "네트워크가" +if(isNetworkAvailable) "연결됨" else "연결 안됨"
        }

        //onUnavailable()이 호출되지 않음
        binding.networkRequest.setOnClickListener {
            val networkReq = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build()
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.requestNetwork(networkReq,object : ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    runOnUiThread{ binding.resultText.text = "[REQUEST]네트워크가 연결됨" }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    runOnUiThread{ binding.resultText.text = "[REQUEST]네트워크가 연결 안됨"}
                }
            },1000)
        }
        requestPermissions(arrayOf("android.permission.READ_PHONE_STATE",
            "android.permission.READ_PHONE_NUMBERS"),0)
    }

}