package technopark.andruxa.myapplication.data.user.lastFm

import technopark.andruxa.myapplication.data.additional.lastFm.Requester
import technopark.andruxa.myapplication.data.additional.lastFm.user.UserInfoXML
import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.models.user.User

class UserLastFmRepo: UserRepo {
    override fun getCurrent(): User {
        if (!Requester.getInstance().isAuthorized()) {
            return UserInfoXML().apply { setNotAuthorized() }
        }

        val response = Requester.getInstance().userApi.getInfo().execute()
        if (response.isSuccessful) {
            return response.body()!!
        }

        return UserInfoXML().apply { setLastFmError(response.errorBody().toString()) }
    }

    override fun getById(id: Int): User {
        return UserInfoXML().apply { setNotPermitted() }
    }

    override fun saveCurrent(current: User): User {
        return UserInfoXML().apply { setNotPermitted() }
    }

    override fun save(user: User): User {
        return UserInfoXML().apply { setNotPermitted() }
    }

    override fun deleteCurrent(): User {
        return UserInfoXML().apply { setNotPermitted() }
    }

    override fun deleteById(id: Int): User {
        return UserInfoXML().apply { setNotPermitted() }
    }
}