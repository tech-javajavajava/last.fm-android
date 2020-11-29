package technopark.andruxa.myapplication.models

interface CanBeBroken {
    fun isBroken(): Boolean {
        return errorCode != 0
    }

    var errorCode: Int
    var message: String
}

fun notFound(broken: CanBeBroken): CanBeBroken {
    broken.errorCode = 404
    broken.message = broken::class.java.name + ": Not Found"
    return broken
}