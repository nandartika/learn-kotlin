package com.anushka.bindingdemo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.anushka.bindingdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.submitButton.setOnClickListener {
			displayGreeting()
		}
	}

	private fun displayGreeting() {
		binding.apply {
			greetingTextView.text = "Hello! ${nameEditText.text}"
		}
	}
}