package com.elyeproj.porterduff

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.CompoundButton
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val radioList by lazy {
        listOf(
            radio_clear, radio_darken, radio_destination, radio_destination_atop, radio_destination_in,
            radio_destination_out, radio_destination_over, radio_lighten, radio_multiply, radio_overlay,
            radio_screen, radio_source, radio_source_atop, radio_source_in, radio_source_out,
            radio_source_over, radio_add, radio_xor
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listener: CompoundButton.OnCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (!isChecked) return@OnCheckedChangeListener
                when (buttonView.id) {
                    radio_clear.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.CLEAR
                        text_porter_duff.text = "Clear"
                    }
                    radio_darken.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.DARKEN
                        text_porter_duff.text = "Darken"
                    }
                    radio_destination.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.DST
                        text_porter_duff.text = "Destination"
                    }
                    radio_destination_atop.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.DST_ATOP
                        text_porter_duff.text = "Destination Atop"
                    }
                    radio_source_atop.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.SRC_ATOP
                        text_porter_duff.text = "Source Atop"
                    }
                    radio_destination_in.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.DST_IN
                        text_porter_duff.text = "Destination In"
                    }
                    radio_destination_out.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.DST_OUT
                        text_porter_duff.text = "Destination Out"
                    }
                    radio_destination_over.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.DST_OVER
                        text_porter_duff.text = "Destination Over"
                    }
                    radio_lighten.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.LIGHTEN
                        text_porter_duff.text = "Lighten"
                    }
                    radio_multiply.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.MULTIPLY
                        text_porter_duff.text = "Multiply"
                    }
                    radio_overlay.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.OVERLAY
                        text_porter_duff.text = "Overlay"
                    }
                    radio_screen.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.SCREEN
                        text_porter_duff.text = "Screen"
                    }
                    radio_source.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.SRC
                        text_porter_duff.text = "Source"
                    }
                    radio_source_in.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.SRC_IN
                        text_porter_duff.text = "Source In"
                    }
                    radio_source_out.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.SRC_OUT
                        text_porter_duff.text = "Source Out"
                    }
                    radio_source_over.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.SRC_OVER
                        text_porter_duff.text = "Source Over"
                    }
                    radio_add.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.ADD
                        text_porter_duff.text = "Add"
                    }
                    radio_xor.id -> if (isChecked) {
                        draw_porter_duff.mode = PorterDuff.Mode.XOR
                        text_porter_duff.text = "Exclusive Or"
                    }
                }
                uncheckedAllExcept(buttonView as RadioButton)
            }

        radioList.forEach {
            it.setOnCheckedChangeListener(listener)
        }
    }

    private fun uncheckedAllExcept(radioButton: RadioButton) {
        radioList.filter{ it != radioButton }.forEach { it.isChecked = false }
    }
}
