package ru.nikshlykov.clothesshop.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import ru.nikshlykov.clothesshop.App
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.databinding.FragmentProfileBinding
import ru.nikshlykov.clothesshop.ui.OnChildFragmentInteractionListener
import ru.nikshlykov.clothesshop.viewmodels.ProfileViewModel
import ru.nikshlykov.clothesshop.viewmodels.ViewModelFactory
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var onChildFragmentInteractionListener: OnChildFragmentInteractionListener

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
        if (parentFragment?.parentFragment is OnChildFragmentInteractionListener) {
            onChildFragmentInteractionListener =
                parentFragment?.parentFragment as OnChildFragmentInteractionListener
        } else {
            throw RuntimeException(parentFragment?.parentFragment.toString() + " must implement OnChildFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel = viewModelFactory.create(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.viewModel = profileViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.fragment_profile___button___sign_out)
            .setOnClickListener {
                profileViewModel.signOut()
                val navDirections = ProfileFragmentDirections.actionNavProfileToNavAuth()
                onChildFragmentInteractionListener.onChildFragmentInteraction(navDirections)
            }
    }
}