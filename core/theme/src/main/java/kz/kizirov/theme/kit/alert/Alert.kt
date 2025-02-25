package kz.kizirov.theme.kit.alert

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Insets
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.LinearLayout
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import kz.alseco.theme.colorAccentBlue
import kz.alseco.theme.colorGreen
import kz.alseco.theme.colorRed
import kz.alseco.theme.colorTextPrimary
import kz.alseco.theme.colorWhite
import kz.kizirov.theme.R


class Alert(private var context: Context) : View.OnClickListener, LifecycleObserver {

    private var mIconDrawable: Drawable? = null
    private var mIconCloseDrawable: Drawable? = null
    private val sneakerView by lazy { AlertView(context) }
    private val DEFAULT_VALUE = -100000
    private var mBackgroundColor = DEFAULT_VALUE
    private var mBackgroundBorderColor = DEFAULT_VALUE
    private var mIconColorFilterColor = DEFAULT_VALUE
    private var mIconSize = 24
    private var mMessage = ""
    private var mMessageColor = DEFAULT_VALUE
    private var mAutoHide = true
    private var mDuration = 5000
    private var mCornerRadius = DEFAULT_VALUE
    private var mMargin = DEFAULT_VALUE
    private var targetView: ViewGroup? = null
    private var isActivity: Boolean = false
    private var mDismissListener:(() -> Unit)? = null
    companion object {

        /**
         * Create Sneaker instance
         *
         * @param activity
         * @return Sneaker instance
         */
        @JvmStatic
        fun with(activity: Activity): Alert {
            return Alert(activity).also {
                it.setTargetView(activity)
            }
        }

        /**
         * Create Sneaker instance
         *
         * @param fragment
         * @return Sneaker instance
         */
       /* @JvmStatic
        fun with(fragment: Fragment): Alert {
            return Alert(fragment.requireContext()).also {
                it.setTargetView(fragment)
            }
        }*/

        /**
         * Create Sneaker instance
         *
         * @param viewGroup
         * @return Sneaker instance
         */
        /*fun with(viewGroup: ViewGroup): Alert {
            return Alert(viewGroup.context).also {
                it.setTargetView(viewGroup)
            }
        }*/
    }
    private fun setTargetView(targetView: Any) {
        this.targetView =
            when (targetView) {
                is Activity -> {
                    isActivity = true
                    mMargin = (getScreenWidth(targetView)-280.px)/2
                    targetView.window?.decorView as ViewGroup
                }
                else -> null
            }
    }

    private fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }
    fun setMessage(message: String): Alert {
        mMessage = message
        return this
    }
    private fun autoHide(autoHide: Boolean): Alert {
        mAutoHide = autoHide
        return this
    }
    fun setOnSneakerDismissListener(listener:() -> Unit): Alert {
        mDismissListener = listener
        return this
    }

    /**
     * Shows success sneaker with fixed icon, background color and icon color.
     * Icons, background and text colors for this are not customizable
     */
    fun sneakSuccess() {
        mBackgroundColor = colorWhite.toArgb()
        mBackgroundBorderColor = colorGreen.toArgb()
        mMessageColor = colorTextPrimary.toArgb()
        mIconColorFilterColor = colorGreen.toArgb()
        mIconDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_done_24)
        if(!mAutoHide) {
            mIconCloseDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_close_24)
        }

        sneakView()
    }
    fun sneakInfo() {
        mBackgroundColor = colorWhite.toArgb()
        mBackgroundBorderColor = colorAccentBlue.toArgb()
        mMessageColor = colorTextPrimary.toArgb()
        mIconColorFilterColor = colorAccentBlue.toArgb()
        mIconDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_info_outline_24)
        if(!mAutoHide) {
            mIconCloseDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_close_24)
        }

        sneakView()
    }

    fun sneakError() {
        mBackgroundColor = colorWhite.toArgb()
        mBackgroundBorderColor = colorRed.toArgb()
        mMessageColor = colorTextPrimary.toArgb()
        mIconColorFilterColor = colorRed.toArgb()
        mIconDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_error_outline_24)
        if(!mAutoHide) {
            mIconCloseDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_close_24)
        }
        sneakView()
    }



    private fun sneakView() {
        // Main layout
        targetView?.let {
            //context.hideKeyboard(targetView!!)
            val ll = LinearLayout(context)
            ll.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            //ll.setBackgroundColor(ContextCompat.getColor(context, R.color.alpha_grey))
            ll.setOnClickListener {
                removeExistingSneakerView(targetView!!)
            }
            val layoutParams2 = LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                ).apply {
                                    gravity = Gravity.TOP
                                    setMargins(mMargin, 95.px, mMargin, 0)
                                }
            with(sneakerView) {
                elevation = 6.px.toFloat()

                this.layoutParams = layoutParams2
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.TOP
                setPadding(16.px, 16.px, 16.px, 16.px)
                setBackground(mBackgroundColor, 16.px, mBackgroundBorderColor)
                setIcon(mIconDrawable, mIconColorFilterColor)
                setTextContent(mMessage, mMessageColor)
                setCloseIcon(mIconCloseDrawable, mIconColorFilterColor)
                setOnClickListener(this@Alert)
            }
            removeExistingSneakerView(it)
            if(mAutoHide) {
                it.addView(sneakerView, 0)
            }else{
                ll.addView(sneakerView, 0)
                it.addView(ll)
            }

            //sneakerView.startAnimation(AnimationUtils.loadAnimation(context, kz.alseco.views.R.anim.popup_show))
            if (mAutoHide) {
                val handler = Handler()
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    removeView(sneakerView)
                }, mDuration.toLong())
            }
        }
    }
    private fun removeView(view: View?, animate: Boolean = true) {
        mDismissListener?.invoke()
        view?.let {
            //if (animate) it.startAnimation(AnimationUtils.loadAnimation(context, kz.alseco.views.R.anim.popup_hide))
            if(mAutoHide) {
                targetView?.removeView(it)
            }else{
                targetView?.removeView(it.parent as LinearLayout)
            }
        }
    }

    private fun removeExistingSneakerView(parent: ViewGroup) {
        parent.findViewById<LinearLayout>(sneakerView.id)?.let {
            removeView(it, false)
        }
    }
    override fun onClick(p0: View?) {
        removeView(sneakerView, true)
    }

}

val Int.px: Int
    get() {
        // Получаем текущую "плотность" (density) экрана.
        val density = Resources.getSystem().displayMetrics.density
        // Умножаем значение (this) на плотность, чтобы получить пиксели.
        return (this * density).toInt()
    }