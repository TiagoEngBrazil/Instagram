package co.tiagoaguiar.course.instagram.Register.data

import android.net.Uri
import co.tiagoaguiar.course.instagram.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FireRegisterDataSource : RegisterDataSource {

    override fun create(email: String, callBack: RegisterCallback) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    callBack.onSuccess()
                } else {
                    callBack.onFailure("Usauário já cadastrado!")
                }
            }
            .addOnFailureListener { exeption ->
                callBack.onFailure(exeption?.message ?: "erro interno no servidor!")
            }
            .addOnCompleteListener {
                callBack.onComplete()
            }
    }

    override fun create(email: String, name: String, password: String, callBack: RegisterCallback) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                // aqui já tem usuário criado no authentication

                val uid = result.user?.uid
                if (uid == null) {
                    callBack.onFailure("erro interno no servidor!")
                } else {

                    FirebaseFirestore.getInstance()
                        .collection("/users")
                        .document(uid)
                        .set(
                            hashMapOf(
                                "name" to name,
                                "email" to email,
                                "followers" to 0,
                                "following" to 0,
                                "postCount" to 0,
                                "uuid" to uid,
                                "photoURI" to null
                            )
                        )
                        .addOnSuccessListener {
                            callBack.onSuccess()
                        }
                        .addOnFailureListener { exception ->
                            callBack.onFailure(exception.message ?: "Erro interno do servidor!")
                        }
                        .addOnCompleteListener {
                            callBack.onComplete()
                        }

                }
            }
            .addOnFailureListener { exeption ->
                callBack.onFailure(exeption?.message ?: "Erro interno do servidor!")
            }
    }

    override fun upDateUser(photoUri: Uri, callBack: RegisterCallback) {
        val uid = FirebaseAuth.getInstance().uid

        if (uid == null || photoUri.lastPathSegment == null) {
            callBack.onFailure("usuário não encontrado!")
            return
        }

        // images/(identificador único)/photoURI

        val storegeRef = FirebaseStorage.getInstance().reference

        val imgRef = storegeRef.child("images/")
            .child(uid)
            .child(photoUri.lastPathSegment!!)

        imgRef.putFile(photoUri)
            .addOnSuccessListener { result ->

                // Aqui a foto já foi para o Fire Storege

                imgRef.downloadUrl
                    .addOnCompleteListener { res ->
                        val userRef =
                            FirebaseFirestore.getInstance().collection("/users").document(uid)

                        userRef.get()
                            .addOnSuccessListener { document ->
                                val user = document.toObject(User::class.java)
                                val newUser = user?.copy(photoUrl = res.toString())

                                if (newUser != null) {
                                    userRef.set(newUser)
                                        .addOnSuccessListener {
                                            callBack.onSuccess()
                                        }
                                        .addOnFailureListener { exception ->
                                            callBack.onFailure(exception.message ?: "Falha ao atualizar a foto!")
                                        }
                                        .addOnCompleteListener {
                                            callBack.onComplete()
                                        }
                                }
                            }
                    }
            }
            .addOnFailureListener { exception ->
                callBack.onFailure(exception.message ?: "Falha ao subir a foto!")
            }
    }
}