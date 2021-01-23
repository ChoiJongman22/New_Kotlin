package com.jongman22.study1

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.jongman22.study1.databinding.ActivityIntentStudyBinding

class IntentStudy : AppCompatActivity() {
    private lateinit var binding: ActivityIntentStudyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentStudyBinding.inflate(layoutInflater)

        setContentView(binding.root)


        // 이건 전화번호부 할 때 사용하면 될 거 같음.
        val intent = Intent(Intent.ACTION_DIAL)
        val TEST_DIAL_NUMBER = Uri.fromParts("tel", "95732463", null)
        intent.data = TEST_DIAL_NUMBER
        startActivity(intent)

        //암시적 인텐트도 ActivityManager가 인텐트의 의도에 맞는 적당한 액티비티를 찾아서
        /*실행해준다. ActivityManager는 PackageManager에 ResolveActivity API를 호출하여
        가장 적합한 액티비티가 어떤것인지 물어보는데 이것이 resolving이다.*/


        /*binding.quit.setOnClickListener {

            val alertDialog = AlertDialog.Builder(this)
                .setTitle("AlertDialog1")
                .setMessage("Do you want to leave?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                })
                .create()
                .show()
        }*/
    }
}