package com.adempolat.livescoreapp.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.adempolat.livescoreapp.adapter.MatchTitleAdapter
import com.adempolat.livescoreapp.databinding.FragmentHomeBinding
import com.adempolat.livescoreapp.model.response.Data
import com.adempolat.livescoreapp.utils.DataState
import com.adempolat.livescoreapp.utils.showAlert
import com.adempolat.livescoreapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModelHome : HomeViewModel by viewModels()
    private var loadingDialog : Dialog?  = null
    private lateinit var matchTitleAdapter: MatchTitleAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getObservers()
    }

    private fun getObservers() {
        viewModelHome.getScores()
        viewModelHome.data.observe(viewLifecycleOwner, Observer{
            it.let {
                when(it) {
                    is DataState.Success -> {
                        showAlert(0,loadingDialog)
                        if (it.successData.success) {
                            setAdapter(it.successData.data)
                        }
                    }
                    is DataState.Error -> {
                        showAlert(0,loadingDialog)
                    }
                    is DataState.Loading -> {
                        showAlert(1,loadingDialog)
                    }
                }
            }
        })
    }

    private fun setAdapter(matchList: List<Data>) {
        matchTitleAdapter = MatchTitleAdapter(matchList)
        binding?.rvMatches?.layoutManager = LinearLayoutManager(context)
        binding?.rvMatches?.adapter = matchTitleAdapter
    }

}