package com.example.diaryinmyhand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.diaryinmyhand.databinding.ImageItemBinding

private const val ARG_URI="uri"
class photoFragment : Fragment() {
    private var binding1:ImageItemBinding?=null
    private var uri:String?=null
    // (1) 제일 먼저 호출됨
    companion object {
        @JvmStatic
        // 프래그먼트 생성 <그리고 uri를 받음>
        fun newInstance(uri: String) =
        // apply 호출이 n번되면 n번 photoFragment객체를 만든다.
            // <이미지뷰를 n번 만들 수는 없어도 이미지뷰를 가진 프래그먼트를 n번 만들 수는 있다!!>
            photoFragment().apply {
                // arguments생성 후 uri 문자열 저장
                arguments = Bundle().apply {
                    putString(ARG_URI,uri)
                }
            }
    }
    // 프래그먼트 생성 후 호출되는 메소드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // arguments에 담긴 스트링을 빼내서 프로퍼티 uri에 저장
        arguments?.let {
            uri=it.getString(ARG_URI)
        }
    }
    // 프래그먼트에 표시될 뷰를 생성
    // 액티비티가 아닌 것에서 레리아웃 리소스를 가져오기 위해 inflater를 사용함
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.image_item, container, false)
    }
    // 뷰 생성이 완료되면 호출되는 메소드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // glide에 uri를 통해 이미지를 로딩하고 이미지뷰로 옮긴다.
        val binding=ImageItemBinding.bind(view)
        binding1=binding
        Glide.with(this).load(uri).into(binding.imageView)
    }
}