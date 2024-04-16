package com.mis.route.chatapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.mis.route.chatapp.R

@BindingAdapter("error")
fun setErrorTextInputLayout(textInputLayout: TextInputLayout, errorText: String?) {
    textInputLayout.error = errorText
}

@BindingAdapter("imageFromCategory")
fun setImageCategory(imageView: ImageView, category: String?) {
    if (category.equals("movies")) {
        imageView.setImageResource(R.drawable.image_movies_cat)
    } else if (category.equals("sports")) {
        imageView.setImageResource(R.drawable.image_sports_cat)
    } else if (category.equals("music")) {
        imageView.setImageResource(R.drawable.image_music_cat)
    } else {
        imageView.setImageResource(R.drawable.image_room)
    }
}