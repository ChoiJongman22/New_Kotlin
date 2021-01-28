package com.jongman22.room_viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jongman22.room_viewmodel.databinding.ItemContactBinding

class ContactAdapter(
    // 람다식
    // 파라미터 -> 함수
    //위와 같은 식으로 선언
    //파라미터(변수생성) -> 함수 작동이렇게!

    //(Contact) -> Unit까지가 하나의 파라미터, 즉 ()->Unit의 의미는 파라미터가 없고 아무것도 반환하지 않음.
    val contactItemClick: (Contact) -> Unit,
    val contactItemLongClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var contacts: List<Contact> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ContactViewHolder(ItemContactBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ContactViewHolder(val binding:ItemContactBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(contact:Contact){
            binding.itemTvName.text=contact.name
            binding.itemTvNumber.text=contact.number
            binding.itemTvInitial.text=contact.initial.toString()

            itemView.setOnClickListener{
                contactItemClick(contact)
            }
            itemView.setOnLongClickListener {
                contactItemLongClick(contact)
                true
            }
        }

    }
    fun setContacts(contacts:List<Contact>){
        this.contacts=contacts
        notifyDataSetChanged()
    }
}
