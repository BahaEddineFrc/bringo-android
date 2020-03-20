package com.bringo.dotit.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.R
import com.bringo.dotit.adapters.DishesRVAdapters
import com.bringo.dotit.databinding.FragmentSelectedCategoryBinding
import com.bringo.dotit.models.CategoryModel
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.viewmodels.CategoryDishesViewModel
import kotlinx.android.synthetic.main.fragment_selected_category.view.*
import java.io.Serializable


class SelectedCategory : Fragment() {

    private lateinit var viewModel: CategoryDishesViewModel
    var restauRecycler : RecyclerView?=null

    private lateinit var mAdapter: DishesRVAdapters
    private lateinit var binding : FragmentSelectedCategoryBinding
    var dataList: ArrayList<DishModel> = ArrayList()
    var restauId = String()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selected_category, container, false)
        viewModel = ViewModelProviders.of(this).get(CategoryDishesViewModel::class.java)
        binding.categoryviewmodel=viewModel

        initRecyclerView()


        return binding.root
    }


    fun initRecyclerView(){

        mAdapter = DishesRVAdapters{dish->
            //Hell("SelectedCateg: clicked dish: ${dish._id}")
            val bundle = bundleOf("dish" to dish as Serializable
                                        ,"restauId" to restauId)
            findNavController().navigate(R.id.action_selectedCategory_to_selectedDish,bundle)
        }

        val layoutManager = LinearLayoutManager(context)
        binding.root.category_dishes_rv.layoutManager = layoutManager
        mAdapter.setDishesList(dataList) //delete
        binding.root.category_dishes_rv.adapter = mAdapter

        subscribeDataCallBack()
    }

    private fun subscribeDataCallBack() {

        val restauId=arguments?.getString("restauId")
        this.restauId=restauId!!
        val restauName=arguments?.getString("restauName")
        val category=arguments?.getSerializable("category") as CategoryModel

        viewModel.setUpCategory(category,restauName,restauId)


        viewModel.dishesList.observe(this, Observer { dishes->
            mAdapter.setDishesList(dishes)
        })

    }

}
