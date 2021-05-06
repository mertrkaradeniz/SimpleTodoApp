package com.mertrizakaradeniz.todo.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mertrizakaradeniz.todo.databinding.TodoItemBinding
import com.mertrizakaradeniz.todo.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            TodoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = differ.currentList[position]

        holder.binding.apply {
            tvTodoTitle.text = todo.todoTitle
        }

        holder.binding.checkBox.apply {
            setOnClickListener {
                holder.binding.apply {
                    if (isChecked) {
                        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or
                                Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and
                                Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
            }
        }
    }

    override fun getItemCount() = differ.currentList.size

}
