package com.example.btvn26_11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.btvn26_11.databinding.FragmentAddEditStudentBinding

class AddEditStudentFragment : Fragment() {

    private var _binding: FragmentAddEditStudentBinding? = null
    private val binding get() = _binding!!

    private val args: AddEditStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditStudentBinding.inflate(inflater, container, false)
        val view = binding.root

        // Nếu có dữ liệu sinh viên, hiển thị nó
        val student = args.student
        binding.editTextName.setText(student.name)
        binding.editTextId.setText(student.id)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val id = binding.editTextId.text.toString()

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
