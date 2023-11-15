package com.example.reloj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Toast
import com.example.reloj.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private var isRunning = false
    private var timer: CountDownTimer? = null
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.horaPick.minValue = 0
        binding.horaPick.maxValue = 23

        binding.minutosPick.minValue = 0
        binding.minutosPick.maxValue = 59

        binding.segundosPick.minValue = 0
        binding.segundosPick.maxValue = 59
        binding.startButton.setOnClickListener {
            iniciartemp()
        }
        binding.stopButton.setOnClickListener {
            parartemp()
        }
        binding.startButtoncro.setOnClickListener {
            iniciarcrono()
        }
        binding.stopButtoncro.setOnClickListener {
            paracrono()
        }
    }

    private fun iniciartemp() {
        val totalmilseg =
            (binding.horaPick.value * 3600000L) +
                    (binding.minutosPick.value * 60000L) +
                    (binding.segundosPick.value * 1000L)

        binding.startButton.isEnabled = false
        binding.stopButton.isEnabled = true

        timer = object : CountDownTimer(totalmilseg, 1000) {
            override fun onTick(finalmiliseg: Long) {
                val hours = finalmiliseg / 3600000
                val minutes = (finalmiliseg % 3600000) / 60000
                val seconds = (finalmiliseg % 60000) / 1000

                binding.horaPick.value = hours.toInt()
                binding.minutosPick.value = minutes.toInt()
                binding.segundosPick.value = seconds.toInt()
            }

            override fun onFinish() {
                parartemp()
                Toast.makeText(this@MainActivity2, "tiempo finalizao juan soy un cracken:(", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun parartemp() {
        timer?.cancel()
        binding.startButton.isEnabled = true
        binding.stopButton.isEnabled = false
    }

    private fun iniciarcrono() {
        if (!isRunning) {
            binding.cronometro .base = SystemClock.elapsedRealtime() // este vainito Resetea el cronómetro
            binding.cronometro .start()
            isRunning = true
        }
    }

    private fun paracrono() {
        if (isRunning) {
            binding.cronometro .stop()
            isRunning = false
        }
    }
}




