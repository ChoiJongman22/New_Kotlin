package com.jongman22.dmh_study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jongman22.dmh_study.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private var catDb : CatDB? = null
    private lateinit var binding:ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        catDb = CatDB.getInstance(this)

        /* 새로운 cat 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
        val addRunnable = Runnable {
            val newCat = Cat()
            newCat.catName = binding.addname.text.toString()
            newCat.lifeSpan = binding.addLifeSpan.text.toString().toInt()
            newCat.origin = binding.addOrigin.text.toString()
            catDb?.catDao()?.insert(newCat)
        }

        binding.addbtn.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        CatDB.destroyInstance()
        super.onDestroy()
    }
}