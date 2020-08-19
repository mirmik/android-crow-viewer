package com.example.android_crow_viewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PowerManager
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import crow.Crow
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    var view0 : VideoSubscriber? = null;
    var view1 : VideoSubscriber? = null;

    @SuppressLint("InvalidWakeLockTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getSupportActionBar()?.hide(); //<< this


        setContentView(R.layout.activity_main)

        Crow.create_udpgate(12,10010);
        Crow.diagnostic(true, true);

        val imgview0 : ImageView = findViewById<ImageView>(R.id.iA)
        val imgview1 : ImageView = findViewById<ImageView>(R.id.iB)

        view0 = VideoSubscriber(imgview0, this)
        view1 = VideoSubscriber(imgview1, this)

        view0?.bind(7);
        view1?.bind(8);

        view0?.subscribe(1, ".12.192.168.1.105:10042", 2, 100);
        view1?.subscribe(1, ".12.192.168.1.105:10041", 2, 100);

        thread(start = true) {
            while(true) {
                Thread.sleep(4000)
                view0?.resubscribe(2, 100)
                view1?.resubscribe(2, 100)
            }
        }

        Crow.start_spin();
    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
