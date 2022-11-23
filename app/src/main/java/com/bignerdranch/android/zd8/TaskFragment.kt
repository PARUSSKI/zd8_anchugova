package com.bignerdranch.android.zd8

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "TaskFragment"
class TaskFragment: Fragment() {
    private lateinit var task: Task
    private lateinit var titleField: EditText
    private lateinit var timeField: EditText
    private lateinit var dateField: EditText
    private lateinit var descField: EditText
    private lateinit var taskRecyclerView: RecyclerView
    private var adapter: CustomRecyclerAdapter? = CustomRecyclerAdapter(emptyList())

    interface Callbacks {
        fun onTaskSelected(title: String)


    }

    private var callbacks: Callbacks? = null
    override fun onAttach(context: Context) {

        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    private val taskListViewModel: TaskListViewModel by lazy {
        ViewModelProviders.of(this).get(TaskListViewModel::class.java)
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


    companion object {
        fun newInstance(): TaskFragment{
            return TaskFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.recyclerview_item_list,container,false)
        taskRecyclerView = view.findViewById(R.id.recyclerView2) as RecyclerView
        taskRecyclerView.layoutManager=LinearLayoutManager(context)
        taskRecyclerView.adapter = adapter
        return view
    }

    private fun updateUI(tasks: List<Task>) {
        adapter = CustomRecyclerAdapter(tasks)
        taskRecyclerView.adapter = adapter
    }

    private inner class TaskHolder(view: View): RecyclerView.ViewHolder(view),View.OnClickListener{
        private lateinit var task: Task
        val titleText:EditText = itemView.findViewById(R.id.title)
        val descText:EditText = itemView.findViewById(R.id.description)
        val dateText:EditText = itemView.findViewById(R.id.date)
        val timeText:EditText = itemView.findViewById(R.id.time)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View){
            callbacks?.onTaskSelected(task.title)
        }
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        titleField.addTextChangedListener(titleWatcher)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListViewModel.taskListLiveData.observe(
            viewLifecycleOwner,
            Observer { tasks->
                tasks?.let{
                    Log.i(TAG,"Tasks ${tasks.size}")
                    updateUI(tasks)
                }
            })
    }
}