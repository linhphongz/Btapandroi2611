package com.example.btvn26_11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val students: MutableList<StudentModel>,
    private val onEditClick: (StudentModel) -> Unit,
    private val onRemoveClick: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.textStudentName.text = student.studentName
        holder.textStudentId.text = student.studentId

        // Gán đối tượng student vào tag của itemView
        holder.itemView.tag = student

        holder.itemView.setOnLongClickListener { v ->
            v.showContextMenu()
            true
        }
    }

    override fun getItemCount(): Int = students.size

    fun addStudent(student: StudentModel) {
        students.add(student)
        notifyItemInserted(students.size - 1)
    }

    fun removeStudent(student: StudentModel) {
        val position = students.indexOf(student)
        if (position >= 0) {
            students.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}


