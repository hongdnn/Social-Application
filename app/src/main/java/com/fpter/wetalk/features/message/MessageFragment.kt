package com.fpter.wetalk.features.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fpter.wetalk.R
import com.fpter.wetalk.databinding.FragmentMessageBinding
import com.fpter.wetalk.features.messagedetail.MessageDetailFragment


class MessageFragment : Fragment() {
    private lateinit var binding: FragmentMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTest.setOnClickListener {
            MessageDetailFragment.showMe(this)
        }
    }

}