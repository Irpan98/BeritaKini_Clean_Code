package id.itborneo.core.utils.uiUtils

import android.view.View
import kotlinx.android.synthetic.main.partial_appbar.view.*

object AppbarUtils {

    fun title(view: View, text: String) {
        view.tbApp.title = text
    }
}