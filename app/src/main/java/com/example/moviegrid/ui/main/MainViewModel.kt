package com.example.moviegrid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviegrid.base.DisposingViewModel
import com.example.moviegrid.domain.entity.Movie
import com.example.moviegrid.domain.interactor.MovieUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    DisposingViewModel() {

    private val _movieListLiveData = MutableLiveData<List<Movie>>()
    val movieListLiveData: LiveData<List<Movie>>
        get() = _movieListLiveData

    private val _showProgressLiveData = MutableLiveData<Boolean>()
    val showProgressLiveData: LiveData<Boolean>
        get() = _showProgressLiveData

    fun getMovies(page: Int) {
        _showProgressLiveData.postValue(true)
        val disposableObserver =
            movieUseCase.execute(MovieUseCase.Params(page)).subscribeWith(getObserver())
        addDisposable(disposableObserver)
    }

    private fun getObserver(): DisposableObserver<List<Movie>> {
        return object : DisposableObserver<List<Movie>>() {
            override fun onComplete() {
                _showProgressLiveData.postValue(false)
            }

            override fun onNext(movieList: List<Movie>) {
                _movieListLiveData.postValue(movieList)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                _showProgressLiveData.postValue(false)
            }
        }
    }
}