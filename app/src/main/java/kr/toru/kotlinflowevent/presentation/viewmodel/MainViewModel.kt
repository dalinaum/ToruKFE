package kr.toru.kotlinflowevent.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.toru.kotlinflowevent.domain.repository.FakeJsonRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FakeJsonRepository
) : ViewModel() {

    private var _eventFlow = MutableStateFlow<Event>(Event.Loading)
    var eventFlow = _eventFlow.asStateFlow()

    suspend fun start() {
        viewModelScope.launch {
            repository.getPost().collect { post ->
                Log.e("Toru", "length: ${post.size}")

                val event = if(post.isNotEmpty()) {
                    Event.LoadedList(post.map { it.title })
                } else {
                    Event.EmptyList
                }
                _eventFlow.emit(event)
            }
        }
    }
}

sealed class Event {
    data object Loading: Event()
    data class LoadedList(val list: List<String>): Event()
    data object EmptyList: Event()
}