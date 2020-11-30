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

    fun setLastFmError(message: String): CanBeBroken {
        errorCode = 555
        this.message = this::class.java.name + message
        return this
    }

    fun setEmptyMbid(): CanBeBroken {
        errorCode = 419
        message = this::class.java.name + ": Empty mbid"
        return this
    }

    fun setNotPermitted(): CanBeBroken {
        errorCode = 420
        message = this::class.java.name + ": not permitted"
        return this
    }

    fun setBadRequest(): CanBeBroken {
        errorCode = 421
        message = this::class.java.name + ": bad request"
        return this
    }
}
