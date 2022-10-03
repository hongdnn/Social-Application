package com.fpter.core.common.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fpter.core.R

class ContainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        // Inflate fragment only in state create new not in restart(by change orientation, low memory, etc...)
        if (savedInstanceState == null) {
            initFragmentHostFromIntent()
        }
    }

    private fun initFragmentHostFromIntent() {
        val className = intent.getStringExtra(EXTRA_FRAGMENT_CLASS_NAME)
        val bundle = intent.getBundleExtra(EXTRA_FRAGMENT_BUNDLE_ARGS)
        instantiateFragment(className, bundle)
    }

    /**
     * Init fragment to host
     *
     * @param fragmentClassName
     * @param data
     */
    private fun instantiateFragment(fragmentClassName: String?, data: Bundle?) {
        if (fragmentClassName != null) {
//            val fm = this.fragmentManager
//            fm.ins
//            Fragment.instantiate(this, fragmentClassName, data)
    val fragment = Class.forName(fragmentClassName).newInstance()
            val transaction = supportFragmentManager.beginTransaction()
            if (fragment != null) {
                transaction.replace(R.id.frame_container, fragment as Fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }else {
                print("not found fragment")
            }
        } else {
            print("null123")
        }
    }

    companion object {
        private const val EXTRA_FRAGMENT_CLASS_NAME = "fragment_class_name"
        private const val EXTRA_FRAGMENT_BUNDLE_ARGS = "fragment_bundle_args"

        class IntentBuilder(fragment: Fragment) {
            private var fragment: Fragment = fragment
            private var activity: Activity = fragment.requireActivity()
            private lateinit var context: Context
            private var data: Bundle? = null
            private lateinit var fragmentClass: Class<out Fragment>
            private val requestCode = -1
            private val isFinishPreviousActivity = false

            //            constructor(fragment: Fragment) : this(fragment) {
//                activity = fragment.requireActivity()
//            }

            fun setFragmentClass(fragmentClass: Class<out Fragment>): IntentBuilder {
                this.fragmentClass = fragmentClass
                return this
            }

            fun start() {
                val intent = Intent(activity, ContainerActivity::class.java)
                intent.putExtra(EXTRA_FRAGMENT_CLASS_NAME, fragmentClass.name)
                if (data != null) {
                    intent.putExtra(EXTRA_FRAGMENT_BUNDLE_ARGS, data)
                }
                if (requestCode != -1) {
                    if (fragment != null) {
                        fragment.registerForActivityResult(ActivityResultContracts.GetContent()) {

                        }
                    } else {
                        activity.startActivityForResult(intent, requestCode)
                    }
                } else {
                    activity.startActivity(intent)
                }
                if (isFinishPreviousActivity) {
                    activity.finish()
                }
            }
        }


    }


}