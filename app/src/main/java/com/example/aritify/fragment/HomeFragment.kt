package com.example.aritify.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aritify.AllProduct
import com.example.aritify.ProductDetails
import com.example.aritify.R
import com.example.aritify.UserInformation
import com.example.aritify.databinding.FragmentHomeBinding
import com.example.aritify.mvvm.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth
    private lateinit var vm : ViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()

        vm = ViewModelProvider(this).get(ViewModel::class.java)

        vm.retrive_user_data {
            Picasso.get().load(it.profile_photo).into(binding.profilePhoto)
            binding.address.text = it.address
        }

        var allProductArray: List<String>

        vm.myItem.observe(requireActivity(), Observer {
            allProductArray = it.product_name
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, allProductArray)
            val textView = binding.autocompleteSearch as AutoCompleteTextView
            textView.setAdapter(adapter)
        })

        binding.profilePhoto.setOnClickListener{
            startActivity(Intent(activity , UserInformation::class.java))
        }
        binding.llmen.setOnClickListener {
            showAllProductCategory("Mens")
        }
        binding.llwomen.setOnClickListener {
            showAllProductCategory("Womens")
        }
        binding.llhomedecore.setOnClickListener {
            showAllProductCategory("Home Decor")
        }
        binding.llpottery.setOnClickListener {
            showAllProductCategory("Pottery")
        }
        binding.llpainting.setOnClickListener {
            showAllProductCategory("Painting")
        }
        binding.lltoys.setOnClickListener {
            showAllProductCategory("Toys")
        }
        binding.llbags.setOnClickListener {
            showAllProductCategory("Bags")
        }
        binding.llother.setOnClickListener {
            showAllProductCategory("Others")
        }

        binding.allProductViewAll.setOnClickListener {
            showAllProductCategory("All Products")
        }

        val searchBarText = binding.autocompleteSearch.text.toString()
        val searchBar = "1"

        binding.searchButton.setOnClickListener {
            val intent = Intent(requireContext() , AllProduct::class.java)
            intent.putExtra("search_text" , searchBarText)
            intent.putExtra("search_bar_value" , searchBar)
            startActivity(intent)
        }
        binding.autocompleteSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    val intent = Intent(requireContext() , AllProduct::class.java)
                    intent.putExtra("search_text" , searchBarText)
                    intent.putExtra("search_bar_value" , searchBar)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun showAllProductCategory(s: String) {
        val intent = Intent(requireContext(),AllProduct::class.java)
        intent.putExtra("Category" , s)
        startActivity(intent)
    }
}