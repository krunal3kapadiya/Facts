package app.krunal3kapadiya.facts.ui.factsList

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.krunal3kapadiya.facts.Injection
import app.krunal3kapadiya.facts.R
import app.krunal3kapadiya.facts.network.FactsRows
import kotlinx.android.synthetic.main.activity_facts_list.*

class FactsListActivity : AppCompatActivity() {
    lateinit var factsViewModel: FactsViewModel
    lateinit var factsListAdapter: FactsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_list)

        val factsList = ArrayList<FactsRows>()
        factsListAdapter = FactsListAdapter(factsList)
        rv_fact.layoutManager = LinearLayoutManager(this)
        rv_fact.adapter = factsListAdapter
        val viewModelFactory = Injection.provideFactsViewModel()
        factsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(FactsViewModel::class.java)

        if (isInternetAvailable()) {
            factsViewModel.fetchAndDisplayData()
            factsViewModel.isLoading.observe(this, Observer {
                if (it) {
                    vf_list.displayedChild = 1
                } else {
                    vf_list.displayedChild = 0
                }
            })
            factsViewModel.factsLiveData.observe(this, Observer {
                factsList.clear()
                factsList.addAll(it)
                factsListAdapter.notifyDataSetChanged()
            })
            factsViewModel.titleLiveData.observe(this, Observer {
                supportActionBar?.title = it
            })

        } else {
            vf_list.displayedChild = 2 // displaying no internet connection
            bt_internet_on.setOnClickListener {
                this@FactsListActivity.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
        }
    }

    // checking internet
    @Suppress("DEPRECATION")
    fun isInternetAvailable(): Boolean {
        var result = false
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}
