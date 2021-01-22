package com.info.retrofitplaceholder.presenter

import com.info.retrofitplaceholder.view.IMainActivityView
import com.info.retrofitplaceholder.pojo.Popular

class MainActivityPresenter constructor(private val IMainActivityView: IMainActivityView) :
    IMainActivityPresenter {
    override fun addFilm(popular: Popular) {
        for (film in popular.results) {
            IMainActivityView.setFilm(film)
        }
    }
}