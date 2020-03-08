package com.kaloglu.library.ui.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kaloglu.library.ui.BaseApplication
import com.kaloglu.library.ui.viewmodel.states.State

abstract class BaseViewModel<S : State>(application: BaseApplication) :
    AndroidViewModel(application) {
    val state: LiveData<S>
        get() = _state

    private val _state: MutableLiveData<S> = MutableLiveData()

    init {
        Transformations.map(state, ::onState)
    }

    abstract fun onState(state: S)

    fun postState(state: S) {
        _state.postValue(state)
    }

}
