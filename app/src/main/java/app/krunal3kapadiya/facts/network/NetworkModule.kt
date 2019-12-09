package app.krunal3kapadiya.facts.network

import app.krunal3kapadiya.facts.BuildConfig
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author krunal kapadiya
 * @link https://krunal3kapadiya.app
 * @date 14,April,2019
 */

object NetworkModule {
    /**
     * provides fact api
     */
    @JvmStatic
    fun provideFactsApi(): FactApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory
                    .createWithScheduler(Schedulers.io())
            )
            .build().create(FactApi::class.java)
    }
}