package technopark.andruxa.myapplication.data

import androidx.lifecycle.MutableLiveData

abstract class RepoState: IRepo {
    override var state: MutableLiveData<IRepo.State> = MutableLiveData(IRepo.State.Ok)
        protected set
    override var message: MutableLiveData<String> = MutableLiveData("init")
        protected set

    protected fun setState(newState: IRepo.State, newMessage: String? = null) {
        state.postValue(newState)
        newMessage?.let { message.postValue(newMessage) }
    }
}