package com.example.btvn26_11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val studentName = intent.getStringExtra("student_name") ?: ""
        val studentId = intent.getStringExtra("student_mssv") ?: ""

        val nameEditText = findViewById<EditText>(R.id.edit_student_name)
        val idEditText = findViewById<EditText>(R.id.edit_student_id)

        nameEditText.setText(studentName)
        idEditText.setText(studentId)

        val saveButton = findViewById<Button>(R.id.btn_save)
        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedId = idEditText.text.toString()
            Log.v("a2","Saved")
            val resultIntent = Intent()
            resultIntent.putExtra("updated_name", updatedName)
            resultIntent.putExtra("updated_id", updatedId)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
