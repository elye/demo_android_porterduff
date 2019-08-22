package com.elyeproj.porterduff

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
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
        compositeDisposable.add(Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMap {
                return@flatMap Observable.create<Int> { emitter ->
                    if (control == 1) {
                        for (x in 0 until animationPorterDuff.size) {
                            emitAndWait(emitter, x)
                        }
                    } else {
                        for (x in (animationPorterDuff.size - 1) downTo 0) {
                            emitAndWait(emitter, x)
                        }
                    }
                    control *= -1
                    emitter.onComplete()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ mode = animationPorterDuff[it] }, {} ))
    }

    private fun emitAndWait(emitter: ObservableEmitter<Int>, x: Int) {
        emitter.onNext(x)
        try {
            Thread.sleep(100)
        } catch (e: Exception) {
            // On interrupted, don't care
        }
    }

    private fun stopAnimate() {
        compositeDisposable.clear()
    }

    fun stop() = stopAnimate()
}
