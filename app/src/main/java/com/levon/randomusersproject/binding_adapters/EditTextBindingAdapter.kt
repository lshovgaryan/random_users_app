package com.levon.randomusersproject.binding_adapters

import android.text.TextWatcher
import android.view.View.OnFocusChangeListener
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("textChangedListener")
fun bindTextWatcher(editText: TextInputEditText, textWatcher: TextWatcher?) {
    editText.addTextChangedListener(textWatcher)
}

@BindingAdapter("onFocusChanged")
fun bindFocusChangeListener(editText: TextInputEditText, focusChangeListener: OnFocusChangeListener?) {
    editText.onFocusChangeListener = focusChangeListener
}