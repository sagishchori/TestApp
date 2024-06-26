package sagi.shchori.testapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import sagi.shchori.testapp.Result
import sagi.shchori.testapp.databinding.FragmentHomeBinding
import sagi.shchori.testapp.ui.UiState
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    @Inject private lateinit var adapter: Adapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textView
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }

            adapter = this@HomeFragment.adapter
        }


        homeViewModel.itemsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.updateData(it)
            }
        }

        homeViewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                UiState.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                UiState.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textView.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }

                UiState.FAILURE -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.INVISIBLE
                    binding.textView.visibility = View.VISIBLE
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}