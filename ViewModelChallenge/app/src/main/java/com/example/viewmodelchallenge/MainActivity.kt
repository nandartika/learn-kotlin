package com.example.viewmodelchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var viewModel: MainActivityViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

		binding.apply {
			outputText.text = viewModel.getCurrentCount().toString()
			addButton.setOnClickListener {
				outputText.text =
					viewModel.getUpdatedCount(inputField.text.toString().toInt())
			}
		}
	}
}