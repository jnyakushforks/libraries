package com.kaloglu.library.ui.viewmodel.databinding

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kaloglu.library.ui.viewmodel.ViewModelActivity
import com.kaloglu.library.ui.viewmodel.databinding.interfaces.DataBinding

abstract class BindingActivity<VDB, VM> : ViewModelActivity<VM>(), DataBinding<VDB>
        where VM : BindableViewModel<*, *>, VDB : ViewDataBinding {

    internal open fun VDB.viewOnBind() = Unit

    override fun initUserInterface() {
        viewDataBinding = DataBindingUtil.setContentView(this, resourceLayoutId)
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
        viewDataBinding.viewOnBind()
    }

}
