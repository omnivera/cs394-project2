package com.mert.omnivera.service

import com.mert.omnivera.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CryptoAPI {

    @GET("omnivera/Cryptos/main/crypto.json?token=ARBUI7KYAFGBVUDXTBEVH7DB24TVI")
    fun getData(): Observable<List<CryptoModel>>
}