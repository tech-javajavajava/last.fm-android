package technopark.andruxa.myapplication.data.user

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.session.SessionRepo
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.user.UserInfoXML
import technopark.andruxa.myapplication.models.user.User

class UserRepo: UserRepoI {
    override var currentUser: SData<User> = SData()

    private val sessionRepo = SessionRepo.getInstance()

    private val userApi = LastFmStore.instance.userApi

    override fun getCurrent(): SData<User> {
        if (sessionRepo.isLogined.isOk() &&
                sessionRepo.isLogined.data == true &&
                sessionRepo.apiSig != null &&
                sessionRepo.sessionKey != null) {

            userApi.getInfo(sessionRepo.sessionKey!!, sessionRepo.apiSig!!).enqueue(
                object: Callback<UserInfoXML> {
                    override fun onResponse(
                        call: Call<UserInfoXML>,
                        response: Response<UserInfoXML>
                    ) {
                        currentUser.setData(response.body()?.user?.toUser())
                        currentUser.postState(SDataI.State.NetOk)
                        currentUser.setNetErr(false);
                    }

                    override fun onFailure(call: Call<UserInfoXML>, t: Throwable) {
                        t.message?.let { currentUser.setMessage(it) }
                        currentUser.setNetErr(true)
                    }
                }
            )

        } else {
            currentUser.setMessage("need to authorize")
            currentUser.postState(SDataI.State.Err)
        }

        return this.currentUser;
    }
}