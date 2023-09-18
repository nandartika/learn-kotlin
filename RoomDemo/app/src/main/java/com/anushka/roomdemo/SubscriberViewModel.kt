package com.anushka.roomdemo

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.db.SubscriberRepository
import com.anushka.roomdemo.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {
    val subscribers = repository.subscriber
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateAndDelete: Subscriber

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>>
        get() = _statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (inputName.value == "") {
            _statusMessage.value = Event("Please enter subscriber's name")
        } else if (inputEmail.value == "") {
            _statusMessage.value = Event("Please enter subscriber's email")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            _statusMessage.value = Event("Please enter correct email address")
        } else {
            if (isUpdateOrDelete) {
                subscriberToUpdateAndDelete.name = inputName.value!!
                subscriberToUpdateAndDelete.email = inputEmail.value!!
                update(subscriberToUpdateAndDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                insert(Subscriber(0, name, email))
                inputName.value = ""
                inputEmail.value = ""
            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateAndDelete)
        } else {
            clearAll()
        }
    }

    private fun insert(subscriber: Subscriber) = viewModelScope.launch {
        val newRowId = repository.insert(subscriber)

        withContext(Dispatchers.Main) {
            if (newRowId > -1) {
                _statusMessage.value = Event("Subscriber Inserted Successfully ${newRowId}!")
            } else {
                _statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    private fun update(subscriber: Subscriber) = viewModelScope.launch {
        val numberOfRows = repository.update(subscriber)

        withContext(Dispatchers.Main) {
            if (numberOfRows > 0) {
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                _statusMessage.value = Event("$numberOfRows Rows Updated Successfully!")
            } else {
                _statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    private fun delete(subscriber: Subscriber) = viewModelScope.launch {
        val numberOfRows = repository.delete(subscriber)

        withContext(Dispatchers.Main) {
            if (numberOfRows > 0) {
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                _statusMessage.value = Event("$numberOfRows Rows Deleted Successfully!")
            } else {
                _statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    private fun clearAll() = viewModelScope.launch {
        val numberOfRows = repository.deleteAll()

        withContext(Dispatchers.Main) {
            if (numberOfRows > 0) {
                _statusMessage.value = Event("$numberOfRows Rows Deleted Successfully!")
            } else {
                _statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateAndDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }
}