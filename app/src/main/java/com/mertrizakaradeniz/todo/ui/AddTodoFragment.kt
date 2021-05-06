package com.mertrizakaradeniz.todo.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mertrizakaradeniz.todo.R
import com.mertrizakaradeniz.todo.databinding.FragmentAddTodoBinding
import com.mertrizakaradeniz.todo.model.Todo
import com.mertrizakaradeniz.todo.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : Fragment(R.layout.fragment_add_todo) {

    private var _binding: FragmentAddTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by viewModels<TodoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_addTodoFragment_to_todoListFragment
            )
        }

        binding.btnAdd.setOnClickListener { mView ->
            saveTodo(mView)
        }
    }

    private fun saveTodo(mView: View) {
        val todoName = binding.etTodoTitle.text.toString()
        if (todoName.isNotEmpty()) {
            val todo = Todo(0, todoName)
            viewModel.insertTodo(todo)

            Snackbar.make(
                mView,
                "Todo added",
                Snackbar.LENGTH_SHORT
            ).show()

            mView.findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
        } else {
            val toast = Toast.makeText(activity, "Todo title cannot be empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}