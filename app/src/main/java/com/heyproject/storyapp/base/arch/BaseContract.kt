package com.heyproject.storyapp.base.arch

import com.heyproject.storyapp.base.wrapper.ViewResource

/**
Written by Yayan Rahmat Wijaya on 9/22/2022 17:31
Github : https://github.com/yayanrw
 **/

interface BaseContract {
    interface BaseView {
        fun observeData()
        fun showContent(isContentVisible: Boolean)
        fun showEmptyData(isEmpty: Boolean)
        fun showLoading(isShowLoading: Boolean)
        fun showError(isErrorEnabled: Boolean, exception: Exception? = null)
        fun <T : ViewResource<*>> handleData(viewResource: T)
        fun <T> showData(data: T)
    }

    interface BaseRepository {
        fun logResponse(msg: String?)
    }
}