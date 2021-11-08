package com.example.psmart.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment
import androidx.lifecycle.ViewModelProvider
import com.example.psmart.FingerPrintRegisterActivity
import com.example.psmart.R
import com.example.psmart.ScanFingerPrintActivity
import com.example.psmart.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {



    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



//    val register = findViewById<TextView>(R.id.register_attendance)
//    register.setOnClickListener {
//        val intent = Intent(this, ForgotPasswordActivity::class.java);
//        startActivity(intent)
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root






//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
    //    return root


        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val registerNew = view.findViewById<Button>(R.id.register_new)
        registerNew.setOnClickListener {
            val intent = Intent(activity, FingerPrintRegisterActivity::class.java)
            startActivity(intent)
        }
        val register = view.findViewById<Button>(R.id.reports)
        register.setOnClickListener {
            val intent = Intent(activity, ReportFragment::class.java)
            startActivity(intent)
        }

        val scan = view.findViewById<Button>(R.id.register_attendance)
        scan.setOnClickListener {
            val intent = Intent(activity, ScanFingerPrintActivity::class.java)
            startActivity(intent)
        }

        // Return the fragment view/layout
          return view


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}