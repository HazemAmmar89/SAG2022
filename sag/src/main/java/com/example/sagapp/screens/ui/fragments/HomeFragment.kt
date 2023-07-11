package com.example.sagapp.screens.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sagapp.R
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var _binding :FragmentHomeBinding
     val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.setOnClickListener {
            navigateSafe(R.id.action_homeFragment_to_addAlarmFragment, container = R.id.frag_host)
        }
    }
}




