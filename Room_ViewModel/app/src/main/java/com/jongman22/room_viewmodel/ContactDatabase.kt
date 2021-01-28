package com.jongman22.room_viewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// @Database 어노테이션을 해주고 entity를 정의해준다음 버전도 정의해준다.
// 데이터베이스 인스턴스를 싱글톤으로 사용하기 위해 companion object로 해줬다.
// class안에 형성할 수 있는 인스턴스들을 오브젝트라고 한다.
// 디저트라는 클래스에는 디저뜨와, 마카롱같은 오브젝트가 있는 걸 말한다. 오브젝트는 인스턴스다.
// 인스턴스는 구체적인 것이라는 의미가 있다.
// 소프트웨어 디자인 패턴에서 싱글톤 패턴이라는 것은 생성자가 여러차례 호출되더라도 실제로 생성되는 객체는 하나.
// 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 객체를 리턴한다.

@Database(entities = [Contact::class], version = 1)
//RoomDB를 상송하는 추상클래스
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    //동반자 객체는 클래스 인스턴스 없이 어떤 클래스 내부에 접근하고 싶을 때 사용하는 것입니다.
    companion object {
        //데이터베이스 인스턴스 생성
        private var INSTANCE: ContactDatabase? = null
        fun getInstance(context: Context): ContactDatabase? {
            //예외처리
            if (INSTANCE == null) {
                //여러 스레드가 접근하지 못하도록 synchronized 해준다.
                synchronized(ContactDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "contact"
                    )
                        .fallbackToDestructiveMigration()
                        //DB갱신될때 기존의 테이블을 버리고 새로 사용하는 것. Migration
                        .build()
                }
            }
            //널 아니면 INSTANCE반환
            return INSTANCE
        }
    }
}