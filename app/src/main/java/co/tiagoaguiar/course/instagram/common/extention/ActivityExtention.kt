package co.tiagoaguiar.course.instagram.common.extention

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hidekeyboard() {
    val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    var view: View? = currentFocus
    if (view == null) view = View(this)

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}