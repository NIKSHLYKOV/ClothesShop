package ru.nikshlykov.clothesshop.ui.flowfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.ui.OnChildFragmentInteractionListener

class GoodsFlowFragment : Fragment(),
    OnChildFragmentInteractionListener {

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (navController.currentBackStackEntry?.destination?.id == R.id.nav_clothes_categories) {
                        this.remove()
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    } else {
                        navController.popBackStack()
                    }
                }
            })

        (requireActivity() as AppCompatActivity).supportActionBar?.title = ""
    }

    override fun onChildFragmentInteraction(navDirections: NavDirections) {
        navController.navigate(navDirections)

        //TODO Разобраться, почему в стеке лежит два одинаковых фрагмента, которые являются startDestination.
    }
}