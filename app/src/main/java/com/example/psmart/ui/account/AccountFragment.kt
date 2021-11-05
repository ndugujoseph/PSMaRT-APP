package com.example.psmart.ui.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.psmart.R
import com.example.psmart.databinding.FragmentAccountBinding
import com.example.psmart.ui.login.Account
import com.example.psmart.ui.login.AccountAdapter
import org.json.JSONArray
import org.json.JSONException




class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //this is the JSON Data URL
    private val URL_ACCOUNT = "https://solfixafrica.com/psmart/accountApi.php?apicall="

    //a list to store all the products
    private var accountList: MutableList<Account>? = null

    //the recyclerview
    @SuppressLint("StaticFieldLeak")
    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val rootView: View = inflater.inflate(R.layout.fragment_account, container, false)
        //getting the recyclerview from xml
        recyclerView = rootView.findViewById(R.id.recylcerView)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(activity?.applicationContext)

        //initializing the productlist
        accountList = ArrayList()

        //this method will fetch and parse json
        //to display it in recyclerview
        loadAccount()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadAccount() {
        val stringRequest = StringRequest(
            Request.Method.GET, URL_ACCOUNT,
            { response: String? ->
                try {
                    //converting the string to json array object
                    val array = JSONArray(response)

                    //traversing through all the object
                    for (i in 0 until array.length()) {

                        //getting product object from json array
                        val account = array.getJSONObject(i)

                        //adding the product to product list
                        accountList!!.add(
                            Account(
                                account.getInt("id"),
                                account.getString("name"),
                                account.getString("gender"),
                                account.getString("email"),
                                account.getString("contact"),
                                account.getString("image")
                            )
                        )
                    }

                    //creating adapter object and setting it to recyclerview
                    val adapter = AccountAdapter(this, accountList)
                    recyclerView!!.adapter = adapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { }

        //adding our stringRequest to queue
        Volley.newRequestQueue(context).add(stringRequest)
    }
}