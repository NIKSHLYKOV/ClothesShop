package ru.nikshlykov.clothesshop.ui.flowfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.ui.OnChildFragmentInteractionListener

class GoodsFlowFragment : Fragment(), OnChildFragmentInteractionListener {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flow_fragment_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.flow_fragment_goods___nav_host) as NavHostFragment
        navController = navHostFragment.navController

        // TODO убрать название этого фрагмента из тулбара.
    }

    override fun onChildFragmentInteraction(navDirections: NavDirections) {
        navController.navigate(navDirections)
    }
}