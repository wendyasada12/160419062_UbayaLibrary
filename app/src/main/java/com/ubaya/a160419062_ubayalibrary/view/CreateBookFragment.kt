package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.model.Book
import com.ubaya.a160419062_ubayalibrary.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_create_book.*


class CreateBookFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Book"
        buttonAddBook.setOnClickListener{

            var radio =
                view.findViewById<RadioButton>(radioGroupCategory.checkedRadioButtonId)
            var books = Book(
                textIdBook.text.toString(),
                textName.text.toString(),
                radio.text.toString(),
                textDesc.text.toString(),
                textAuthor.text.toString(),
                textPages.text.toString(),
                textLanguage.text.toString(),
                textLink.text.toString(),
                textDateBook.text.toString(),
                textPublisher.text.toString())

            val listBook = listOf(books)
            viewModel.addBooks(listBook)
            Toast.makeText(view.context, "Success Add Book", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}