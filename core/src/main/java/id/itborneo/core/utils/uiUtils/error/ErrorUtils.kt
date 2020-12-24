package id.itborneo.core.utils.uiUtils.error

import android.view.View
import kotlinx.android.synthetic.main.view_error.view.*

object ErrorUtils {

    fun showError(errorView: View, text: String) {
        errorView.visibility = View.VISIBLE
        errorView.tv_error.text = text

    }


}