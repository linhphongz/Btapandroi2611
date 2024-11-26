package com.example.btvn26_11

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var students: MutableList<StudentModel>

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val studentName = data?.getStringExtra("student_name") ?: ""
            val studentId = data?.getStringExtra("student_mssv") ?: ""

            if (studentName.isNotEmpty() && studentId.isNotEmpty()) {
                val newStudent = StudentModel(studentName, studentId)
                studentAdapter.addStudent(newStudent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        )

        studentAdapter = StudentAdapter(students, { student ->
            editStudent(student)
        }, { student ->
            removeStudent(student)
        })

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        registerForContextMenu(recyclerView)
    }

    private fun editStudent(student: StudentModel) {
        val intent = Intent(this, EditStudentActivity::class.java)
        intent.putExtra("student_name", student.studentName)
        intent.putExtra("student_mssv", student.studentId)
        startActivity(intent)
    }

    private fun removeStudent(student: StudentModel) {
        studentAdapter.removeStudent(student)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                resultLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu) // context_menu.xml chứa Edit và Remove
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val student = (item.menuInfo as AdapterView.AdapterContextMenuInfo).targetView.tag as StudentModel
        return when (item.itemId) {
            R.id.edit -> {
                editStudent(student)
                true
            }
            R.id.remove -> {
                removeStudent(student)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}
