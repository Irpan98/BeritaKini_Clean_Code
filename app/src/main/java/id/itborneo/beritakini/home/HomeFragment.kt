package id.itborneo.beritakini.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.beritakini.R
import id.itborneo.core.R.id.rl_error
import id.itborneo.core.R.id.svLoading
import id.itborneo.core.data.Resource
import id.itborneo.core.data.model.News
import id.itborneo.core.ui.NewsAdapter
import id.itborneo.core.utils.constant.EXTRA_NEWS
import id.itborneo.core.utils.mapperUtils.DataMapper
import id.itborneo.core.utils.uiUtils.BottomNavigationUtils
import id.itborneo.core.utils.uiUtils.error.ErrorUtils
import id.itborneo.core.utils.uiUtils.loading.ShimmerUtils
import id.itborneo.core.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.partial_search_bar.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private lateinit var navController: NavController
    private lateinit var listNews: List<News>

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visibilityBottomNav()
        initRecyclerView()
        initializeSearchBar()
        initNav(view)
        getData()
    }

    private fun visibilityBottomNav() {
        BottomNavigationUtils.visible(requireActivity())

    }

    private fun getData() {
        if (!::listNews.isInitialized) {
            loading()
        }

        viewModel.getAllNews().observe(viewLifecycleOwner) {
            val data = it.data
            if (data != null) {

                when (it) {
                    is Resource.Loading -> loading()
                    is Resource.Success -> {
                        loading(false)
                        listNews = DataMapper.newsEntityToModel(data)
                        adapter.setNews(listNews)
                    }
                    is Resource.Error -> {
                        loading(false)
                        ErrorUtils.showError(
                            requireActivity().findViewById(rl_error),
                            it.message ?: getString(R.string.something_wrong)
                        )
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {


        adapter = NewsAdapter {
            navAction(it)
        }
        rvNews.adapter = adapter
        rvNews.apply {

            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun navAction(news: News) {

        val bundle = bundleOf(
            EXTRA_NEWS to news,

            )
        navController.navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundle
        )

    }

    private fun initializeSearchBar() {
        search_bar_view.setOnClickListener {
            search_bar_view.onActionViewExpanded()
        }

        search_bar_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                if (newText != null) {
                    val searchList = listNews.filter {
                        it.title.contains(newText, true) ||
                                it.title.contains(newText, true)

                    }

                    if (searchList.isEmpty()) {
                        adapter.setNews(searchList)

                        queryToEndPoint(newText) {
                            adapter.setNews(it)

                        }

                    } else {
                        adapter.setNews(searchList)

                    }
                }

                return true
            }

        })


    }

    private fun queryToEndPoint(query: String, searchedNews: (List<News>) -> Unit) {
        loading()

        viewModel.searchNews(query).observe(viewLifecycleOwner) {

            val data = it.data
            if (data != null) {

                when (it) {
                    is Resource.Loading -> loading()
                    is Resource.Success -> {
                        loading(false)
                        searchedNews(DataMapper.newsEntityToModel(data))
                    }
                    is Resource.Error -> {
                        loading(false)
                        ErrorUtils.showError(
                            requireActivity().findViewById(rl_error),
                            it.message ?: getString(R.string.something_wrong)
                        )
                    }
                }
            }
        }

    }


    private fun initNav(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun loading(showIt: Boolean = true) {
        val shimmerLoading = ShimmerUtils(requireActivity().findViewById(svLoading))
        if (showIt) {
            shimmerLoading.showLoading()
        } else {
            shimmerLoading.hideLoading()
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()
        rvNews.adapter = null

    }


}