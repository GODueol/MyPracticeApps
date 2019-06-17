package com.miraclehwan.miraclegithub

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.adapters.ListenerUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclegithub.adapter.SearchAdapter
import com.miraclehwan.miraclegithub.network.response.Item
import com.miraclehwan.miraclegithub.util.Log

object Extensions {

    @JvmStatic
    @BindingAdapter("android:text")
    fun setText(view: TextView, text: CharSequence?) {
        if(text == null){
            return;
        }
        if (view.text.toString() != text.toString()) {
            Log.e(text.toString())
            view.text = text
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "android:text", event = "textEvent")
    fun getText(view: TextView): String {
        Log.e(view.text.toString())
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
                    Log.e(s.toString())
                    listener.onChange()
                }
            }
        })
    }

    @JvmStatic
    @BindingAdapter("setItem")
    fun setItem(view: RecyclerView, items: List<Item>?) {
        if (items == null) {
            return
        }
        val adapter = view.adapter as? SearchAdapter ?: SearchAdapter().apply { view.adapter = this }
        adapter.setItems(items)
    }

    @JvmStatic
    @BindingAdapter("onScrolled")
    fun onScrolled(view:RecyclerView, onScrolled: OnScrolled){
        val newValue: RecyclerView.OnScrollListener?
        if (onScrolled == null) {
            newValue = null
        } else {
            newValue = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (onScrolled == null) {
                        return
                    }
                    val layoutManager = view.layoutManager
                    val totalItemCount = layoutManager?.itemCount ?: 0
                    val lastVisibleItem =
                        layoutManager?.let { (layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 1 } ?: 0

                    Log.e("size log | $totalItemCount / $lastVisibleItem")

                    if (totalItemCount != 0 && totalItemCount!! <= lastVisibleItem!!){
                        onScrolled.onScrolled(totalItemCount)
                    }
                }
            }
        }
        val oldValue = ListenerUtil.trackListener(view, newValue, R.id.rv)
        if (oldValue != null) {
            view.removeOnScrollListener(oldValue)
        }
        if (newValue != null) {
            view.addOnScrollListener(newValue)
        }
    }

    interface OnScrolled {
        fun onScrolled(totalItemCount:Int)
    }
}