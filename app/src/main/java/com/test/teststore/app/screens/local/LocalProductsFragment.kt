package com.test.teststore.app.screens.local

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.teststore.app.App
import com.test.teststore.app.screens.main.ProductsAdapter
import com.test.teststore.app.utils.subscribeToFlow
import com.test.teststore.databinding.FragmentLocalProductsBinding
import javax.inject.Inject


class LocalProductsFragment : Fragment() {

    private var _binding: FragmentLocalProductsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var lViewmodel: LocalProductsViewModel

    private val listAdapter by lazy {
        ProductsAdapter(navigate = { id ->
            findNavController().navigate(
                LocalProductsFragmentDirections.actionLocalProductsFragmentToDetailsFragment(
                    id = id
                )
            )
        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocalProductsBinding.inflate(layoutInflater)

        (requireActivity().applicationContext as App).appComponent.inject(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        bindView()
        subscribeToFlow()

        lViewmodel.getLocalProducts()
    }

    private fun bindView() = with(binding){
        ivBackPressed.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() = with(binding) {
        rvRoot.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvRoot.adapter = listAdapter
    }

    private fun subscribeToFlow() = with(lViewmodel) {
        actionProduct.subscribeToFlow(
            lifecycleOwner = viewLifecycleOwner
        ) { list ->
            Log.d("STORE", list.toString())
            listAdapter.submitList(list)
        }

        actionMessage.subscribeToFlow(lifecycleOwner = viewLifecycleOwner) { message ->
            android.widget.Toast.makeText(
                requireContext(),
                message,
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }

}