package app.krunal3kapadiya.facts.ui.factsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.krunal3kapadiya.facts.network.FactApi
import app.krunal3kapadiya.facts.network.Facts
import app.krunal3kapadiya.facts.network.FactsRows
import app.krunal3kapadiya.facts.network.NetworkModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author krunal kapadiya
 * @link https://krunal3kapadiya.app
 * @date 14,April,2019
 */
class FactsViewModel : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val factsLiveData: MutableLiveData<List<FactsRows>> = MutableLiveData()
    val titleLiveData: MutableLiveData<String> = MutableLiveData()
    fun fetchAndDisplayData() {
        // fact api object
        val factApi: FactApi = NetworkModule.provideFactsApi()

        // fact observer
        val factsObservable: Observable<Facts> = factApi.getFacts()
        isLoading.postValue(true)
        disposable.add(factsObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isLoading.postValue(false)
                titleLiveData.postValue(it.title)
                factsLiveData.postValue(it.rowsList)
            }, { isLoading.postValue(false) })
        )
    }
}