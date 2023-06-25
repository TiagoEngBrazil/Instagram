package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import java.util.*

data class Post(
    val uuid: UUID,
    val uri: Uri,
    val caption: String,
    val timestamp: Long,
)