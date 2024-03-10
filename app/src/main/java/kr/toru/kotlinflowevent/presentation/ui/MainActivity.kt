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
// 안쓰는 import 제거
// import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.toru.kotlinflowevent.R
import kr.toru.kotlinflowevent.presentation.viewmodel.Event
import kr.toru.kotlinflowevent.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 단순히 setContentView하는 것이면 AppCompatActivity 생성자에 넣는게 좋을 듯.
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            // 둘을 분리할 필요가 없는 듯.
//            viewModel.start()
//            viewModel.eventFlow.collectLatest { event ->
            viewModel.getEvents().collectLatest {
                onHandleEvent(it)
//                onHandleEvent(event)
            }
        }
    }

    private fun onHandleEvent(event: Event) {
        when(event) {
            Event.Loading -> { // Object는 is 비교 필요없음.
                Log.e("Toru", "OnHandleEvent: Loading")
            }
            is Event.LoadedList -> {
                // do something
                Log.e("Toru", "OnHandleEvent: Loaded with List")
            }
            Event.EmptyList -> { // Object는 is 비교 필요 없음.
                // do something
                Log.e("Toru", "OnHandleEvent: Empty List")
            }
        }
    }
}