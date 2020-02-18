package com.kaloglu.library.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.kaloglu.library.ui.interfaces.FragmentLifecycle

abstract class BaseFragment @ContentView constructor(
    @LayoutRes internal open val resourceLayoutId: Int = 0
) : Fragment(resourceLayoutId), FragmentLifecycle {

    override lateinit var fragmentView: View

    override val activity by lazy { getActivity() as BaseActivity }

    override val fragmentTag = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentView = view
        super.onViewCreated(view, savedInstanceState)
        initUserInterface(savedInstanceState)
    }

}