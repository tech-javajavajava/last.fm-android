package technopark.andruxa.myapplication.data

import androidx.lifecycle.MutableLiveData

class SData<T>: SDataI<T> {
    override var state: MutableLiveData<SDataI.State> = MutableLiveData(SDataI.State.Ok)
        private set
    fun postState(newState: SDataI.State) {
        state.postValue(newState)
    }
    override var message: String = "init"
        private set
    fun setMessage(newMessage: String) {
        message = newMessage
    }

    override var data: T? = null
        private set
    fun setData(newData: T?) {
        data = newData
    }
}