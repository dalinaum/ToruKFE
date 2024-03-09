package kr.toru.kotlinflowevent.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.toru.kotlinflowevent.R
import kr.toru.kotlinflowevent.presentation.viewmodel.Event
import kr.toru.kotlinflowevent.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            viewModel.start()
            viewModel.eventFlow.collectLatest { event ->
                onHandleEvent(event)
            }

        }
    }

    private fun onHandleEvent(event: Event) {
        when(event) {
            is Event.Loading -> {
                Log.e("Toru", "OnHandleEvent: Loading")
            }
            is Event.LoadedList -> {
                // do something
                Log.e("Toru", "OnHandleEvent: Loaded with List")
            }
            is Event.EmptyList -> {
                // do something
                Log.e("Toru", "OnHandleEvent: Empty List")
            }
        }
    }
}