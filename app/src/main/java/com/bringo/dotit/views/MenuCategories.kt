package com.bringo.dotit.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bringo.dotit.R
import com.bringo.dotit.adapters.CategoriesRVAdapter
import com.bringo.dotit.databinding.RestauMenuBinding
import com.bringo.dotit.models.CategoryModel
import com.bringo.dotit.models.MenuModel
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.CategoriesViewModel
import kotlinx.android.synthetic.main.menu_categories_fragment.view.*


class MenuCategories : Fragment() {

    companion object {
        fun newInstance() = MenuCategories()
    }

    private lateinit var viewModel: CategoriesViewModel
    var restauRecycler: RecyclerView? = null

    private lateinit var mAdapter: CategoriesRVAdapter
    private lateinit var binding: RestauMenuBinding
    var dataList: ArrayList<CategoryModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =
            DataBindingUtil.inflate(inflater, R.layout.menu_categories_fragment, container, false)
        val view = binding.root

        //restauRecycler=view.findViewById(R.id.categories_rv) as RecyclerView
        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)

        initRecyclerView()


        /*view.list_item.setOnClickListener { v:View ->
            v.findNavController().navigate(R.id.action_homeList_to_restauMenu)
        }*/

        return view
    }


    fun initRecyclerView( ) {

        val bundle = this.arguments
        if (bundle != null) {
            val restauId = bundle.getString("restauId", "0")
            val menuPerTitle = bundle.getSerializable("menuPerTitle") as MenuModel

            Hell("sectionCategories in MenuCategoriesFrgmnt = ${menuPerTitle.sectionCategories}")

            mAdapter = CategoriesRVAdapter { category ->
                Hell("MenuCategories: clicked category: ${category}")

                var bundle = bundleOf("categoryId" to category._id, "restauId" to restauId)
                findNavController().navigate(R.id.action_restauMenu_to_selectedCategory, bundle)
            }
            val layoutManager = LinearLayoutManager(context)
            //layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.root.categories_rv.layoutManager = layoutManager
            mAdapter.setRestauList(menuPerTitle.sectionCategories)
            binding.root.categories_rv.adapter = mAdapter
        }
        //subscribeDataCallBack()
    }

    private fun subscribeDataCallBack() {
        viewModel.getCategories()
        viewModel.categoriesList.observe(this, Observer { categories ->
            //mAdapter.setRestauList(categories)

        })

    }


}
