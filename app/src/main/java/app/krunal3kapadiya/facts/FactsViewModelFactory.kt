package app.krunal3kapadiya.facts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.krunal3kapadiya.facts.ui.factsList.FactsViewModel

/**
 * @author krunal kapadiya
 * @link https://krunal3kapadiya.app
 * @date 14,April,2019
 */

class FactsViewModelFactory : ViewModelProvider.Factory {

    /**
     * providing fact view model
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactsViewModel::class.java)) {
            return FactsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}