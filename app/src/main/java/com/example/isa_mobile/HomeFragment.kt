package com.example.isa_mobile

import Login.Login
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
         return inflater.inflate(R.layout.fragment_home, container, false)




    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        button2 = view.findViewById(R.id.button2)
//        button2.setOnClickListener{
//          val intent = Intent(this@HomeFragment.context,Login::class.java)
//            startActivity(intent)
//        }
//    }




}