package technopark.andruxa.myapplication.data.user

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.session.SessionRepo
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.user.UserInfoXML
import technopark.andruxa.myapplication.data.storages.lastFm.user.UserLovedXML
import technopark.andruxa.myapplication.data.storages.lastFm.user.UserRecentXML
import technopark.andruxa.myapplication.models.track.Track
import technopark.andruxa.myapplication.models.user.User

class UserRepo: UserRepoI {
    override var currentUser: SData<User> = SData()
        private set
    override var loveTracks: SData<List<Track>> = SData()
        private set
    override var recenTracks: SData<List<Track>> = SData()
        private set

    private val sessionRepo = SessionRepo.getInstance()
    private val lastFmStore = LastFmStore.instance.userApi

    override fun getLovedTracks(name: String, limit: Int, page: Int): SDataI<List<Track>> {
        with(loveTracks) {
            postState(SDataI.State.Load)
            lastFmStore.getLoved(name, limit, page).enqueue(object : Callback<UserLovedXML> {
                override fun onResponse(call: Call<UserLovedXML>, response: Response<UserLovedXML>) {
                    setData(response.body()?.tracks?.map { t -> t.toTrack() })
                    postState(SDataI.State.NetOk)
                    setNetErr(false)
                }

                override fun onFailure(call: Call<UserLovedXML>, t: Throwable) {
                    Log.d("db/userloved/err", "err")
                    t.message?.let {
                        Log.d("db/userloved/message", it)
                    }
                    Log.d("db/userloved/stack", t.stackTrace.joinToString("\n"))
                    networkError(t.message)
                }
            })

            return this
        }
    }

    override fun getRecentTracks(name: String, limit: Int, page: Int): SDataI<List<Track>> {
        with(recenTracks) {
            postState(SDataI.State.Load)
            lastFmStore.getRecent(name, limit, page).enqueue(object : Callback<UserRecentXML> {
                override fun onResponse(call: Call<UserRecentXML>, response: Response<UserRecentXML>) {
                    setData(response.body()?.tracks?.map { t -> t.toTrack() })
                    postState(SDataI.State.NetOk)
                    setNetErr(false)
                }

                override fun onFailure(call: Call<UserRecentXML>, t: Throwable) {
                    Log.d("db/userrecent", "err")
                    t.message?.let {
                        Log.d("db/userrecent/message", it)
                    }
                    Log.d("db/userrecent/stack", t.stackTrace.joinToString("\n"))
                    networkError(t.message)
                }
            })

            return this
        }
    }

    override fun getCurrent(): SData<User> {
        Log.d("user", sessionRepo.isLogined.isOk().toString())
        Log.d("user", sessionRepo.isLogined.data.toString())
        Log.d("user", sessionRepo.apiSig.toString())
        Log.d("user", sessionRepo.sessionKey.toString())
        if (sessionRepo.isLogined.isOk() &&
                sessionRepo.isLogined.data == true &&
                sessionRepo.sessionKey != null) {

            lastFmStore.getInfo(sessionRepo.sessionKey!!).enqueue(
                object: Callback<UserInfoXML> {
                    override fun onResponse(
                        call: Call<UserInfoXML>,
                        response: Response<UserInfoXML>
                    ) {
                        currentUser.setData(response.body()?.user?.toUser())
                        currentUser.postState(SDataI.State.NetOk)
                        currentUser.setNetErr(false)
                    }

                    override fun onFailure(call: Call<UserInfoXML>, t: Throwable) {
                        Log.d("user", t.toString())
                        t.message?.let { currentUser.setMessage(it) }
                        currentUser.setNetErr(true)
                    }
                }
            )

        } else {
            currentUser.setMessage("need to authorize")
            currentUser.postState(SDataI.State.Err)
        }

        return this.currentUser
    }

    companion object {
        var repo: UserRepoI? = null
        fun getInstance(): UserRepoI {
            if (repo == null) {
                repo = UserRepo()
            }
            return repo as UserRepoI
        }
    }
}