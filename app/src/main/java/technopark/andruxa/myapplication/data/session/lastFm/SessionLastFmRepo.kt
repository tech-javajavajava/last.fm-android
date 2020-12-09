package technopark.andruxa.myapplication.data.session.lastFm

import technopark.andruxa.myapplication.data.additional.lastFm.Requester
import technopark.andruxa.myapplication.data.session.SessionRepo

class SessionLastFmRepo: SessionRepo {
    override fun login(login: String, password: String): Boolean {
        return Requester.getInstance().login(login, password)
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }
}