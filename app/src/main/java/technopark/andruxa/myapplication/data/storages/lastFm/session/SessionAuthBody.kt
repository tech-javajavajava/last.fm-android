package technopark.andruxa.myapplication.data.storages.lastFm.session

import technopark.andruxa.myapplication.Utils
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore

class SessionAuthBody(val username: String, password: String) {
    val method: String = "auth.getMobileSession"
    val api_key: String = LastFmStore.instance.apiKey
    val api_sig: String = Utils.md5("api_key" + api_key + "method" + method + "password" + password + "username" + username + System.getenv("last_fm_api_secret"))
}