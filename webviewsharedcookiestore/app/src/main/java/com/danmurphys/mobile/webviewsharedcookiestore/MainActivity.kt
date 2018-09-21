package com.danmurphys.mobile.webviewsharedcookiestore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONObject
import java.io.BufferedInputStream
import java.lang.System.`in`
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import android.os.StrictMode
import android.util.Log
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("http://10.0.2.2:39467/api/values")

            urlConnection = url
                    .openConnection() as HttpURLConnection

            val status = urlConnection.getResponseCode()

            var `in`: InputStream? = null
            if  (status != 200)
            {
                `in` = urlConnection.getErrorStream()
            }
            else
            {
                `in` = urlConnection.inputStream
            }


            val isw = InputStreamReader(`in`)

            var data = isw.read()
            while (data != -1) {
                val current = data.toString()
                data = isw.read()
                Log.d("DM MY app", current);
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
        }

    }
}
