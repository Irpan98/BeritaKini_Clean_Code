package id.itborneo.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.itborneo.core.R
import id.itborneo.core.data.model.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(val clickListener: (News) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var allNews = listOf<News>()
    fun setNews(allNews: List<News>) {
        this.allNews = allNews
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = allNews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(allNews[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {

            itemView.apply {
                tvTitle.text = news.title

                Glide.with(context)
                    .load(news.urlToImage)
                    .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
                    .into(ivNews)

                if (news.isBookmarked) {

                    tvBookmark.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_bookmarked,
                        0,
                        0,
                        0
                    )
                } else {

                    tvBookmark.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_bookmark_border,
                        0,
                        0,
                        0
                    )
                }


                setOnClickListener {
                    clickListener(news)
                }
            }


        }
    }

}