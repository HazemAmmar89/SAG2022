package com.example.sagapp.commands

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.chinalwb.slidetoconfirmlib.ISlideListener
import com.example.features.calls.ContactLoader
import com.example.features.calls.FirebasePhoneCallHelper
import com.example.sagapp.R
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.commands.data.remote.CommandsViewModel
import com.example.sagapp.databinding.FragmentCommuncationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunicationFragment : BaseFragment<FragmentCommuncationBinding, CommandsViewModel>() {
    override val mViewModel: CommandsViewModel by viewModels()



    private lateinit var firebasePhoneCallHelper: FirebasePhoneCallHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_communcation, container, false)

        firebasePhoneCallHelper = FirebasePhoneCallHelper(requireContext())
        firebasePhoneCallHelper.startListenForCalls()

        // Load contacts into Firebase
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            val contactLoader = ContactLoader(requireContext())
            contactLoader.loadContacts()
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }

        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val contactLoader = ContactLoader(requireContext())
            contactLoader.loadContacts()
        } else {
            // Handle permission denied
        }
    }

    override fun onFragmentReady() {
        mViewModel.readMessagesFromFirebase()
        mViewModel.whatsappMessageFromFirebase()
        mViewModel.reminderMessageFromFirebase()
        subscribeToObservers()
        binding.slideToConfirm.slideListener = object : ISlideListener {
            override fun onSlideStart() {
            }

            override fun onSlideMove(percent: Float) {
            }

            override fun onSlideCancel() {

            }

            override fun onSlideDone() {
                navigateSafe(
                    R.id.action_communicationFragment_to_screensActivity,
                    container = R.id.frag_host
                )
            }
        }
    }

    private fun subscribeToObservers() {
//        mViewModel.apply {
//            observe(mViewModel.viewState) {
//                handleUiState(it)
//            }
//        }
    }

//    private fun handleUiState(action: FireBaseCommand) {
//        when (action) {
//            is FireBaseCommand.ReadCommand -> {
//                Log.e("firebase", "handleUiState: "+action.message )
//            }
//        }
//    }


}