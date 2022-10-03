package com.fpter.wetalk.features.messagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fpter.core.common.activity.ContainerActivity
import com.fpter.wetalk.databinding.FragmentMessageDetailBinding


class MessageDetailFragment : Fragment() {
    private lateinit var binding: FragmentMessageDetailBinding

    companion object {
        fun showMe(fragment: Fragment) {
            val intentBuilder = ContainerActivity.Companion.IntentBuilder(fragment)
            intentBuilder.setFragmentClass(MessageDetailFragment::class.java)
            intentBuilder.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}