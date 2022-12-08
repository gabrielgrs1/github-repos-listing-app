package com.gabrielgrs2.listrepos.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabrielgrs2.listrepos.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            R.anim.activity_slide_start_enter,
            R.anim.activity_scale_start_exit
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.activity_scale_finish_enter,
            R.anim.activity_slide_finish_exit
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
