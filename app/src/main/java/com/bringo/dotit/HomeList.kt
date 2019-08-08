package com.bringo.dotit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.adapters.RestaurantsRVAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.bringo.dotit.models.Restaurant


class HomeList : Fragment() {
    //private var listener: OnFragmentInteractionListener? = null
    lateinit var restauRecycler : RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var mAdapter: RestaurantsRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home_list, container, false) as View

        restauRecycler=view.findViewById(R.id.restaurants_rv)
        progressBar=view.findViewById(R.id.home_progressbar)
        floatingActionButton=view.findViewById(R.id.fab_home)

        initRecyclerView()

        return view
    }

    fun initRecyclerView(){
        mAdapter= RestaurantsRVAdapter(context, ArrayList<Restaurant>())
        var linearLayout : RecyclerView.LayoutManager = LinearLayoutManager(context)
        restauRecycler.layoutManager=linearLayout
        restauRecycler.adapter=mAdapter
    }



    /*
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
           // throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }*/

}
