package technopark.andruxa.myapplication.data.session

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.Constants
import technopark.andruxa.myapplication.Utils
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.session.SessionResponseXML

class SessionRepo: ISessionRepo {
    override var isLogined: SData<Boolean> = SData()
        private set
    override var sessionKey: String? = null
        private set

    private val lastFmStore = LastFmStore.instance.sessionApi

    override fun login(login: String, password: String): SDataI<Boolean> {
        lastFmStore.login(
            login,
            password,
            Utils.md5("api_key" + Constants.lastFmApiKey + "methodauth.getMobileSessionpassword" + password + "username" + login + Constants.lastFmApiSecret)
        ).enqueue(
            object : Callback<SessionResponseXML> {
                override fun onResponse(
                    call: Call<SessionResponseXML>,
                    response: Response<SessionResponseXML>
                ) {
                    Log.d("auth", "started!")
                    response.body()?.let {
                        if (it.status != null) {
                            Log.d("auth resp status", it.status!!)
                        } else {
                            Log.d("auth resp status", "NO STATUS")
                        }

                        if (it.sessionKey != null) {
                            Log.d("auth resp session key", it.sessionKey!!)
                        } else {
                            Log.d("auth resp session key", "NO SKEY")
                        }

                        Log.d("auth", "got session key")
                        isLogined.setData(it.sessionKey != null)
                        sessionKey = it.sessionKey
                    }
                    isLogined.postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<SessionResponseXML>, t: Throwable) {
                    Log.d("auth", t.toString())
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