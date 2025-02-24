package kz.alseco.views.alert

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kz.alseco.theme.px

class AlertView(context: Context?) : LinearLayout(context) {
    init {
        id = generateViewId()
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        isFocusable = false
    }

    private val DEFAULT_VALUE = -100000

    fun setIcon(icon: Drawable?, colorFilter: Int) {
        icon?.let {
            val ivIcon = ImageView(context)
            ivIcon.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 0f
            }
            ivIcon.setImageDrawable(it)
            ivIcon.isClickable = false
            if (colorFilter != DEFAULT_VALUE) ivIcon.setColorFilter(colorFilter)
            addView(ivIcon, 0)
        }
    }

    fun setTextContent(description: String, messageColor: Int) {
        // Title and description
        val textLayout = LinearLayout(context)
        val textLayoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            weight = 1f
        }
        textLayout.layoutParams = textLayoutParams
        textLayout.orientation = VERTICAL

        // Description
        if (!description.isEmpty()) {
            val tvMessage = TextView(context)
            tvMessage.layoutParams = textLayoutParams
            tvMessage.gravity = Gravity.TOP
            tvMessage.text = description
            tvMessage.isClickable = false
            tvMessage.setPadding(18.px, 0, 0,  0) // Top padding only if there is message
            //tvMessage.setTextAppearance(R.style.body_opensans_14)
            tvMessage.setTextColor(messageColor)
            textLayout.addView(tvMessage)
        }
        addView(textLayout)
    }

    fun setCloseIcon(icon: Drawable?, colorFilter: Int) {
        icon?.let {
            val ivIcon = ImageView(context)
            ivIcon.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                weight = 0f
            }
            ivIcon.setImageDrawable(it)
            ivIcon.isClickable = false
            if (colorFilter != DEFAULT_VALUE) ivIcon.setColorFilter(colorFilter)
            addView(ivIcon)
        }
    }

    fun setBackground(color: Int, cornerRadius: Int, colorStroke: Int) {
        background = customView(color, cornerRadius, colorStroke)
    }

    fun customView(backgroundColor: Int, cornerRadius: Int, colorStroke: Int): GradientDrawable {
        val radiusInDP = cornerRadius.toFloat()
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadii = floatArrayOf(radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP)
            setStroke(2.px, colorStroke)
            setColor(backgroundColor)
        }
    }

    fun setCustomView(view: View) {
        addView(view, 0)
    }
}