package com.condorlabs.skill_test.feature_search_product

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("bindingEditTextValue")
fun bindingEditTextValue(textInputEditText: TextInputEditText, value: MediatorLiveData<String?>) {
    textInputEditText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            value.value = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setAvailableCountries")
fun setAvailableCountries(textView: TextView, string: String) {
    if (string.isNotEmpty()) {
        textView.text = "Disponible en: $string"
    } else {
        textView.text = "Disponible en más de 5 países"
    }
}

@BindingAdapter("loadImageFromUrl")
fun bindingImageFromUrl(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        Glide.with(imageView.context).load(imageUrl).into(imageView)
    }
}

@BindingAdapter("setPopularity")
fun setPopularity(ratingBar: RatingBar, number: Int?) {
    if (number != null) {
        ratingBar.rating = (number / 10 / 2).toFloat()
    }
}

@BindingAdapter(value = ["hasResults", "isloading"], requireAll = true)
fun bindingPlaceholderIsGone(view: View, hasResults: Boolean?, isloading: Boolean?) {
    view.visibility = if (hasResults == false && isloading == false) View.VISIBLE else View.GONE
}