package com.rollcake.tripPhoto.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.firebase.ui.auth.AuthUI
import com.rollcake.tripPhoto.R
import com.rollcake.tripPhoto.authentication.AuthenticationActivity
import com.rollcake.tripPhoto.base.BaseFragment
import com.rollcake.tripPhoto.databinding.FragmentSettingBinding
import com.rollcake.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject

class SettingFragment : BaseFragment(){

    override val _viewModel: SettingViewModel by inject()

    private lateinit var binding: FragmentSettingBinding

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)

        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.logout_btn).setOnClickListener {
            logout()
        }

        view.findViewById<Button>(R.id.delete_db_btn).setOnClickListener {
            _viewModel.deleteTripData()
        }
    }

    private fun logout(){
        _viewModel.showToast.value = getString(R.string.logout_success)
        AuthUI.getInstance().signOut(this.requireContext()) .addOnCompleteListener {
            requireActivity().startActivity(Intent(this.context , AuthenticationActivity::class.java).addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY))
        }
    }

}