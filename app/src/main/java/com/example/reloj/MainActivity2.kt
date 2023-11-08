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

        binding.hoursPicker.minValue = 0
        binding.hoursPicker.maxValue = 23

        binding.minutesPicker.minValue = 0
        binding.minutesPicker.maxValue = 59

        binding.secondsPicker.minValue = 0
        binding.secondsPicker.maxValue = 59
        binding.startButton.setOnClickListener {
            startTimer()
        }
        binding.stopButton.setOnClickListener {
            stopTimer()
        }
        binding.startButtoncro.setOnClickListener {
            startChronometer()
        }
        binding.stopButtoncro.setOnClickListener {
            stopChronometer()
        }
    }

    private fun startTimer() {
        val totalMilliseconds =
            (binding.hoursPicker.value * 3600000L) +
                    (binding.minutesPicker.value * 60000L) +
                    (binding.secondsPicker.value * 1000L)

        binding.startButton.isEnabled = false
        binding.stopButton.isEnabled = true

        timer = object : CountDownTimer(totalMilliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hours = millisUntilFinished / 3600000
                val minutes = (millisUntilFinished % 3600000) / 60000
                val seconds = (millisUntilFinished % 60000) / 1000

                binding.hoursPicker.value = hours.toInt()
                binding.minutesPicker.value = minutes.toInt()
                binding.secondsPicker.value = seconds.toInt()
            }

            override fun onFinish() {
                stopTimer()
                Toast.makeText(this@MainActivity2, "tiempo finalizao juan soy un cracken:(", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun stopTimer() {
        timer?.cancel()
        binding.startButton.isEnabled = true
        binding.stopButton.isEnabled = false
    }

    private fun startChronometer() {
        if (!isRunning) {
            binding.chronometer.base = SystemClock.elapsedRealtime() // este vainito Resetea el cronómetro
            binding.chronometer.start()
            isRunning = true
        }
    }

    private fun stopChronometer() {
        if (isRunning) {
            binding.chronometer.stop()
            isRunning = false
        }
    }
}




