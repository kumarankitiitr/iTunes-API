package `in`.programy.itunesapi.ui

import `in`.programy.itunesapi.R
import `in`.programy.itunesapi.util.RvAdapter
import `in`.programy.itunesapi.model.room.TunesDatabase
import `in`.programy.itunesapi.repository.TunesRepository
import `in`.programy.itunesapi.util.Resource
import `in`.programy.itunesapi.viewModel.TunesViewModel
import `in`.programy.itunesapi.viewModel.TunesViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        val database = TunesDatabase(this)
        val repository = TunesRepository(database)
        val viewModelFactory = TunesViewModelFactory(repository,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(TunesViewModel::class.java)

        viewModel.currentItems.observe(this, Observer {
            when(it){
                is Resource.Success -> {
                    it.data?.let {list ->
                        rvAdapter.differ.submitList(list)
                    }
                    hideProgressBar()
                }
                is Resource.Error -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()){
                        viewModel.getItems(query)
                        return true
                    }
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }

    private fun hideProgressBar(){
        pbMain.visibility = View.GONE
    }
    private fun showProgressBar(){
        pbMain.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        rvAdapter = RvAdapter()
        rvMain.apply {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(this@MainActivity,3)
        }
    }
}