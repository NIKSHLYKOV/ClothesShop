package ru.nikshlykov.clothesshop.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.nikshlykov.clothesshop.App
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.data.models.Product
import ru.nikshlykov.clothesshop.ui.OnChildFragmentInteractionListener
import ru.nikshlykov.clothesshop.ui.adapters.OnItemClickListener
import ru.nikshlykov.clothesshop.ui.adapters.GoodsRvAdapter
import ru.nikshlykov.clothesshop.viewmodels.CategoryViewModel
import ru.nikshlykov.clothesshop.viewmodels.ViewModelFactory
import javax.inject.Inject

class CategoryFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var goodsRvAdapter: GoodsRvAdapter = GoodsRvAdapter()

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
        categoryViewModel = viewModelFactory.create(CategoryViewModel::class.java)
        goodsRvAdapter.attachOnItemClickListener(object :
            OnItemClickListener<Product> {
            override fun onItemClick(model: Product, v: View) {
                val navDirections =
                    CategoryFragmentDirections.actionNavCategoryToProductFragment(model.id)
                onChildFragmentInteractionListener.onChildFragmentInteraction(navDirections)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productsRecyclerView: RecyclerView =
            view.findViewById(R.id.fragment_category___recycler_view___products)
        productsRecyclerView.adapter = goodsRvAdapter

        productsRecyclerView.adapter = goodsRvAdapter
        productsRecyclerView.layoutManager = GridLayoutManager(context, 2)

        categoryViewModel.product.observe(viewLifecycleOwner, Observer {
            goodsRvAdapter.setProductList(it)
        })

        categoryViewModel.productsRequest()
    }

    override fun onDestroy() {
        super.onDestroy()
        goodsRvAdapter.detachOnItemClickListener()
    }
}