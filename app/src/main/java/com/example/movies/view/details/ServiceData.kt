package com.example.movies.view.details

import android.app.IntentService
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.movies.model.MoviesWithoutGenres
import com.example.movies.model.YOUR_API_KEY
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val SERVICE_DATA_INT_EXTRA = "IntExtra"
private var movieId: Int? = null

class ServiceData : IntentService(null) {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            movieId = it.getIntExtra(SERVICE_DATA_INT_EXTRA, 0)
        }

        val moviesURL =
            "https://api.themoviedb.org/3/movie/${movieId}?api_key=${YOUR_API_KEY}&language=ru-RU"
        try {
            var urlConnection: HttpsURLConnection? = null
            try {
                urlConnection = URL(moviesURL).openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 3000
                urlConnection.connectTimeout = 3000
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                sendResult(Gson().fromJson(getLines(bufferedReader),
                    MoviesWithoutGenres::class.java))
            } catch (e: Exception) {
                Log.e("", "Fail connection", e)
                e.printStackTrace()
            } finally {
                urlConnection?.disconnect()
            }
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
    }

    private fun sendResult(result: MoviesWithoutGenres) {
        val broadcastIntent = Intent(INTENT_FILTER)
        broadcastIntent.putExtra(DETAILS_FRAGMENT_BROADCAST, result)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}