package com.elyeproj.porterduff

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class AnimateDrawPorterDuffView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : DrawPorterDuffView(context, attrs, defStyleAttr, defStyleRes) {
    private val compositeDisposable = CompositeDisposable()

    private val animationPorterDuff by lazy {
        listOf(
            PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.DARKEN,
            PorterDuff.Mode.OVERLAY,
            PorterDuff.Mode.ADD,
            PorterDuff.Mode.SCREEN,
            PorterDuff.Mode.LIGHTEN,
            PorterDuff.Mode.DST_OVER
        )
    }
    private var control = 1

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        stopAnimate()
        startAnimate()
    }

    private fun startAnimate() {
        compositeDisposable.add(Observable
            .interval(0, 1, TimeUnit.SECONDS)
            .flatMap {
                return@flatMap Observable
                    .interval(0, 100, TimeUnit.MILLISECONDS)
                    .take(animationPorterDuff.size.toLong())
            }
            .map { it.toInt() }
            .doAfterNext { if (it == (animationPorterDuff.size - 1)) control *= -1 }
            .map { if (control == 1) it else animationPorterDuff.size - 1 - it }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mode = animationPorterDuff[it] })
    }

    private fun stopAnimate() {
        compositeDisposable.clear()
    }

    fun stop() = stopAnimate()
}
