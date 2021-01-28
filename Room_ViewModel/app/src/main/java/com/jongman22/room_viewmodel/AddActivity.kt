package com.jongman22.room_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jongman22.room_viewmodel.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    //AddActivity에서 intent extra로 사용할 상수를 만들고 Viewmodel객체를 만든다.
    //intent가 null이 아니고 extra에 주소록 정보가 모두 들어있다면 EditText와 id값을 지정한다.
    //MainActivity에서 ADD 버튼을 눌렀을 때에는 신규 추가이므로 인텐트가 없고, RecyclerView item 을 눌렀을 때에는 편집을 할 때에는 해당하는 정보를 불러오기 위해 인텐트 값을 불러올 것이다.
    //DONE버튼을 통해 EditText의 null을 체크한 후 ViewModel을 통해 insert해주고 MainActivity로 돌아간다.

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null
    private lateinit var binding:ActivityAddBinding
    //동반자 객체로 각 변수들은 선언한다.
    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactViewModel=ViewModelProvider(viewModelStore,ContactViewModelFactory(application)).get(
            ContactViewModel::class.java
        )

        //널 아니고 주소록 정보가 모두 들어있다면
        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(
                EXTRA_CONTACT_NUMBER
            ) && intent.hasExtra(
                EXTRA_CONTACT_ID
            )){
            binding.addEdittextName.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            binding.addEdittextNumber.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)

        }

        binding.addButton.setOnClickListener {
            val name=binding.addEdittextName.text.toString().trim()
            val number=binding.addEdittextNumber.text.toString()
            if(name.isEmpty()||number.isEmpty()){
                Toast.makeText(this,"Please enter name and number",Toast.LENGTH_SHORT).show()
            }else{
                val initial=name[0].toUpperCase()
                val contact=Contact(id,name,number,initial)
                contactViewModel.insert(contact)
                finish()
            }
        }


    }
}