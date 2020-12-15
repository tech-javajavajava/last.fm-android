package technopark.andruxa.myapplication.data.session

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.session.SessionAuthBody
import technopark.andruxa.myapplication.data.storages.lastFm.session.SessionResponseXML

class SessionRepo: ISessionRepo {
    override var isLogined: SData<Boolean> = SData()
        private set
    override var sessionKey: String? = null
        private set
    override var apiSig: String? = null
        private set

    private val lastFmStore = LastFmStore.instance.sessionApi

    override fun login(login: String, password: String): SDataI<Boolean> {
        lastFmStore.login(SessionAuthBody(login, password)).enqueue(
            object : Callback<SessionResponseXML> {
                override fun onResponse(
                    call: Call<SessionResponseXML>,
                    response: Response<SessionResponseXML>
                ) {
                    response.body()?.let {
                        sessionKey = it.sessionKey
                        apiSig = it.apiSig
                    }
                    isLogined.setData(true)
                    isLogined.postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<SessionResponseXML>, t: Throwable) {
                    isLogined.setData(false)
                    isLogined.networkError(t.message)
                }
            }
        )

        return isLogined
    }

    companion object {
        var repoSessionRepo: ISessionRepo? = null
        fun getInstance(): ISessionRepo {
            if (repoSessionRepo == null) {
                repoSessionRepo = SessionRepo()
            }
            return repoSessionRepo as ISessionRepo
        }
    }
}