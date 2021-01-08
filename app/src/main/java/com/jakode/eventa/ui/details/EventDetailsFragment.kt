package com.jakode.eventa.ui.details

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import com.jakode.eventa.R
import com.jakode.eventa.databinding.FragmentEventDetailsBinding
import com.jakode.eventa.model.Event
import com.jakode.eventa.ui.main.MainActivity
import com.jakode.eventa.utils.blur
import com.jakode.eventa.utils.makeStatusBarTransparent

class EventDetailsFragment : Fragment(R.layout.fragment_event_details) {
    private val args: EventDetailsFragmentArgs by navArgs()
    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var event: Event

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEventDetailsBinding.bind(view)
        (activity as MainActivity).showBottomNav(false)
        requireActivity().makeStatusBarTransparent()

        setupArgs()
        setupPrice()
        setupBlur()
        setupLocation()
        clickListener()
    }

    private fun setupLocation() {
        binding.locationImage.load(event.location) {
            transformations(RoundedCornersTransformation(20f))
            crossfade(true)
            crossfade(400)
            memoryCachePolicy(CachePolicy.ENABLED)
        }
    }

    private fun clickListener() {
        binding.apply {
            back.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setupBlur() {
        binding.apply {
            back.blur { it.radius(10) }
            likeAndShare.blur { it.radius(10) }
        }
    }

    private fun setupPrice() {
        val ss = SpannableString(event.price)
        ss.setSpan(RelativeSizeSpan(1.25f), 0, 6, 0)
        binding.price.text = ss
    }

    private fun setupArgs() {
        event = args.event
        binding.event = event
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}