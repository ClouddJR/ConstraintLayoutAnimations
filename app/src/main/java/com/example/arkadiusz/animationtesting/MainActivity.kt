package com.example.arkadiusz.animationtesting

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import kotlinx.android.synthetic.main.summary.*

class MainActivity : AppCompatActivity() {

    var isShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summary)
        initOnBackgroundClick()
    }

    private fun initOnBackgroundClick() {
        imageView.setOnClickListener {
            isShown = if (!isShown) {
                val constraintSet = getDetailConstraint()
                val transition = getTransition()
                showTransition(transition)
                constraintSet.applyTo(constraint)
                true
            } else {
                val constraintSet = getSummaryConstraint()
                val transition = getTransition()
                showTransition(transition)
                constraintSet.applyTo(constraint)
                false
            }
        }
    }

    private fun getDetailConstraint(): ConstraintSet {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.detail)
        return constraintSet
    }

    private fun getSummaryConstraint(): ConstraintSet {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.summary)
        return constraintSet
    }

    private fun getTransition(): Transition {
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1f)
        transition.duration = 1000
        return transition
    }

    private fun showTransition(transition: Transition) {
        TransitionManager.beginDelayedTransition(constraint, transition)
    }

}
