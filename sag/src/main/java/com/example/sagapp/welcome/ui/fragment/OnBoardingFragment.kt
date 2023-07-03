package com.example.sagapp.welcome.ui.fragment

import com.example.sagapp.android.BaseFragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

import com.example.sagapp.R
import com.example.sagapp.android.extentions.gone
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.android.extentions.visible
import com.example.sagapp.databinding.FragmentOnBoardingBinding
import com.example.sagapp.welcome.ui.adapters.OnBoardAdapter
import com.example.sagapp.welcome.ui.viewmodel.OnBoardingAction
import com.example.sagapp.welcome.ui.viewmodel.OnBoardingViewModel
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>() {

    override val mViewModel: OnBoardingViewModel by viewModels()

    @Inject
    lateinit var onBoardAdapter: OnBoardAdapter
    override fun onFragmentReady() {


        mViewModel.getOnBoarding()
        subscribeToObservers()
        viewPager2Scrolling()
        initIndicator()
        binding.onboardFragmentNextTxt.setOnClickListener {
            binding.viewpagerOnboard.currentItem = binding.viewpagerOnboard.currentItem + 1
        }
        binding.onboardFragmentConfirmBtn.setOnClickListener {
            mViewModel.onBoardingWriteToFinish()
            navigateSafe(R.id.action_onBoardingFragment_to_loginFragment,container= R.id.frag_host)
        }
        binding.onboardFragmentSkipTxt.setOnClickListener {
            mViewModel.onBoardingWriteToFinish()
            navigateSafe(R.id.action_onBoardingFragment_to_loginFragment,container= R.id.frag_host)
        }

    }

    private fun viewPager2Scrolling() {
        binding.viewpagerOnboard.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.onboardFragmentConfirmBtn.visible()
                    binding.onboardFragmentNextTxt.gone()
                    binding.onboardFragmentSkipTxt.gone()
                } else {
                    binding.onboardFragmentConfirmBtn.gone()
                    binding.onboardFragmentNextTxt.visible()
                    binding.onboardFragmentSkipTxt.visible()
                }
                binding.indicator.onPageSelected(position)

            }

//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//            }
        })
    }

    private fun initIndicator() {
        binding.indicator.setSliderWidth(15f)
        binding.indicator.setSliderHeight(10f)
        binding.indicator.setSlideMode(IndicatorSlideMode.SMOOTH)
        binding.indicator.setIndicatorStyle(IndicatorStyle.CIRCLE)
        binding.indicator.setPageSize(3)
        binding.indicator.notifyDataChanged()
    }

    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: OnBoardingAction) {
        when (action) {
            is OnBoardingAction.OnBoardingData -> {
                onBoardAdapter.onBoardList = action.list
                binding.viewpagerOnboard.adapter = onBoardAdapter
            }
        }

    }

}