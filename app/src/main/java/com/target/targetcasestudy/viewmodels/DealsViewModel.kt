package com.target.targetcasestudy.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.DealItemViewData
import com.target.targetcasestudy.data.DealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(val dealsRepository: DealsRepository) : ViewModel() {
  private val dealsList = MutableLiveData<List<DealItemViewData>>()

  fun getDealsLiveData(): MutableLiveData<List<DealItemViewData>> {
    return dealsList
  }

  @SuppressLint("CheckResult")
  fun getAllDeals() {
    dealsRepository.deals
      .flatMap {
        Observable.fromIterable(it.products)
          .map { product ->
            DealItemViewData(
              id = product.id,
              title = product.title,
              description = product.description,
              salePrice = product.salePrice?.displayString,
              regularPrice = product.regularPrice.displayString,
              aisle = product.aisle,
              image = product.imageUrl
            )
          }
          .toList()
      }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        {
          dealsList.value = it.toList()
        }
      ) { error: Throwable ->
        Log.e(TAG, "getDeals: " + error.message)
      }
  }

  companion object {
    private const val TAG = "DealsViewModel"
  }
}