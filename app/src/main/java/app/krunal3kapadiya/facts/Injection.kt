package app.krunal3kapadiya.facts

object Injection {
    /**
     * fact view model
     */
    @JvmStatic
    fun provideFactsViewModel(): FactsViewModelFactory {
        return FactsViewModelFactory()
    }
}
