package com.example.idmemolist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.idmemolist.databinding.ActivityAppLockFunctionBinding

class AppLockDial : AppCompatActivity() {
    private lateinit var binding: ActivityAppLockFunctionBinding
    private var oldPwd = ""
    private var changePwdUnlock = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppLockFunctionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttonArray = arrayListOf<Button>(
            binding.btn0,
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9,
            binding.btnclear,
            binding.btnErase
        )
        for (button in buttonArray) {
            button.setOnClickListener(btnListner)
        }
    }

    //버튼 클릭 했을때
    private val btnListner = View.OnClickListener { view ->
        var currentValue = -1
        when (view.id) {
            R.id.btn0 -> currentValue = 0
            R.id.btn1 -> currentValue = 1
            R.id.btn2 -> currentValue = 2
            R.id.btn3 -> currentValue = 3
            R.id.btn4 -> currentValue = 4
            R.id.btn5 -> currentValue = 5
            R.id.btn6 -> currentValue = 6
            R.id.btn7 -> currentValue = 7
            R.id.btn8 -> currentValue = 8
            R.id.btn9 -> currentValue = 9
            R.id.btnclear -> onClear()
            R.id.btnErase -> onDeleteKey()
        }
        val strCurrentValue = currentValue.toString()
        if (currentValue != -1) {
            when {
                binding.PassCode1.isFocused -> {
                    setEditText(binding.PassCode1,binding.PassCode2,strCurrentValue)
                }
                binding.PassCode2.isFocused->{
                    setEditText(binding.PassCode2,binding.PassCode3,strCurrentValue)
                }
                binding.PassCode3.isFocused->{
                    setEditText(binding.PassCode3,binding.PassCode4,strCurrentValue)
                }
                binding.PassCode4.isFocused->{
                    binding.PassCode4.setText(strCurrentValue)
                }
            }
        }
        if(binding.PassCode4.text.isNotEmpty()&&binding.PassCode3.text.isNotEmpty()&&binding.PassCode2.text.isNotEmpty()&&binding.PassCode1.text.isNotEmpty()){
            inputType(intent.getIntExtra("type",0))
        }
    }

    private fun onDeleteKey() {
        when {
            binding.PassCode1.isFocused -> {
                binding.PassCode1.setText("")
            }
            binding.PassCode2.isFocused -> {
                binding.PassCode1.setText("")
                binding.PassCode1.requestFocus()
            }
            binding.PassCode3.isFocused -> {
                binding.PassCode2.setText("")
                binding.PassCode2.requestFocus()
            }
            binding.PassCode4.isFocused -> {
                binding.PassCode3.setText("")
                binding.PassCode3.requestFocus()
            }
        }
    }

    //모두 지우기
    private fun onClear() {
        binding.PassCode1.setText("")
        binding.PassCode2.setText("")
        binding.PassCode3.setText("")
        binding.PassCode4.setText("")
        binding.PassCode1.requestFocus()
    }

    //입력된 비밀번호 합치기
    private fun inputedPassword(): String {
        return "${binding.PassCode1.text}${binding.PassCode2.text}${binding.PassCode3.text}${binding.PassCode4.text}"
    }

    //EditText 설정
    private fun setEditText(
        currentEditText: EditText,
        nextSetEditText: EditText,
        strCurrentValue: String
    ) {
        currentEditText.setText(strCurrentValue)
        nextSetEditText.requestFocus()
        nextSetEditText.setText("")
    }

    //IntentType분류
    private fun inputType(type: Int) {
        when (type) {
            AppLockConst.ENABLE_PASSLOCK -> {
                if (oldPwd.isEmpty()) {
                    oldPwd = inputedPassword()
                    onClear()
                    binding.InputInfo.text = "다시 입력해주세요."

                } else {
                    if (oldPwd == inputedPassword()) {
                        AppLock(this).setPassLock(inputedPassword())
                        setResult(Activity.RESULT_OK)
                        finish()
                    } else {
                        onClear()
                        oldPwd = ""
                        binding.InputInfo.text="비밀번호 입력"
                    }
                }
            }
            AppLockConst.DISABLE_PASSLOCK->{
                if(AppLock(this).isPassLockSet()){
                    if(AppLock(this).checkPassLock(inputedPassword())){
                        AppLock(this).removePassLock()
                        setResult(Activity.RESULT_OK)
                        finish()
                    }else{
                        binding.InputInfo.text="비밀번호가 다릅니다."
                        onClear()
                    }
                }
                else{
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                }
            }
            AppLockConst.UNLOCK_PASSWORD->{
                if(AppLock(this).checkPassLock(inputedPassword())){
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                else{
                    binding.InputInfo.text="비밀번호가 틀립니다."
                    onClear()
                }
            }
            AppLockConst.CHANGE_PASSWORD->{
                if(AppLock(this).checkPassLock(inputedPassword())&&!changePwdUnlock){
                    onClear()
                    changePwdUnlock=true
                    binding.InputInfo.text="새로운 비밀번호 입력"
                }
                else if(changePwdUnlock){
                    if(oldPwd.isEmpty()){
                        oldPwd=inputedPassword()
                        onClear()
                        binding.InputInfo.text="새로운 비밀번호 다시 입력"
                    }else{
                        if(oldPwd==inputedPassword()){
                            AppLock(this).setPassLock(inputedPassword())
                            setResult(Activity.RESULT_OK)
                            finish()
                        }else{
                            onClear()
                            oldPwd=""
                            binding.InputInfo.text="현재 비밀번호 다시 입력"
                            changePwdUnlock=false
                        }
                    }
                }
                else{
                    binding.InputInfo.text="비밀번호가 틀립니다."
                    changePwdUnlock=false
                    onClear()
                }
            }
        }
    }
}