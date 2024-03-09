package kr.toru.kotlinflowevent.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.toru.kotlinflowevent.domain.repository.FakeJsonRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FakeJsonRepository
)   : ViewModel() {

    suspend fun start() {
        viewModelScope.launch {
            val post = repository.getPost()
            Log.e("Toru", "length: ${post.size}")
        }
    }
}