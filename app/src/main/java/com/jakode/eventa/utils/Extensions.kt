package com.jakode.eventa.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import jp.wasabeef.blurry.Blurry
import java.text.DecimalFormat
import java.util.*

fun formatFloat(number: Float): String {
    val numberToInt = number.toInt()
    return if (number == numberToInt.toFloat()) numberToInt.toString() else number.toString()
}

fun formatIntegerWithCommas(value: Double): String {
    val df = DecimalFormat("###,###,###")
    return df.format(value)
}

fun isPersian() = Locale.getDefault().language == "fa"

fun isDarkTheme(activity: Activity): Boolean {
    return activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}

fun changeTheme(theme: Boolean?) {
    when (theme) {
        false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        null -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}

fun getColor(colorHex: String): Int {
    var color = colorHex
    if (color[0] != '#') color = "#$color"

    return Color.parseColor(color)
}

fun getColor(context: Context, colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}

fun getDrawable(context: Context, drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(context, drawableRes)
}

@SuppressLint("WrongConstant")
fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    } else {
        @Suppress("DEPRECATION")
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun Activity.enableEdgeToEdge() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}

fun hideKeyboard(context: Context, view: View) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun CardView.blur(builder: (Blurry.Composer) -> Blurry.Composer) {
    Blurry.with(context).run(builder).async().animate(500).onto(this)
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, uri: Uri) {
    imageView.load(uri) {
        crossfade(true)
        crossfade(400)
        memoryCachePolicy(CachePolicy.ENABLED)
    }
}

fun setImage(imageView: ImageView, url: String, isCircle: Boolean) {
    imageView.load(url) {
        crossfade(true)
        crossfade(400)
        memoryCachePolicy(CachePolicy.ENABLED)
        if (isCircle) transformations(CircleCropTransformation())
    }
}

suspend fun getBitmap(context: Context, uri: Uri): Bitmap {
    val loading = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(uri)
        .build()
    val result = (loading.execute(request) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap
}

fun buildDrawable(backgroundColor: Int): Drawable {
    return GradientDrawable().also {
        it.shape = GradientDrawable.OVAL
        it.setColor(backgroundColor)
        it.setStroke(2, getColor("#D6CDCDCD"))
    }
}