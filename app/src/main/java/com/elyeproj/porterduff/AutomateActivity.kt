package com.elyeproj.porterduff

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_automate.*

class AutomateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automate)
    }

    override fun onPause() {
        super.onPause()
        animate_draw_porter_duff_dragon.stop()
        animate_draw_porter_duff_angel.stop()
    }
}
