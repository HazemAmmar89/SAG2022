package com.example.sagapp.commands


import androidx.fragment.app.viewModels
import com.chinalwb.slidetoconfirmlib.ISlideListener
import com.example.sagapp.R
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.commands.data.remote.CommandsViewModel
import com.example.sagapp.databinding.FragmentCommuncationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunicationFragment : BaseFragment<FragmentCommuncationBinding, CommandsViewModel>() {
    override val mViewModel: CommandsViewModel by viewModels()

    override fun onFragmentReady() {
        mViewModel.readMessagesFromFirebase()
        mViewModel.whatsappMessageFromFirebase()
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