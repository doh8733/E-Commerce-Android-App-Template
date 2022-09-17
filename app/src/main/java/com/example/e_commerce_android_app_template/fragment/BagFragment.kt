package com.example.e_commerce_android_app_template.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.adapter.SelectProductTypeAdapter
import com.example.e_commerce_android_app_template.model.TypeProduct
import com.example.e_commerce_android_app_template.viewmodel.ProductViewModel


class BagFragment : Fragment() {
    private val listTypeProduct: MutableList<TypeProduct> = mutableListOf()
    private val productViewModel: ProductViewModel by lazy {

        ViewModelProvider(
            this, ProductViewModel.ProductFactory(
                context?.applicationContext as Application
            )
        )[ProductViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvSum: TextView  by lazy { view.findViewById<TextView>(R.id.tvSum) }
        val rcView: RecyclerView by lazy { view.findViewById<RecyclerView>(R.id.rcView) }
        rcView.adapter = SelectProductTypeAdapter(requireContext(), getlistTypeProduct())
        rcView.layoutManager = LinearLayoutManager(requireContext())


    }

    private fun getlistTypeProduct(): MutableList<TypeProduct> {
        listTypeProduct.add(TypeProduct("1", "Giày"))
        listTypeProduct.add(TypeProduct("2", "Váy"))
        listTypeProduct.add(TypeProduct("3", "Quần"))
        listTypeProduct.add(TypeProduct("4", "mũ"))
        listTypeProduct.add(TypeProduct("5", "Túi sách"))

        return listTypeProduct
    }


}