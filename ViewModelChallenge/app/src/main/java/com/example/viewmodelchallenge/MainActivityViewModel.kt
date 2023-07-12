package com.example.viewmodelchallenge

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
	private var count = 0

	fun getCurrentCount() = count

	fun getUpdatedCount(value: Int): String {
		count += value
		return count.toString()
	}
}