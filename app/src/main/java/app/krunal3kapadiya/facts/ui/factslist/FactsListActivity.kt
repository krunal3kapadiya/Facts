package app.krunal3kapadiya.facts.ui.factslist

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.krunal3kapadiya.facts.R
import app.krunal3kapadiya.facts.network.models.FactsRows
import app.krunal3kapadiya.facts.utils.Utility
import app.krunal3kapadiya.facts.viewmodels.FactsViewModel
import kotlinx.android.synthetic.main.activity_facts_list.*

class FactsListActivity : AppCompatActivity() {

    lateinit var factsViewModel: FactsViewModel
    lateinit var factsListAdapter: FactsListAdapter
    lateinit var factsList: ArrayList<FactsRows>
    var isFromSwipeRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_list)

        factsList = ArrayList()
        factsListAdapter = FactsListAdapter(factsList)
        rv_fact.layoutManager = LinearLayoutManager(this)
        rv_fact.adapter = factsListAdapter
        factsViewModel = ViewModelProviders.of(this)
            .get(FactsViewModel::class.java)


        swipe_refresh.setOnRefreshListener {
            // load data on refresh
            isFromSwipeRefresh = true
            loadData()
        }

        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                loadData()
            }
        }
        return true
    }

    private fun loadData() = if (Utility.isInternetAvailable(this)) {
        factsViewModel.fetchAndDisplayData()
        factsViewModel.isLoading.observe(this, Observer {
            if (it || isFromSwipeRefresh) {
                vf_list.displayedChild = 1
            } else {
                vf_list.displayedChild = 0
            }
            swipe_refresh.isRefreshing = false
            isFromSwipeRefresh = false
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
