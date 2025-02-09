package com.example.aritify.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.aritify.R
import com.example.aritify.adapter.ShowsAdapter
import com.example.aritify.adapter.ViewPagerAdapter
import com.example.aritify.databinding.FragmentHomeBinding
import com.example.aritify.databinding.FragmentMicBinding
import com.example.aritify.login.GetStarted
import com.example.aritify.mvvm.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MicFragment : Fragment(){

    private var _binding : FragmentMicBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>
    private lateinit var vm : ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMicBinding.inflate(inflater,container,false)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        vm = ViewModelProvider(this).get(ViewModel::class.java)

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            activity?.intent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity?.startActivity(Intent(activity , GetStarted::class.java))
            activity?.finish()
        }

        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.img_virat
        imageList = imageList + R.drawable.women_category
        imageList = imageList + R.drawable.app_logo
        imageList = imageList + R.drawable.img_other
        imageList = imageList + R.drawable.img_painting
        imageList = imageList + R.drawable.img_real_home

        viewPager = binding.idViewPager

        viewPagerAdapter = ViewPagerAdapter(requireContext(), imageList)


        // on below line we are setting
        // adapter to our view pager.
//        viewPager.adapter = viewPagerAdapter
//
//        val showsAdapter = ShowsAdapter()
//
//        vm.retrive_show_data("comedy").observe(requireActivity(), Observer {
//            showsAdapter.setItemList1(it)
//            showsAdapter.notifyDataSetChanged()
//        })

//        binding.comedyRecyclerView.adapter = showsAdapter
//        binding.musicRecyclerView.adapter = showsAdapter
//        binding.poetryRecyclerView.adapter = showsAdapter

    }

}