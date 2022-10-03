package com.fpter.wetalk.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fpter.wetalk.R
import com.fpter.wetalk.databinding.ActivityHomeBinding
import com.fpter.wetalk.features.contact.ContactFragment
import com.fpter.wetalk.features.message.MessageFragment
import com.fpter.wetalk.features.setting.SettingFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initBottomNavigation()
        loadFragment(MessageFragment())
    }

    private fun initBottomNavigation(){
        binding.bottomNavView.setOnClickListener { item ->
            val fragment: Fragment
            when (item.id) {
                R.id.actionBottomMessage -> {
                    binding.toolbar.tvActionTitle.text = "Message"
                    fragment = MessageFragment()
                    loadFragment(fragment)
                }
                R.id.actionBottomContact -> {
                    binding.toolbar.tvActionTitle.text = "Contact"
                    fragment = ContactFragment()
                    loadFragment(fragment)
                }
                R.id.actionBottomSetting -> {
                    binding.toolbar.tvActionTitle.text = "Setting"
                    fragment = SettingFragment()
                    loadFragment(fragment)
                }
            }
        }
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}