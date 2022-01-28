package com.target.targetcasestudy.ui.helpers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt

class CircleTextSpan(private val backgroundColor: Int, private val textColor: Int, private val padding: Int) : ReplacementSpan() {
  override fun getSize(
    paint: Paint,
    text: CharSequence,
    start: Int,
    end: Int,
    fontMetricsInt: FontMetricsInt?
  ): Int {
    return measureWidth(paint, text, start, end).roundToInt()
  }

  override fun draw(
    canvas: Canvas,
    text: CharSequence,
    start: Int,
    end: Int,
    x: Float,
    top: Int,
    y: Int,
    bottom: Int,
    paint: Paint
  ) {
    paint.color = backgroundColor
    val size = measureWidth(paint, text, start, end)
    canvas.drawCircle(x + size / 2, ((bottom - top) / 2).toFloat(), size , paint)
    paint.color = textColor
    canvas.drawText(text, start, end, x + padding, y.toFloat(), paint)
  }

  private fun measureWidth(paint: Paint, text: CharSequence, start: Int, end: Int): Float {
    return paint.measureText(text, start, end) + 2 * padding
  }
}