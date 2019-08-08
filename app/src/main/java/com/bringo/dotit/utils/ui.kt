package com.bringo.dotit.utils

import android.animation.ValueAnimator
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.animation.doOnEnd


    fun handleButtonLoading(progressBar:ProgressBar, btn: Button) {
        ValueAnimator.ofInt(210, 350).apply {
            duration = 100
            addUpdateListener { updatedAnimation ->
                var pad=updatedAnimation.animatedValue as Int
                btn.setPadding(pad,0,pad,0)
            }
            start()
        }.doOnEnd { progressBar.visibility= ProgressBar.VISIBLE; btn.isEnabled=false }
    }
    fun handleButtonFinishedLoading(progressBar:ProgressBar, btn: Button) {
    ValueAnimator.ofInt(350, 210).apply {
        duration = 100
        addUpdateListener { updatedAnimation ->
            var pad=updatedAnimation.animatedValue as Int
            btn.setPadding(pad,0,pad,0)
        }
        start()
    }.doOnEnd { progressBar.visibility= ProgressBar.GONE; btn.isEnabled=true }
    }

    fun showProgressBar(progressBar: ProgressBar){
        progressBar.visibility= View.VISIBLE
    }
    fun hideProgressBar(progressBar: ProgressBar){
        progressBar.visibility= View.GONE
    }
