package com.anushka.viewmodeldemo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.viewmodeldemo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var viewModel: MainActivityViewModel
	private lateinit var viewModelFactory: MainActivityViewModelFactory

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		viewModelFactory = MainActivityViewModelFactory(125)
		viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
		binding.viewModel = viewModel
		binding.lifecycleOwner = this
	}
}