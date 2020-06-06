package com.example.novelreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(){

    private lateinit var authorListRecyclerView: RecyclerView
    private lateinit var authorListViewAdapter: RecyclerView.Adapter<*>
    private lateinit var authorListViewManager: RecyclerView.LayoutManager
    private val dummyAuthorList = arrayOf("Author1","Author2","Author3","Author4","Author5","Author6","Author7","Author8")

    private lateinit var bookListRecyclerView: HorizontalCarouselRecyclerView
    private lateinit var bookListViewAdapter: RecyclerView.Adapter<*>
    private lateinit var bookListViewManager: RecyclerView.LayoutManager
    private val dummyBookList = arrayOf(R.drawable.hp_4, R.drawable.hp_4,R.drawable.hp_7, R.drawable.hp_7)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        authorListViewManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        authorListViewAdapter = AuthorListAdapter(dummyAuthorList)

        authorListRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_author_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = authorListViewManager

            // specify an viewAdapter (see also next example)
            adapter = authorListViewAdapter

        }


        bookListViewAdapter = BookListAdapter(dummyBookList)
        bookListRecyclerView = view.findViewById(R.id.carousel_book_list)
        bookListRecyclerView.initialize(bookListViewAdapter)
        bookListRecyclerView.setViewsToChangeColor(listOf(R.id.image_book))

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}