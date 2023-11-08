package com.example.reloj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.reloj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        object : CountDownTimer(4000,1){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@MainActivity,MainActivity2::class.java))
            }
        }.start()
    }
}