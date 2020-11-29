package technopark.andruxa.myapplication.data.user.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.user.User
import java.util.*

@Entity
class UserRoomModel: User {
    @PrimaryKey override var id: Int = -1
    @ColumnInfo override lateinit var name: String
    @ColumnInfo override lateinit var realName: String
    @ColumnInfo override lateinit var url: String
    @ColumnInfo override lateinit var image: String
    @ColumnInfo override lateinit var country: String
    @ColumnInfo override var age: Int = -1
    @ColumnInfo override lateinit var gender: String
    @ColumnInfo override var isSubscriber: Boolean = false
    @ColumnInfo override var playlists: Int = -1
    @ColumnInfo override var isBootstrap: Boolean = false
    @ColumnInfo override lateinit var registeredTime: Calendar
    @Ignore override var errorCode: Int = 0
    @Ignore override lateinit var message: String
}