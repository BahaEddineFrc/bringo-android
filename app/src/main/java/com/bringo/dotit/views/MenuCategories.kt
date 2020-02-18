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

    companion object { //todo delete
        fun newInstance() = MenuCategories()
    }

    private lateinit var viewModel: CategoriesViewModel

    private lateinit var mAdapter: CategoriesRVAdapter
    private lateinit var binding: RestauMenuBinding

    var dataList: ArrayList<CategoryModel> = ArrayList()
    var restauRecycler: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =
            DataBindingUtil.inflate(inflater, R.layout.menu_categories_fragment, container, false)
        val view = binding.root

        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)

        initRecyclerView()

        return view
    }


    fun initRecyclerView( ) {

        val bundle = this.arguments
        if (bundle != null) {
            val restauId = bundle.getString("restauId")
            val restauName = bundle.getString("restauName")
            val menuPerTitle = bundle.getSerializable("menuPerTitle") as MenuModel

            mAdapter = CategoriesRVAdapter { category ->
                //Hell("MenuCategories: clicked category: ${category._id} and sending restauId ${restauId}")

                val bundle = bundleOf("category" to category,
                    "restauId" to restauId, "restauName" to restauName)
                findNavController().navigate(R.id.action_restauMenu_to_selectedCategory, bundle)
            }

                val layoutManager = LinearLayoutManager(context)
                //layoutManager.orientation = LinearLayoutManager.VERTICAL
                binding.root.categories_rv.layoutManager = layoutManager
                mAdapter.setRestauList(menuPerTitle.sectionCategories)
                binding.root.categories_rv.adapter = mAdapter

        }
    }
}
