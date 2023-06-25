package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Post(
    val uuid: UUID,
    val uri: Uri,
    val caption: String,
    val timestamp: Long,
) : Parcelable