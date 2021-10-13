package com.yaircarreno.pushnotificationshandbookwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yaircarreno.pushnotificationshandbookwithkotlin.databinding.ActivityPushReceiverBinding

class PushReceiverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPushReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushReceiverBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle: Bundle? = intent.extras
        val score: String? = bundle?.getString("score")
        val country: String? = bundle?.getString("country")

        binding.scoreTextView.text = score
        binding.countryTextView.text = country
    }
}