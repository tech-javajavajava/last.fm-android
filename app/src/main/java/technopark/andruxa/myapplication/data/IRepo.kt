package technopark.andruxa.myapplication.data

import androidx.lifecycle.LiveData

interface IRepo {
    enum class State {
        Ok,
        Warn,
        Err,
    }

    val state: LiveData<State>
    val message: LiveData<String>
}
