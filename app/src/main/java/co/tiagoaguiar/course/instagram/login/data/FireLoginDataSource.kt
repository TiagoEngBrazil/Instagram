package co.tiagoaguiar.course.instagram.login.data

import com.google.firebase.auth.FirebaseAuth

class FireLoginDataSource: LoginDataSource {
    override fun login(email: String, password: String, callBack: LoginCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->
                if (res.user != null) {
                    callBack.onSuccess()
                } else {
                    callBack.onFailure("usuário não encontrado!")
                }
            }
            .addOnFailureListener { exception ->
                callBack.onFailure(exception.message ?: "Usuário não cadastrado!")
            }
            .addOnCompleteListener {
                callBack.onComplete()
            }
    }
}