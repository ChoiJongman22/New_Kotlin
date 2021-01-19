package com.jongman22.basicstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.jongman22.basicstudy.databinding.ActivityMain2Binding
import java.util.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    val TAG = "MainActivity2"
    var db: AppDatabase? = null
    var nameList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)
        val savedContacts = db!!.userDao().getAll()
        if (savedContacts.isNotEmpty()) {
            nameList.addAll(savedContacts)
        }

        val adapter = DataReAdapter(nameList)
        adapter.setItemClickListener(object : DataReAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val users = nameList[position]
                db?.userDao()?.delete(user = users)
                nameList.removeAt(position)
                adapter.notifyDataSetChanged()
            }
        })
        binding.mRecyclerView.adapter=adapter



        binding.mPlusButton.setOnClickListener {
            val name=User(
                1,
                binding.name1Edit.text.toString(),
                binding.name2Edit.text.toString()
            )
            db?.userDao()?.insertAll(name)
            nameList.add(name)
            adapter.notifyDataSetChanged()


        }

    }

}