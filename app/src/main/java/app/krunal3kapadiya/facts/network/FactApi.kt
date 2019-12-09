package app.krunal3kapadiya.facts.network

import app.krunal3kapadiya.facts.network.models.Facts
import io.reactivex.Observable
import retrofit2.http.GET

interface FactApi {
        /**
         * fact data from api
         */
        @GET("/s/2iodh4vg0eortkl/facts.json")
        fun getFacts(): Observable<Facts>
}