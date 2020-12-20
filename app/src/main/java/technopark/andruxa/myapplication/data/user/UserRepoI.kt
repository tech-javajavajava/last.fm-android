package technopark.andruxa.myapplication.data.user

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.user.User

interface UserRepoI {
    val currentUser: SDataI<User>

    fun getCurrent(): SDataI<User>
}
