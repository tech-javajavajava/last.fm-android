package technopark.andruxa.myapplication.data

import androidx.lifecycle.LiveData

interface SDataI<T> {
    val state: LiveData<State>
    val message: String
    val data: T?
    val isNetErr: Boolean

    enum class State {
        NetOk,
        CacheOk,
        SqlOk,
        Load,
        Err,
    }

    fun isOk(): Boolean {
        return state.value == State.NetOk ||
                state.value == State.CacheOk ||
                state.value == State.SqlOk
    }
}