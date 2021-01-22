package com.info.retrofitplaceholder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.info.retrofitplaceholder.api.NetworkService
import com.info.retrofitplaceholder.R
import com.info.retrofitplaceholder.presenter.MainActivityPresenter
import com.info.retrofitplaceholder.adapter.PopularAdapter
import com.info.retrofitplaceholder.pojo.Popular
import com.info.retrofitplaceholder.pojo.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), IMainActivityView {
    private var arrayList = ArrayList<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvFilms = findViewById<RecyclerView>(R.id.rvFilms)
        rvFilms.layoutManager = LinearLayoutManager(
            this@MainActivity,
            RecyclerView.VERTICAL, false
        )

        val mainActivityPresenter = MainActivityPresenter(this)

        NetworkService().getInstance()?.getJSONApi()?.getPopular()
            ?.enqueue(object : Callback<Popular?> {
                override fun onResponse(call: Call<Popular?>, response: Response<Popular?>) {
                    val post: Popular? = response.body()
                    mainActivityPresenter.addFilm(post!!)
                }

                override fun onFailure(call: Call<Popular?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun setFilm(result: Result) {
        val rvFilms = findViewById<RecyclerView>(R.id.rvFilms)
        arrayList.add(result)
        rvFilms.adapter = PopularAdapter(arrayList, baseContext)
    }
}