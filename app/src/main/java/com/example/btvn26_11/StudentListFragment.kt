package com.example.btvn26_11

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {

    private lateinit var studentAdapter: ArrayAdapter<StudentModel>
    private lateinit var students: MutableList<StudentModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002")
        )

        val listView: ListView = view.findViewById(R.id.listViewStudents)
        studentAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, students)
        listView.adapter = studentAdapter

        registerForContextMenu(listView)

        listView.setOnItemClickListener { _, _, position, _ ->
            val student = students[position]
            val action = StudentListFragmentDirections.actionToAddEditStudentFragment(student)
            findNavController().navigate(action)
        }

        return view
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val student = students[info.position]

        return when (item.itemId) {
            R.id.action_edit -> {
                val action = StudentListFragmentDirections.actionToAddEditStudentFragment(student)
                findNavController().navigate(action)
                true
            }
            R.id.action_remove -> {
                students.remove(student)
                studentAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
