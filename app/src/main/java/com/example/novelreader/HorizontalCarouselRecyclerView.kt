package com.example.novelreader

import android.content.Context
import android.graphics.ColorMatrix
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import kotlin.math.pow


class HorizontalCarouselRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    private val activeColor
            by lazy { ContextCompat.getColor(context, R.color.colorPrimaryDark) }
    private val inactiveColor
            by lazy { ContextCompat.getColor(context, R.color.colorBackground) }
    private var viewsToChangeColor = listOf<Int>()

    fun <T : ViewHolder> initialize(newAdapter: Adapter<T>) {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        Log.d("debug","initialize function invoked")
        newAdapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                post {
                    Log.d("debug","onChanged invoked")
                    val sidePadding = (width/2) - (getChildAt(0).width/2)
                    setPadding(sidePadding,0,sidePadding,0)
                    addOnScrollListener(object: OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                onScrollChanged()
                        }
                    })
                }
            }
        })
        adapter = newAdapter
    }

    private fun onScrollChanged() {
        post {
            Log.d("debug","test")
            (0 until childCount).forEach {position ->
                val child = getChildAt(position)
                val childCenterX = (child.left + child.right) / 2
                val scaleValue = getGaussianScale(childCenterX, 0.85f, 0.2f, 250.toDouble())
                child.scaleX = scaleValue
                child.scaleY = scaleValue
                //child.y = child.y-scaleValue.toInt()
                colorView(child, scaleValue)
            }
        }
    }

    private fun getGaussianScale(
        childCenterX: Int,
        minScaleOffest: Float,
        scaleFactor: Float,
        spreadFactor: Double
    ): Float {
        val recyclerCenterX = (left + right) / 2
        return (Math.E.pow(-(childCenterX - recyclerCenterX.toDouble()).pow(2.toDouble()) / (2 * spreadFactor.pow(2.toDouble()))
        ) * scaleFactor + minScaleOffest).toFloat()
    }

    fun setViewsToChangeColor(viewIds: List<Int>) {
        viewsToChangeColor = viewIds
    }

    private fun colorView(child: View, scaleValue: Float) {
        val saturationPercent  = (scaleValue - 1) / 1f
        val alphaPercent = scaleValue / 1f
        val matrix = ColorMatrix()
        matrix.setSaturation(saturationPercent)

        viewsToChangeColor.forEach { viewId ->
            when (val viewToChangeColor = child.findViewById<View>(viewId)) {
                is ImageView -> {
                    //viewToChangeColor.colorFilter = ColorMatrixColorFilter(matrix)
                    //viewToChangeColor.imageAlpha = (255 * alphaPercent).toInt()
                }
                is TextView -> {
                val textColor = ArgbEvaluator().evaluate(saturationPercent, inactiveColor, activeColor) as Int
                viewToChangeColor.setTextColor(textColor)
            }
        }

        }
    }

}