package com.adempolat.livescoreapp.utils

import android.app.Dialog
import android.view.View
import android.widget.ImageView
import com.adempolat.livescoreapp.R
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImageNOCache(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(this)
}

fun showAlert(status: Int?, dialog: Dialog?) {
    if (dialog != null) {
        dialog.setContentView(R.layout.layout_loading)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        val img: LottieAnimationView =
            dialog.findViewById<View>(R.id.animate_loading) as LottieAnimationView
        if (status == 0) {
            dialog.dismiss()
        } else {
            dialog.show()
        }
    }

}