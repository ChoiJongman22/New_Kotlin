package com.example.idmemolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.idmemolist.databinding.ActivityAppPassWordBinding

class AppPassWordMain : AppCompatActivity() {
    var lock = true//잠금 상태 여부 확인
    private lateinit var binding: ActivityAppPassWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppPassWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.ENABLE_PASSLOCK)
            }
            startActivityForResult(intent, AppLockConst.ENABLE_PASSLOCK)
        }


        binding.btnSetLock.setOnClickListener {
            val intent = Intent(this, AppLockDial::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.ENABLE_PASSLOCK)
            }
            startActivityForResult(intent, AppLockConst.ENABLE_PASSLOCK)
        }
        binding.btnSetDelLock.setOnClickListener {
            val intent = Intent(this, AppLockDial::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.DISABLE_PASSLOCK)
            }
            startActivityForResult(intent, AppLockConst.DISABLE_PASSLOCK)
        }
        binding.btnChangePwd.setOnClickListener {
            val intent = Intent(this, AppLockDial::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.CHANGE_PASSWORD)
            }
            startActivityForResult(intent, AppLockConst.CHANGE_PASSWORD)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            AppLockConst.ENABLE_PASSLOCK ->
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "암호 설정 됨", Toast.LENGTH_SHORT).show()
                    init()
                    lock = false
                }
            AppLockConst.DISABLE_PASSLOCK ->
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "암호 삭제 됨", Toast.LENGTH_SHORT).show()
                    init()
                }
            AppLockConst.CHANGE_PASSWORD ->
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "암호 변경 됨", Toast.LENGTH_SHORT).show()
                    lock = false
                }
            AppLockConst.UNLOCK_PASSWORD -> {
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "잠금 해제 됨", Toast.LENGTH_SHORT).show()
                    lock = false
                }
            }

        }
    }
    override fun onStart() {
        super.onStart()
        if (lock && AppLock(this).isPassLockSet()) {
            val intent = Intent(this, AppLockDial::class.java).apply {
                putExtra(AppLockConst.type, AppLockConst.UNLOCK_PASSWORD)
            }
            startActivityForResult(intent, AppLockConst.UNLOCK_PASSWORD)
        }
    }


    override fun onPause() {
        super.onPause()
        if (AppLock(this).isPassLockSet()) {
            lock = true // 잠금으로 변경
        }
    }

    //버튼 비활성화
    private fun init() {
        if (AppLock(this).isPassLockSet()) {
            binding.btnSetLock.isEnabled = false
            binding.btnSetDelLock.isEnabled = true
            binding.btnChangePwd.isEnabled = true
            lock = true
        } else {
            binding.btnSetLock.isEnabled = true
            binding.btnSetDelLock.isEnabled = false
            binding.btnChangePwd.isEnabled = false
            lock = false
        }
    }
}