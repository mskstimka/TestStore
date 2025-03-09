package com.test.teststore.app.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.teststore.R
import com.test.teststore.app.App
import com.test.teststore.app.utils.setImageByURL
import com.test.teststore.app.utils.subscribeToFlow
import com.test.teststore.databinding.FragmentDetailsBinding
import com.test.teststore.domain.models.Product
import javax.inject.Inject


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()

    @Inject
    lateinit var dViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)

        (requireActivity().applicationContext as App).appComponent.inject(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToFlow()
        dViewModel.getDetailsOfProduct(args.id)
    }

    private fun bindView(product: Product) = with(binding) {

        ivAvatar.setImageByURL(product.image.toString())

        tvTitle.text = product.title
        tvDescription.text = product.description
        tvCategory.text = product.category
        tvPrice.text = getString(R.string.price_text, product.price.toString())

        ivBackPressed.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun subscribeToFlow() = with(dViewModel) {
        actionProduct.subscribeToFlow(
            lifecycleOwner = viewLifecycleOwner
        ) { product ->
            bindView(product)
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