package com.jongman22.room_viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

//DB인스턴스를 Repository에서 호출하여 사용할 것이다.
class ContactRepository(application: Application) {
    //인스턴스 호출
    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    //연락처 삽입함수수
   fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.insert(contact)
            })
            thread.start()
        }catch (e:Exception){}
    }

    fun delete(contact: Contact){
        try{
            val thread=Thread(Runnable {
                contactDao.delete(contact)
            })
            thread.start()
        }catch (e:Exception){}
    }

}