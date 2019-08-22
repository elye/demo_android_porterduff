package com.elyeproj.porterduff

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_root.*

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        button_manual_porter_duff.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        button_animate_porter_duff.setOnClickListener {
            startActivity(Intent(this, AutomateActivity::class.java))
        }
    }
}
