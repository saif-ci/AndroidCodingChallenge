package com.example.otchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.otchallenge.databinding.ActivityMainBinding
import com.example.otchallenge.features.bookslist.BookListContract
import com.example.otchallenge.features.bookslist.BookPresentationObject
import com.example.otchallenge.features.bookslist.ui.BooksListAdapter
import com.example.otchallenge.repository.api.BooksRepository
import javax.inject.Inject

private const val LOADING_VIEW = 0
private const val LIST_VIEW = 1
private const val ERROR_VIEW = 2

class MainActivity : AppCompatActivity(), BookListContract.View {

	@Inject
	lateinit var presenter: BookListContract.Presenter

	@Inject
	lateinit var repository: BooksRepository

	private lateinit var binding: ActivityMainBinding
	private lateinit var adapter: BooksListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		(application as MyApplication).appComponent.inject(this)
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}


		binding.toolbar.setTitle(R.string.app_name)
		setupRecyclerView(binding.list)

	}

	private fun setupRecyclerView(recyclerView: RecyclerView) {
		adapter = BooksListAdapter()
		recyclerView.run {
			val columnTotal = resources.getInteger(R.integer.column_total)
			if (columnTotal > 1) {
				layoutManager = GridLayoutManager(context, columnTotal)
			} else {
				layoutManager = LinearLayoutManager(context)
				addItemDecoration(
					DividerItemDecoration(
						context,
						DividerItemDecoration.VERTICAL
					)
				)
			}
			adapter = this@MainActivity.adapter
		}
	}

	override fun onStart() {
		super.onStart()
		presenter.attach(this)
	}

	override fun onStop() {
		presenter.detach()
		super.onStop()
	}


	override fun showBooks(books: List<BookPresentationObject>) {
		binding.main.displayedChild = LIST_VIEW
		adapter.submitList(books)
	}

	override fun showError(@StringRes errorMessage: Int) {
		binding.error.message.setText(errorMessage)
		binding.main.displayedChild = ERROR_VIEW
	}

	override fun showLoading() {
		binding.main.displayedChild = LOADING_VIEW
	}
}
