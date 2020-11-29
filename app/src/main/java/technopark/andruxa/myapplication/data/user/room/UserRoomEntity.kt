package technopark.andruxa.myapplication.data.user.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.user.User
import java.util.*

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
    @ColumnInfo override var registeredTime: Calendar = Calendar.getInstance()
    @Ignore override var errorCode: Int = 0
    @Ignore override lateinit var message: String

    override fun isBroken(): Boolean {
        if (id < 0) return true
        return super.isBroken()
    }
}