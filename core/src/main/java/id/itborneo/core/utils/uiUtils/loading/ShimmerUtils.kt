package id.itborneo.core.utils.uiUtils.loading

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerUtils(private val shLoading: ShimmerFrameLayout) {


    fun showLoading() {
        shLoading.startShimmer()
        shLoading.visibility = View.VISIBLE

    }

    fun hideLoading() {
        shLoading.stopShimmer()
        shLoading.visibility = View.INVISIBLE
    }
}