package com.jongman22.study1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Database
import com.jongman22.study1.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var db: DataBase? = null
    var nameList= mutableListOf<NameData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= DataBase.getInstance(this)
        val savedContacts=db!!.nameDao().getAll()
        if(savedContacts.isNotEmpty()){
            nameList.addAll(savedContacts)
        }

        val adapter=NameAdapter(nameList)
        adapter.setItemClickListener(object:NameAdapter.OnItemClickListener{
            override fun onClick(v:View,position: Int){
                var names=nameList[position]
                db?.nameDao()?.delete(name=names)
                nameList.removeAt(position)
                adapter.notifyDataSetChanged()
            }

        })
        binding.recyclerView.adapter=adapter


        binding.plus.setOnClickListener {
            var name=NameData(
                1,
                binding.nameedit1.text.toString(),
                binding.nameedit2.text.toString()
            )
            db?.nameDao()?.insertAll(name)
            db?.nameDao()?.updateAll(name)
            nameList.add(name)
            adapter.notifyDataSetChanged()
        }
    }
}

