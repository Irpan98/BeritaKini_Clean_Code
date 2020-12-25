package id.itborneo.beritakini.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.itborneo.beritakini.R
import id.itborneo.core.data.model.News
import id.itborneo.core.utils.constant.EXTRA_NEWS
import id.itborneo.core.utils.mapperUtils.SingleMapper
import id.itborneo.core.utils.uiUtils.BottomNavigationUtils
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var news: News

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonListener()

        removeBottomNav()

        initIntentData()
    }

    private fun removeBottomNav() {
        BottomNavigationUtils.invisible(requireActivity())

    }

    private fun initButtonListener() {

        ivBookmark.setOnClickListener {
            if (news.isBookmarked) {
                bookmarkUpdate(false)
            } else {
                bookmarkUpdate(true)
            }
        }
    }


    private fun initIntentData() {
        val getDataIntent = arguments?.getParcelable<News>(EXTRA_NEWS)

        if (getDataIntent != null)
            news = getDataIntent
        updateUI()


    }

    private fun bookmarkUpdate(newState: Boolean) {

        viewModel.updateBookmark(SingleMapper.newsToEntity(news), newState)

        news.isBookmarked = newState
        updateUIBookmarked()

    }

    private fun updateUI() {


        news.apply {
            tvTitle.text = title
            btnKeBerita.setOnClickListener {
                openWebPage(news.url)
            }
            tvSourceName.text = news.sourceName
            tvAuthor.text = news.author
            Glide.with(requireContext())
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
                .into(ivNews)

            updateUIBookmarked()
        }


    }

    private fun updateUIBookmarked() {

        if (news.isBookmarked) {
            Glide.with(requireContext())
                .load(R.drawable.ic_bookmarked)
                .into(ivBookmark)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.ic_bookmark_border)
                .into(ivBookmark)
        }


    }

    private fun openWebPage(url: String?) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(intent)
    }


}