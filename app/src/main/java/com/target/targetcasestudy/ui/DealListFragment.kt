package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.viewmodels.DealsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealListFragment : Fragment() {

  private val dealsViewModel: DealsViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_deal_list, container, false)

    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.layoutManager = LinearLayoutManager(requireContext())
    val dealItemAdapter = DealItemAdapter()
    recyclerView.adapter = dealItemAdapter

    dealsViewModel.getAllDeals()
    dealsViewModel.getDealsLiveData().observe(viewLifecycleOwner,
      Observer { deals ->
        dealItemAdapter.setData(deals)
      })

    return view
  }
}
