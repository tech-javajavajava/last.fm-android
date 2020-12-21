package technopark.andruxa.myapplication.data.session

import technopark.andruxa.myapplication.data.SDataI

interface ISessionRepo {
    val isLogined: SDataI<Boolean>
    val sessionKey: String?

    fun login(login: String, password: String): SDataI<Boolean>
}