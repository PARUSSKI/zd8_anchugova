package com.bignerdranch.android.zd8

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private lateinit var taskRecyclerView: RecyclerView
 var adapter: CustomRecyclerAdapter? = null



class GeneralActivity : AppCompatActivity() {
    val tasks = mutableListOf<Task>()
    private val taskListViewModel: TaskListViewModel by lazy {
        ViewModelProviders.of(this).get(TaskListViewModel::class.java)
    }
    lateinit var task: Task

    init {
        for (i in 1..4) {
            val task = Task()
            task.title = "Task #$i"
            task.date = "22/22/22"
            tasks.add(task)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
       recyclerView.layoutManager = LinearLayoutManager(this)

        //val tasks = taskListViewModel.tasks
        adapter = CustomRecyclerAdapter(tasks)
        taskRecyclerView.adapter = adapter
        adapter!!.setOnItemClickListener(object : CustomRecyclerAdapter.onItemClickListener{
        override fun onItemClick(position: Int) {
         val intent= Intent(this@GeneralActivity,EditTaskActivity::class.java)
                intent.putExtra("title",tasks[position].title)
                intent.putExtra("date",tasks[position].date)
                intent.putExtra("time",tasks[position].time)
                intent.putExtra("description",tasks[position].description)
                startActivity(intent)
            }

        })

    }



        fun listButton(view: View) {
            val intent = Intent(this, TaskListActivity::class.java)
            startActivity((intent))

        }

        fun test(view: View) {
            val intent = Intent(this, EditTaskActivity::class.java)
            startActivity((intent))
        }


    }


    class CustomRecyclerAdapter(private val names: List<Task>) :
        RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
        private lateinit var mListener: onItemClickListener

        interface onItemClickListener {
            fun onItemClick(position: Int)
        }

        fun setOnItemClickListener(listener: onItemClickListener) {
            mListener = listener
        }

        class MyViewHolder(itemView: View, listener: onItemClickListener) :
            RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.title)
            val descriprion: TextView = itemView.findViewById(R.id.description)
            val time: TextView = itemView.findViewById(R.id.time)
            val date: TextView = itemView.findViewById(R.id.date)

            init {
                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return MyViewHolder(itemView, mListener)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val current = names[position]
            holder.title.text = current.title
            holder.descriprion.text = current.description
            holder.time.text = current.time
            holder.date.text = current.date
        }

        override fun getItemCount(): Int {
            return names.size
        }
    }
