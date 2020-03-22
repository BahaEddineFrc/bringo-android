package com.bringo.dotit.adapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bringo.dotit.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation

object ImageUrlBindingAdapter{

    @JvmStatic
    @BindingAdapter("android:img")
    fun setImageUrl(view: ImageView, url:String?){
        if(url!=null)
        Picasso.get().load(url).error(R.drawable.pizza)
            .into(view, object :Callback{
            override fun onSuccess() {
                Log.d("ImageUrlBindingAdapter", "success")
            }

            override fun onError(e: Exception?) {
                Log.d("ImageUrlBindingAdapter", "error ${e?.message}")
            }
        })
        else view.setImageResource(R.drawable.pizza)
    }

    @JvmStatic
    @BindingAdapter("android:blur")
    fun setBlurImageUrl(view: ImageView, url:String?){
        if(url!=null)
            Picasso.get().load(url).error(R.drawable.pizza)
                .transform(BlurTransformation(view.context, 25, 1))
                .into(view, object :Callback{
                    override fun onSuccess() {
                        Log.d("ImageUrlBindingAdapter", "success")
                    }

                    override fun onError(e: Exception?) {
                        Log.d("ImageUrlBindingAdapter", "error ${e?.message}")
                    }
                })
        else view.setImageResource(R.drawable.pizza)

    }

}