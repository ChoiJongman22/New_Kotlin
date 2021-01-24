package com.jongman22.coffee

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.jongman22.coffee.databinding.ActivityGiftBinding

class GiftActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGiftBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGiftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.coffee.setOnClickListener {
            it.setBackgroundColor(R.color.purple_500)
            val intent=Intent(this,CafeActivity2::class.java)
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("선물하기")
                .setMessage("선물하시겠습니까?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                })
                .create()
                .show()


        }
    }
}