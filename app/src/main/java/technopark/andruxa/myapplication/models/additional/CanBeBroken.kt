package technopark.andruxa.myapplication.models

interface CanBeBroken {
    fun isBroken(): Boolean {
        return errorCode != 0
    }

    val errorCode: Int
    val message: String
}