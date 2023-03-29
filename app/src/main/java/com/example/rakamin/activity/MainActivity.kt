package com.example.rakamin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rakamin.R
import com.example.rakamin.adapter.AllnewsAdapter
import com.example.rakamin.adapter.BreakingAdapter
import com.example.rakamin.model.ArticleModel
import com.example.rakamin.model.NewsModel
import com.example.rakamin.network.ApiClient.getApiClient
import com.example.rakamin.network.ApiInterface
import com.example.rakamin.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var strCountry: String? = null
    var strKeyword: String? = ""
    var page = 1
    var pageSize = 100
    var modelArticle: MutableList<ArticleModel> = ArrayList()
    var breakingAdapter: BreakingAdapter? = null
    var allnewsAdapter: AllnewsAdapter? = null
    lateinit var layoutManager: LinearLayoutManager
    var isLoading = false
    companion object {
        const val API_KEY = "525328583ea04128a2f17f569cdf75bf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)

//        setup endless scroll
        setupEndless()

//        setup recyclerview
        setupRecyclerview()

//        get data breaking news
        getBreakingNews()

//        get data all news
        getAllNews()
    }

    private fun setupRecyclerview() {
        rvBeritaTrkni.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvBeritaTrkni.setHasFixedSize(true)

        rvSemuaBer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSemuaBer.setHasFixedSize(true)
    }

    private fun getAllNews() {
        strCountry = Utils.getCountry()
        isLoading = true
        progressBar.visibility = View.VISIBLE
//        set api
        val apiInterface = getApiClient().create(ApiInterface::class.java)
        val call = apiInterface.getEverything(strCountry, strKeyword, page, pageSize, API_KEY)
        call.enqueue(object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful && response.body() != null){
                    modelArticle = response.body()?.articles as MutableList<ArticleModel>
                    allnewsAdapter = AllnewsAdapter(modelArticle, applicationContext)
                    rvSemuaBer.adapter = allnewsAdapter
                }
                progressBar.visibility = View.GONE
                isLoading = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getBreakingNews() {
        strCountry = Utils.getCountry()

//        set api
        val apiInterface = getApiClient().create(ApiInterface::class.java)
        val call = apiInterface.getHeadlines(strCountry, API_KEY)
        call.enqueue(object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful && response.body() != null){
                    modelArticle = response.body()?.articles as MutableList<ArticleModel>
                    breakingAdapter = BreakingAdapter(modelArticle, applicationContext)
                    rvBeritaTrkni.adapter = breakingAdapter
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupEndless(){
        rvSemuaBer.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = allnewsAdapter?.itemCount
                val pastItemsVisible = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading){
                    if (visibleItemCount + pastItemsVisible >= totalItemCount!!){
                        page++
                        getAllNews()
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}