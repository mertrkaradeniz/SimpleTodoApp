package com.mertrizakaradeniz.todo.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertrizakaradeniz.todo.R
import com.mertrizakaradeniz.todo.adapters.TodoAdapter
import com.mertrizakaradeniz.todo.databinding.FragmentTodoListBinding
import com.mertrizakaradeniz.todo.model.Todo
import com.mertrizakaradeniz.todo.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment(R.layout.fragment_todo_list) {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var todoAdapter: TodoAdapter
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()

        binding.fabAddTodo.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_todoListFragment_to_addTodoFragment
            )
        }
    }

    private fun setupRv() {
        todoAdapter = TodoAdapter()
        binding.rvListTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        viewModel.getAllToDos.observe(viewLifecycleOwner, { listTodo ->
            updateUi(listTodo)
            todoAdapter.differ.submitList(listTodo)
        })
    }

    private fun updateUi(list: List<Todo>) {
        if (list.isNotEmpty()) {
            binding.rvListTodo.visibility = View.VISIBLE
            binding.cardViewNoTodo.visibility = View.GONE
        } else {
            binding.rvListTodo.visibility = View.GONE
            binding.cardViewNoTodo.visibility = View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}