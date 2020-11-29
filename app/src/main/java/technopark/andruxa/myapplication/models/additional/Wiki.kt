package technopark.andruxa.myapplication.models.additional

import java.util.*

interface Wiki {
    val published: Calendar
    val summary: String
    val content: String
}