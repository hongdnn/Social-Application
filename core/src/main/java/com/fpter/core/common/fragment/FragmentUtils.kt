package com.fpter.core.common.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.fpter.core.R

class FragmentUtils {
    companion object {
        fun replaceFragment(containerId: Int, fragment: Fragment, supportFragmentManager: FragmentManager, animation: Boolean) {
            val transaction = supportFragmentManager.beginTransaction()
//            if(animation) {
//                transaction.setCustomAnimations(
//                    R.anim.
//                )
//            }
            transaction.replace(containerId, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}