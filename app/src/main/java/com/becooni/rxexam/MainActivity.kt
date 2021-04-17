package com.becooni.rxexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.becooni.rxexam.databinding.ActivityMainBinding
import com.becooni.rxexam.operator.conditional.takeUntil

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        start()

        takeUntil()
    }
}