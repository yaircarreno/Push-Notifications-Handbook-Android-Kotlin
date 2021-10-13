package com.yaircarreno.pushnotificationshandbookwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val PLAY_SERVICES_RESOLUTION_REQUEST: Int = 9000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPlayServices()) {
            // Great! You can send notifications to this device using FCM.
            getTokenFromFMC();
        } else {
            //You won't be able to send notifications to this device
            Log.e(TAG, "Device doesn't have google play services");
        }
    }

    private fun checkPlayServices(): Boolean {
        val resultCode: Int =
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GoogleApiAvailability.getInstance().isUserResolvableError(resultCode)) {
                GoogleApiAvailability.getInstance()
                    .getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)?.show()
            } else {
                Log.d(TAG, "This device is not supported.")
                finish()
            }
            return false
        }
        return true
    }

    private fun getTokenFromFMC() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result
                val msg = "Token created: $token"
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}