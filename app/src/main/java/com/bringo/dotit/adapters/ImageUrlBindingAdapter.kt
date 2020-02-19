package com.bringo.dotit.adapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bringo.dotit.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object ImageUrlBindingAdapter{

    @JvmStatic
    @BindingAdapter("android:img")
    fun setImageUrl(view: ImageView, url:String){
        Picasso.get().load(url).error(R.drawable.home)
            .into(view, object :Callback{
            override fun onSuccess() {
                Log.d("ImageUrlBindingAdapter", "success")
            }

            override fun onError(e: Exception?) {
                Log.d("ImageUrlBindingAdapter", "error ${e?.message}")
            }
        })

    }

}