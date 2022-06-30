package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.model.Book
import com.ubaya.a160419062_ubayalibrary.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment() {
    private lateinit var viewModel : ListViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(ListViewModel::class.java)

        // ADD Book (default)
        var books = Book("0001",
            "Eloquent JavaScript, Third Edition",
            "Informatics",
            "JavaScript lies at the heart of almost every modern web application, from social apps like Twitter to browser-based game frameworks like Phaser and Babylon. Though simple for beginners to pick up and play with, JavaScript is a flexible, complex language that you can use to build full-scale applications.",
            "Marijn Haverbeke",
            "472",
            "English",
            "https://images-na.ssl-images-amazon.com/images/I/51InjRPaF7L._SX377_BO1,204,203,200_.jpg",
            "2018-12-04",
            "No Starch Press",
            false, false, false, false)

        var books2 = Book("0002",
            "All Tomorrows",
            "Sci-Fi",
            "The story begins in the near future, as burgeoning population pressures force humanity to terraform and colonize Mars. After a brief but violent civil war between the two planets, the genetically engineered survivors begin a new wave of colonization, spreading across the galaxy.",
            "C.M.Kosemen",
            "111",
            "English",
            "https://upload.wikimedia.org/wikipedia/en/2/2a/All_tomorrows_cover.jpg",
            "2006-10-04",
            "Nemo Ramjet",
            false, false, false, false)

        var books3 = Book("0003",
            "Nineteen Eighty-Four",
            "Dystopia Fiction",
            "Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching...",
            "George Orwell",
            "276",
            "English",
            "https://upload.wikimedia.org/wikipedia/commons/c/c3/1984first.jpg",
            "1949-06-08",
            "Kjell Hakansson Forlag",
            false, false, false, false)

        var books4 = Book("0004",
            "Fluent Python",
            "Informatics",
            "Python simplicity lets you become productive quickly, but this often means you aren’t using everything it has to offer. With this hands-on guide, you’ll learn how to write effective, idiomatic Python code by leveraging its best and possibly most neglected features.",
            "Luciano Ramalho",
            "792",
            "English",
            "https://images-na.ssl-images-amazon.com/images/I/41R+fNX-akL._SX379_BO1,204,203,200_.jpg",
            "2015-07-24",
            "O Reilly Media",
            false, false, false, false)

        var books5 = Book("0005",
            "Rethinking Productivity in Software Engineering",
            "Software",
            "Get the most out of this foundational reference and improve the productivity of your software teams. This open access book collects the wisdom of the 2017 \\\"Dagstuhl\\\" seminar on productivity in software engineering, a meeting of community leaders, who came together with the goal of rethinking traditional definitions and measures of productivity.",
            "Caitlin Sadowski, Thomas Zimmermann",
            "310",
            "English",
            "https://images-na.ssl-images-amazon.com/images/I/41TT14ilLUL._SX348_BO1,204,203,200_.jpg",
            "2019-05-11",
            "Apress",
            false, false, false, false)

        var listBooks= listOf(books, books2, books3, books4, books5)
        viewModel.addBooks(listBooks)

        // SELECT
        viewModel.refresh()

        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=bookListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility= View.GONE
            textError.visibility= View.GONE
            progressLoad.visibility= View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing= false
        }
    }

    private fun observeViewModel() {

        viewModel.bookLiveData.observe(viewLifecycleOwner){
            bookListAdapter.updateBookList(it)
        }
        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner){
            textError.visibility= if(it)View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recView.visibility= View.VISIBLE
                progressLoad.visibility= View.GONE

            }else{
                recView.visibility= View.GONE
                progressLoad.visibility= View.VISIBLE
            }
        }

    }

}