package com.example.naylaapps.pertemuan_13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.naylaapps.databinding.FragmentTabQrCodeBinding

class TabQrcodeFragment : Fragment() {
    private var _binding: FragmentTabQrCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTabQrCodeBinding.inflate(inflater, container, false)
        return binding.root
    }
}