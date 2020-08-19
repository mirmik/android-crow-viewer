package com.example.android_crow_viewer

import android.R
import android.graphics.ImageDecoder
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import crow.CrowSpamSubscriber
import java.nio.ByteBuffer


class VideoSubscriber(val view : ImageView, val mainActivity: MainActivity) : CrowSpamSubscriber() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun on_message(arr : ByteArray)
    {
        val source : ImageDecoder.Source = ImageDecoder.createSource(ByteBuffer.wrap(arr))
        val drawable = ImageDecoder.decodeDrawable(source)

        mainActivity.runOnUiThread(java.lang.Runnable {
            view.setImageDrawable(drawable)
        })
    }
}