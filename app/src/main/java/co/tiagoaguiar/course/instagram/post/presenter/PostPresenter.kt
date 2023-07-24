package co.tiagoaguiar.course.instagram.post.presenter

import android.net.Uri
import co.tiagoaguiar.course.instagram.post.Post
import co.tiagoaguiar.course.instagram.post.data.PostRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostPresenter(
    private var View: Post.View?,
    private val repository: PostRepository,
) : Post.Presenter, CoroutineScope {

    private var uri: Uri? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun fetchPictures() {

        View?.showProgress(true)

        launch {
            val pictures = repository.fetchPictures()
            View?.showProgress(true)

            withContext(Dispatchers.Main) {
                if (pictures.isEmpty()) {
                    View?.displayEmptyPictures()
                } else {
                    View?.displayFullPictures(pictures)
                }
                View?.showProgress(false)
            }


        }
    }

    override fun selectUri(uri: Uri) {
        this.uri = uri
    }

    override fun getSelectedUri(): Uri? {
        return uri
    }

    override fun onDestroy() {
        job.cancel()
        View = null
    }

}