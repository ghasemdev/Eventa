package com.jakode.eventa.ui.featured

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakode.eventa.R
import com.jakode.eventa.data.DataFake
import com.jakode.eventa.databinding.FragmentFeaturedBinding
import com.jakode.eventa.ui.adapter.ViewTypeAdapter
import com.jakode.eventa.ui.main.MainActivity
import com.jakode.eventa.utils.makeStatusBarTransparent
import com.jakode.eventa.utils.setImage

class FeaturedFragment : Fragment(R.layout.fragment_featured) {
    private var _binding: FragmentFeaturedBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFeaturedBinding.bind(view)
        (activity as MainActivity).showBottomNav(true)
        requireActivity().makeStatusBarTransparent()

        setupProfile()
        setupEvents()
    }

    private fun setupEvents() {
        binding.events.apply {
            adapter = ViewTypeAdapter(
                list = DataFake.getData().map { EventsViewType(it) }.toMutableList(),
                onItemActionListener = { position ->
                    val action =
                        FeaturedFragmentDirections.actionFeaturedFragmentToEventDetailsFragment(
                            DataFake.getData()[position]
                        )
                    findNavController().navigate(action)
                }
            )
        }
    }

    private fun setupProfile() {
        val url =
            "https://s3-alpha-sig.figma.com/img/d464/926d/edaa9ef47b41605f670bb146c29a1b65?Expires=1610928000&Signature=GfZp93BWok7bGyy8oCf-sb3InRiv420y-yNnwyBR9PgQda3nFKXwmtwhQrWH~iswjbLQF0XUNWJKnVZkA46sonX4oOWT1uL-X4S0fS46FH0N9xh1CfXwBoVJnQ-~TdqJrSNSoSLxtz7~831o0o-ptviByKOxQEPSWvKZYEKfqmbRimN~gWZb1OS1nUgUfSh0p7w6O1e04BSzuqMJtRDEPxRkhnlGhXmLISZxO18WF9uLXuPkp-cm7IppvjYMs~IHlswjhjAoYcnMPFU3BmSaalN0Jj6kfVs~20Y08q~d8tX3LMJdDVM~B2L-NbDOFYVKyngNAafhVAwqUysaLv~Cgg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
        setImage(binding.profile, url, isCircle = true)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}