package com.test.teststore.app.screens.main

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.teststore.app.App
import com.test.teststore.app.utils.subscribeToFlow
import com.test.teststore.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    private val titleAdapter by lazy {
        TitleAdapter(navigate = {

        })
    }

    private val listAdapter by lazy { ProductsAdapter() }
    private val concatAdapter by lazy { ConcatAdapter(titleAdapter, listAdapter) }

    @Inject
    lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)

        (requireActivity().applicationContext as App).appComponent.inject(this)


        mViewModel.getAllProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToFlow()
        initAdapter()
    }

    private fun initAdapter() = with(binding) {
        root.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        root.addItemDecoration(CenteredItemDecoration(16))
        root.adapter = concatAdapter

        titleAdapter.submitList(listOf(TitleModel()))

    }

    private fun subscribeToFlow() = with(mViewModel) {
        actionProduct.subscribeToFlow(
            lifecycleOwner = viewLifecycleOwner
        ) { list ->
            listAdapter.submitList(list)
        }
        actionMessage.subscribeToFlow(lifecycleOwner = viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

class CenteredItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.left = space / 2
        outRect.right = space / 2
        outRect.bottom = space
    }
}