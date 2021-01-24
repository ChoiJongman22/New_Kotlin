package com.example.diaryinmyhand

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diaryinmyhand.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var Datadb:DataDB?=null
    private var dataList= listOf<Data>()
    lateinit var mAdapter: DiaryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Datadb= DataDB.getInstance(this)
        mAdapter= DiaryAdapter(this,dataList)

        val r = Runnable {
            try{
                dataList=Datadb?.dataDao()?.getAll()!!
                mAdapter = DiaryAdapter(this, dataList)
                mAdapter.notifyDataSetChanged()

                binding.recyclerView.adapter = mAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.setHasFixedSize(true)
            }catch (e:Exception){
                Log.d("tag","Error - $e")
            }
        }

        val thread = Thread(r)
        thread.start()

        binding.Settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.Calendar.setOnClickListener {
            val intent1 = Intent(this, CalendarActivity::class.java)
            startActivity(intent1)
        }

        binding.Plus.setOnClickListener {
            val i = Intent(this, DiaryWriting::class.java)
            startActivity(i)
            finish()
        }

    }

    override fun onDestroy() {
        DataDB.destroyInstance()
        Datadb = null
        super.onDestroy()
    }
}

