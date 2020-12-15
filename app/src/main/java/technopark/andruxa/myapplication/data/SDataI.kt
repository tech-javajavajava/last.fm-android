package technopark.andruxa.myapplication.data

import androidx.lifecycle.LiveData

interface SDataI<T> {
    val state: LiveData<State>
    val message: String
    val data: T?

    enum class State {
        Ok,
        Load,
        Err,
    }
}