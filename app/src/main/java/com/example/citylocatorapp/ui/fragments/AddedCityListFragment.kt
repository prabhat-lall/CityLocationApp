package com.example.citylocatorapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citylocatorapp.R
import com.example.citylocatorapp.adapters.FavListAdapter
import com.example.citylocatorapp.databinding.FragmentAddedCityListBinding
import com.example.citylocatorapp.models.CityLocationItem
import com.example.citylocatorapp.ui.viewmodel.AddedCityViewModel
import com.example.citylocatorapp.ui.viewmodel.MainViewModel
import com.google.gson.Gson


class AddedCityListFragment : Fragment() {

    lateinit var binding: FragmentAddedCityListBinding
    private lateinit var viewModel : AddedCityViewModel
    //val cityLocationList: MutableList<CityLocationItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddedCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        viewModel = ViewModelProvider(this)[AddedCityViewModel::class.java]
        setListener()
        setObserver()
    }

    private fun setObserver() {
        viewModel.getRecentLocationList().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),"${it}",Toast.LENGTH_SHORT).show()
            binding.rvAddCity.layoutManager = LinearLayoutManager(requireContext())
            binding.rvAddCity.adapter =
                FavListAdapter(::onCityLocationItemClicked, requireContext(), it)
        })
    }

    private fun setListener() {
        binding.fbAdd.setOnClickListener {

            // taking data from user
            val city = binding.tvCityName.text.toString()
            val state = binding.tvState.text.toString()
            val lat = binding.tvLat.text.toString()
            val lon = binding.tvLon.text.toString()

            val data = CityLocationItem(lat = lat, lon = lon, name = city, state = state)
            viewModel.cityLocationList.add(data)
            viewModel.loadLocation(viewModel.cityLocationList)
            if (!data.lat.isNullOrEmpty() || !data.lon.isNullOrEmpty() || !data.name.isNullOrEmpty() || !data.state.isNullOrEmpty()) {
                onCityLocationItemClicked(data)
                Toast.makeText(requireContext(), "Added ${data.name}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Fill all Data", Toast.LENGTH_SHORT).show()
            }

        }


        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onCityLocationItemClicked(cityLocationItem: CityLocationItem) {
        val bundle = Bundle()
        bundle.putString("cityLocationItem", Gson().toJson(cityLocationItem))
        findNavController().navigate(
            R.id.action_addedCityListFragment_to_locationDetailFragment,
            bundle
        )
    }

}