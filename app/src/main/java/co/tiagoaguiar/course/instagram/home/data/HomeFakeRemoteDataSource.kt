package co.tiagoaguiar.course.instagram.home.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.DataBase
import co.tiagoaguiar.course.instagram.common.model.Post

class HomeFakeRemoteDataSource : HomeDataSource {

    override fun fetchFeed(useUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val feed = DataBase.feeds[useUUID]

            callback.onSuccess(feed?.toList() ?: emptyList())


            callback.onComplete()
        }, 2000)
    }
}