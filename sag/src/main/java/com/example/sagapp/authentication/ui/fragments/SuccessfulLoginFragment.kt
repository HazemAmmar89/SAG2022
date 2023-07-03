package com.example.sagapp.authentication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sagapp.R
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.databinding.FragmentSuccessfulLoginBinding


class SuccessfulLoginFragment : Fragment() {
    private lateinit var _binding: FragmentSuccessfulLoginBinding
    private val binding get() = _binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSuccessfulLoginBinding.inflate(inflater, container, false)
        binding.goToLoginTxt.setOnClickListener {
            navigateSafe(
                SuccessfulLoginFragmentDirections.actionSuccessfulLoginFragmentToLoginFragment2(),
                container = R.id.frag_host
            )
        }
        return binding.root
    }
}