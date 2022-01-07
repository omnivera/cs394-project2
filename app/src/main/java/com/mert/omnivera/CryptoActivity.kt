package com.mert.omnivera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mert.omnivera.adapter.CryptoRecyclerAdapter
import com.mert.omnivera.model.CryptoModel
import com.mert.omnivera.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_crypto.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CryptoActivity : AppCompatActivity(),CryptoRecyclerAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : CryptoRecyclerAdapter? = null
    private var compositeDisposable: CompositeDisposable? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)



        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        cryptoView.layoutManager = layoutManager

        loadData()


    }



    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)








    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}", Toast.LENGTH_LONG ).show()
    }

    private fun handleResponse(cryptoList : List<CryptoModel>){
        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            recyclerViewAdapter = CryptoRecyclerAdapter(it,this)
            cryptoView.adapter = recyclerViewAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()
    }

}