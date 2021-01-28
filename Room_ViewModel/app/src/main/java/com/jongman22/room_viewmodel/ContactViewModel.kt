package com.jongman22.room_viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContactViewModelFactory(private val myApplication: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java).newInstance(myApplication)
    }
}

class ContactViewModel(application: Application) : ViewModel() {
    //repository 함수를 사용할 수 있도록 함.
    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()


    fun getAll(): LiveData<List<Contact>>{
        return this.contacts
    }

    fun insert(contact: Contact){
        repository.insert(contact)
    }

    fun delete(contact: Contact){
        repository.delete(contact)
    }


}