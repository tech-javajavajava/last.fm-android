package technopark.andruxa.myapplication.data.additional.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.user.User

@Entity
class UserRoomEntity: User {
    @PrimaryKey override var id: Int = -1
    @ColumnInfo override var name: String = ""
    @ColumnInfo override var realName: String = ""
    @ColumnInfo override var url: String = ""
    @ColumnInfo override var image: String = ""
    @ColumnInfo override var country: String = ""
    @ColumnInfo override var age: Int = -1
    @ColumnInfo override var gender: String = ""
    @ColumnInfo override var isSubscriber: Boolean = false
    @ColumnInfo override var playlists: Int = -1
    @ColumnInfo override var isBootstrap: Boolean = false
    @ColumnInfo override var registeredTime: String = ""
    @Ignore override var errorCode: Int = 0
    @Ignore override var message: String = ""

    override fun isBroken(): Boolean {
        if (id < 0) return true
        return super.isBroken()
    }
}

fun fromUser(user: User): UserRoomEntity {
    return UserRoomEntity().apply {
        id = user.id
        name = user.name
        realName = user.realName
        url = user.url
        image = user.image
        country = user.country
        age = user.age
        gender = user.gender
        isSubscriber = user.isSubscriber
        playlists = user.playlists
        isBootstrap = user.isBootstrap
        registeredTime = user.registeredTime
        errorCode = user.errorCode
        message = user.message
    }
}