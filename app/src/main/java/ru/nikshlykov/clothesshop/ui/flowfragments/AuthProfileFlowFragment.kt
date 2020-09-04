package ru.nikshlykov.clothesshop.ui.flowfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import ru.nikshlykov.clothesshop.App
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.ui.OnChildFragmentInteractionListener
import ru.nikshlykov.clothesshop.viewmodels.AuthProfileFlowViewModel
import ru.nikshlykov.clothesshop.viewmodels.ViewModelFactory
import javax.inject.Inject

class AuthProfileFlowFragment : Fragment(), OnChildFragmentInteractionListener {

    private lateinit var authProfileFlowViewModel: AuthProfileFlowViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var navController: NavController

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authProfileFlowViewModel = viewModelFactory.create(AuthProfileFlowViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flow_fragment_auth_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    when (navController.currentBackStackEntry?.destination?.id){
                        R.id.nav_auth, R.id.nav_profile -> {
                            this.remove()
                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                        else -> navController.popBackStack()
                    }
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.flow_fragment_auth_profile___nav_host) as NavHostFragment
        navController = navHostFragment.navController

        // TODO убрать название этого фрагмента из тулбара.
    }

    override fun onStart() {
        super.onStart()
        if (authProfileFlowViewModel.isUserAuthenticated()) {
            navController.navigate(R.id.nav_profile)
        }
    }

    override fun onChildFragmentInteraction(navDirections: NavDirections) {
        if (navDirections.actionId != R.id.action_nav_profile_to_nav_auth) {
            navController.navigate(navDirections)
        } else {
            navController.popBackStack()
        }

        // TODO Сделать в RegistrationFragment кнопку <- на тулбаре.
    }
}