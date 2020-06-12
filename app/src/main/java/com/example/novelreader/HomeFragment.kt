package com.example.novelreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*

class HomeFragment : Fragment(){

    private lateinit var authorListRecyclerView: RecyclerView
    private lateinit var authorListViewAdapter: RecyclerView.Adapter<*>
    private lateinit var authorListViewManager: RecyclerView.LayoutManager
    private val dummyAuthorList = arrayOf("Author1","Author2","Author3","Author4","Author5","Author6","Author7","Author8")

    private lateinit var bookListRecyclerView: HorizontalCarouselRecyclerView
    private val bookListViewAdapter = BookListAdapter()
    private val dummyBookList = listOf(R.drawable.hp_4, R.drawable.hp_4,R.drawable.hp_7, R.drawable.hp_7)

    private lateinit var recommendedBookListRecyclerView: RecyclerView
    private lateinit var recommendedBookListViewAdapter: RecommendedBookListAdapter
    private lateinit var recommendedBookListViewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        authorListViewManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        authorListViewAdapter = AuthorListAdapter(dummyAuthorList)

        authorListRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_author_list).apply {
            setHasFixedSize(true)
            layoutManager = authorListViewManager
            adapter = authorListViewAdapter
        }

        val helper: PagerSnapHelper = PagerSnapHelper()
        bookListRecyclerView = view.findViewById(R.id.carousel_book_list)
        helper.attachToRecyclerView(bookListRecyclerView)
        bookListRecyclerView.initialize(bookListViewAdapter)
        bookListRecyclerView.setViewsToChangeColor(listOf(R.id.image_book))
        bookListViewAdapter.setItems(dummyBookList)

        recommendedBookListViewManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        recommendedBookListViewAdapter = RecommendedBookListAdapter(dummyBookList)

        recommendedBookListRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_recommended_book_list).apply {
            setHasFixedSize(true)
            layoutManager = recommendedBookListViewManager
            adapter = recommendedBookListViewAdapter
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}