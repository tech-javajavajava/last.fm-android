package technopark.andruxa.myapplication.models.additional

interface CanBeBroken {
    var errorCode: Int
    var message: String

    fun isBroken(): Boolean {
        return errorCode != 0
    }

    fun notFound(): CanBeBroken {
        errorCode = 404
        message = this::class.java.name + ": Not Found"
        return this
    }

    fun notInitialized(): CanBeBroken {
        errorCode = 418
        message = this::class.java.name + ": Not initialized"
        return this
    }
}
