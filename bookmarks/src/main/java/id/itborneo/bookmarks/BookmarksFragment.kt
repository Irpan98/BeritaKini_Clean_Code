package id.itborneo.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.beritakini.R.id.action_bookmarksFragment_to_detailFragment
import id.itborneo.bookmarks.R.layout.fragment_bookmarks
import id.itborneo.core.data.model.News
import id.itborneo.core.ui.NewsAdapter
import id.itborneo.core.utils.constant.EXTRA_NEWS
import id.itborneo.core.utils.mapperUtils.DataMapper
import id.itborneo.core.utils.uiUtils.AppbarUtils
import id.itborneo.core.utils.uiUtils.BottomNavigationUtils
import id.itborneo.core.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import org.koin.android.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: NewsAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_bookmarks, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initRecyclerView()
        initNav(view)
        getData()
    }

    private fun initView(view: View) {
        AppbarUtils.title(view, "Bookmarks")
        BottomNavigationUtils.visible(requireActivity())

    }

    private fun navAction(news: News) {

        val bundle = bundleOf(
            EXTRA_NEWS to news,

            )
        navController.navigate(
            action_bookmarksFragment_to_detailFragment,
            bundle
        )

    }

    private fun initRecyclerView() {


        adapter = NewsAdapter {
            navAction(it)
        }
        rvBookmarks.adapter = adapter
        rvBookmarks.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initNav(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun getData() {
        viewModel.getAllNews().observe(viewLifecycleOwner) {

            val data = it.data
            if (data != null) {
                val bookmarked = mutableListOf<News>()

                DataMapper.newsEntityToModel(data).forEach { news ->
                    if (news.isBookmarked) {
                        bookmarked.add(news)
                    }
                }

                adapter.setNews(bookmarked)
            }
        }
    }


}