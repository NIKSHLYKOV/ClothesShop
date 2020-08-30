package ru.nikshlykov.clothesshop.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.flow_fragment_auth_profile___nav_host) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onStart() {
        super.onStart()
        if (authProfileFlowViewModel.isUserAuthenticated()) {
            navController.navigate(R.id.nav_profile)
        }
    }

    override fun messageFromChildToParent(message: String) {
        if (message == "auth success") {
            navController.navigate(R.id.nav_profile)
        } else if (message == "user log out"){
            //TODO Переделать так, чтобы мы возвращались к предыдущему AuthFragment, а не создавали новый
            // При использовании только popUpTo без destination navigate не работает. Он работает только, когда
            // AuthFragment был убит, и в стеке его нет.
            //navController.navigate(ProfileFragmentDirections.actionNavProfileToNavAuth())
            navController.navigate(R.id.action_nav_profile_to_nav_auth)
        }
    }
}