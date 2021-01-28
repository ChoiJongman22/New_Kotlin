package com.jongman22.room_viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jongman22.room_viewmodel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //뷰모델 객체는 직접적으로 초기화하는것이 아니라 안드로이드 시스템을 통해 생성한다.
    //시스템에서는 만약 이미 생성된 ViewModel 인스턴스가 있다면 이를 반환할 것이므로 메모리 낭비 줄여준다.
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //람다식으로 실행하기
        val adapter = ContactAdapter({ contact ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, contact.name)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, contact.number)
            intent.putExtra(AddActivity.EXTRA_CONTACT_ID, contact.id)
        }, { contact ->
            deleteDialog(contact)
        })

        val lm = LinearLayoutManager(this)
        binding.mainRecyclerview.adapter = adapter
        binding.mainRecyclerview.layoutManager = lm
        binding.mainRecyclerview.setHasFixedSize(true)




        contactViewModel=ViewModelProvider(viewModelStore,ContactViewModelFactory(application)).get(
            ContactViewModel::class.java
        )
        //Observer를 만들어서 뷰모델이 어느 액티비티/프래그먼트의 생명주기를 관찰할 것인지 정한다.
        //이 액티비티가 파괴되는 시점에 시스템에서 뷰모델도 자동으로 파괴한다.
        //Observer에너느 onChanged메소드를 갖고 있어서 LiveData가 변하면 무엇을 할 것인지 액션을 지정한다.

        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            //Update UI
            adapter.setContacts(contacts!!)

        })
        binding.mainButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }
}