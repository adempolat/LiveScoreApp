package com.adempolat.livescoreapp.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adempolat.livescoreapp.R
import com.adempolat.livescoreapp.adapter.CompositeAdapter
import com.adempolat.livescoreapp.databinding.FragmentHomeBinding
import com.adempolat.livescoreapp.model.response.Match
import com.adempolat.livescoreapp.utils.DataState
import com.adempolat.livescoreapp.utils.showAlert
import com.adempolat.livescoreapp.viewmodel.FilterType
import com.adempolat.livescoreapp.viewmodel.HomeViewModel
import com.adempolat.livescoreapp.viewmodel.SortOrder
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModelHome: HomeViewModel by viewModels()
    private var loadingDialog: Dialog? = null
    private lateinit var compositeAdapter: CompositeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeAdapter = CompositeAdapter(emptyList(), findNavController())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = compositeAdapter
        }

        binding.buttonFetchScores.setOnClickListener {
            getScores()
        }

        binding.buttonFilter.setOnClickListener {
            showFilterSheet()
        }

        binding.buttonSort.setOnClickListener {
            showSortSheet()
        }

        getObservers()
    }

    private fun getScores() {
        viewModelHome.getScores()
    }

    private fun getObservers() {
        viewModelHome.data.observe(viewLifecycleOwner, Observer {
            it.let {
                when (it) {
                    is DataState.Success -> {
                        showAlert(0, loadingDialog)
                        setAdapter(it.successData)
                    }
                    is DataState.Error -> {
                        showAlert(0, loadingDialog)
                    }
                    is DataState.Loading -> {
                        showAlert(1, loadingDialog)
                    }
                }
            }
        })
    }

    private fun setAdapter(matchList: List<Match>) {
        val groupedMatches = matchList.groupBy { it.league }
        val displayList = mutableListOf<Any>()
        groupedMatches.forEach { (league, matches) ->
            displayList.add(league)
            displayList.addAll(matches)
        }
        compositeAdapter.setData(displayList)
    }

    private fun showFilterSheet() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_filter, null)
        dialog.setContentView(view)

        view.findViewById<TextView>(R.id.filter_all).setOnClickListener {
            viewModelHome.setFilter(FilterType.ALL)
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.filter_schedule).setOnClickListener {
            viewModelHome.setFilter(FilterType.SCHEDULE)
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.filter_second_quarter).setOnClickListener {
            viewModelHome.setFilter(FilterType.SECOND_QUARTER)
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.filter_half_time).setOnClickListener {
            viewModelHome.setFilter(FilterType.HALF_TIME)
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.filter_finished).setOnClickListener {
            viewModelHome.setFilter(FilterType.FINISHED)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showSortSheet() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_sort, null)
        dialog.setContentView(view)

        view.findViewById<TextView>(R.id.sort_first_newest).setOnClickListener {
            viewModelHome.setSortOrder(SortOrder.FIRST_NEWEST)
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.sort_first_oldest).setOnClickListener {
            viewModelHome.setSortOrder(SortOrder.FIRST_OLDEST)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}