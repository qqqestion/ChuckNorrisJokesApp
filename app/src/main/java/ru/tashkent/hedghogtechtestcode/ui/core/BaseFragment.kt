package ru.tashkent.hedghogtechtestcode.ui.core

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    protected var _binding: Binding? = null

    protected val binding: Binding
        get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun snackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    fun snackbar(@StringRes stringId: Int) {
        Snackbar.make(requireView(), stringId, Snackbar.LENGTH_SHORT).show()
    }
}