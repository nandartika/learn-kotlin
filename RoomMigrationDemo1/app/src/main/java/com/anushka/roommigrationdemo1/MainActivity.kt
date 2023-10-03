package com.anushka.roommigrationdemo1

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = StudentDatabase.getInstance(application).subscriberDAO

        val nameEditText = findViewById<EditText>(R.id.etName)
        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val courseEditText = findViewById<EditText>(R.id.etCourse)
        val button = findViewById<Button>(R.id.btnSubmit)
        button.setOnClickListener {
            lifecycleScope.launch {
                if (!TextUtils.isEmpty(nameEditText.text) && !TextUtils.isEmpty(emailEditText.text) && !TextUtils.isEmpty(
                        courseEditText.text
                    )
                ) {
                    val name = nameEditText.text.toString()
                    val email = emailEditText.text.toString()
                    val course = courseEditText.text.toString()

                    dao.insertStudent(Student(0, name, email, course))
                    nameEditText.setText("")
                    emailEditText.setText("")
                    courseEditText.setText("")
                } else {
                    Toast.makeText(applicationContext, "Please fill all field!", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}