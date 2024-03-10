package kr.toru.kotlinflowevent.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
// 안쓰는 import 제거
//import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.toru.kotlinflowevent.domain.repository.FakeJsonRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FakeJsonRepository
) : ViewModel() {

//    private var _eventFlow = MutableStateFlow<Event>(Event.Loading)
//    val eventFlow = _eventFlow.asStateFlow() // var일 필요가 없을 듯.
    // 그런데 굳이 둘을 나눠야 하나?
    // 필드로 만들 필요도 없을듯.

//    suspend fun start() {
    suspend fun getEvents(): StateFlow<Event> {
        val eventFlow = MutableStateFlow<Event>(Event.Loading)
        viewModelScope.launch {
            repository.getPost().collect { post ->
                Log.e("Toru", "length: ${post.size}")

                val event = if(post.isNotEmpty()) {
                    // 데이터를 가공하는 것을 Repository에 넘기는게 낫지 않을까?
                    Event.LoadedList(post.map { it.title })
                } else {
                    Event.EmptyList
                }
//                _eventFlow.emit(event)
                eventFlow.emit(event)
            }
        }
        return eventFlow.asStateFlow()
    }
}

sealed class Event {
    data object Loading: Event()
    data class LoadedList(val list: List<String>): Event()
    data object EmptyList: Event()
}