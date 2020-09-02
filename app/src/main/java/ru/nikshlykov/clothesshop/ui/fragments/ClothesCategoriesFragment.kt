package ru.nikshlykov.clothesshop.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.nikshlykov.clothesshop.App
import ru.nikshlykov.clothesshop.ui.adapters.ClothesCategoriesRvAdapter
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.data.models.ClothesCategory
import ru.nikshlykov.clothesshop.ui.OnChildFragmentInteractionListener
import ru.nikshlykov.clothesshop.ui.OnItemClickListener
import ru.nikshlykov.clothesshop.viewmodels.ClothesCategoriesViewModel
import ru.nikshlykov.clothesshop.viewmodels.ViewModelFactory
import javax.inject.Inject

class ClothesCategoriesFragment : Fragment() {

    private lateinit var clothesCategoriesViewModel: ClothesCategoriesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var onChildFragmentInteractionListener: OnChildFragmentInteractionListener

    private val categoriesRecyclerViewAdapter: ClothesCategoriesRvAdapter =
        ClothesCategoriesRvAdapter()

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
        clothesCategoriesViewModel = viewModelFactory.create(ClothesCategoriesViewModel::class.java)
        categoriesRecyclerViewAdapter.attachOnItemClickListener(object :
            OnItemClickListener<ClothesCategory> {
            override fun onItemClick(model: ClothesCategory, v: View) {
                val navDirections = ClothesCategoriesFragmentDirections.actionNavClothesCategoriesToCategoryFragment()
                onChildFragmentInteractionListener.onChildFragmentInteraction(navDirections)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_clothes_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesRecyclerView: RecyclerView =
            view.findViewById(R.id.fragment_clothes_categories___recycler_view___categories)
        categoriesRecyclerView.adapter = categoriesRecyclerViewAdapter
        categoriesRecyclerView.layoutManager = LinearLayoutManager(context)

        clothesCategoriesViewModel.categories.observe(viewLifecycleOwner, Observer {
            categoriesRecyclerViewAdapter.setCategoriesList(it)
        })

        clothesCategoriesViewModel.categoriesRequest()
    }

    override fun onDestroy() {
        super.onDestroy()
        categoriesRecyclerViewAdapter.detachOnItemClickListener()
    }
}
