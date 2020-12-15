package technopark.andruxa.myapplication.models

abstract class Toppable {
    var isTop: Boolean = false
    fun makeTop() {
        isTop = true
    }
}