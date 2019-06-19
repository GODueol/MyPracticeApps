package com.miraclehwan.translate

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.miraclehwan.translate.model.Language

object Extensions {

    @JvmStatic
    @BindingAdapter("android:text")
    fun setText(view: TextView, text: CharSequence?) {
        if (text == null) {
            return;
        }
        if (view.text.toString() != text.toString()) {
            view.text = text
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "android:text", event = "textEvent")
    fun getText(view: TextView): String {
        return view.text.toString()
    }

    @JvmStatic
    @BindingAdapter("textEvent")
    fun setTextEvent(view: TextView, listener: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (listener != null) {
                    listener.onChange()
                }
            }
        })
    }

    @JvmStatic
    @BindingAdapter("changeItem")
    fun chageItem(view: Spinner, onSelected: OnSelected) {
        val newValue: AdapterView.OnItemSelectedListener?
        if (onSelected == null){
            return
        }
        newValue = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (parent?.id) {
                    R.id.spinner_source -> {
                        when(parent?.selectedItem.toString()){
                            "한국어" -> return onSelected.onSelcted(Language.SOURCE_KOREAN)
                            "영어" -> return onSelected.onSelcted(Language.SOURCE_ENGLISH)
                            "일본어" -> return onSelected.onSelcted(Language.SOURCE_JAPAN)
                            "중국어" -> return onSelected.onSelcted(Language.SOURCE_CHINA)
                            else -> return onSelected.onSelcted(Language.SOURCE_KOREAN)
                        }
                    }
                    R.id.spinner_target -> {
                        when(parent?.selectedItem.toString()){
                            "한국어" -> return onSelected.onSelcted(Language.TARGET_KOREAN)
                            "영어" -> return onSelected.onSelcted(Language.TARGET_ENGLISH)
                            "일본어" -> return onSelected.onSelcted(Language.TARGET_JAPAN)
                            "중국어" -> return onSelected.onSelcted(Language.TARGET_CHINA)
                            else -> return onSelected.onSelcted(Language.TARGET_KOREAN)
                        }
                    }
                }
            }
        }
        view.onItemSelectedListener = newValue
    }

    interface OnSelected {
        fun onSelcted(language: Language)
    }
}