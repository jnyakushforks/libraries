package com.kaloglu.library.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.kaloglu.library.ui.DiffItemSimpleCallback
import com.kaloglu.library.ui.RecyclerItem
import com.kaloglu.library.ui.interfaces.ClickableRecyclerItemAdapter

abstract class BaseRecyclerAdapter<RI, VH>(
    diffUtilCallback: DiffItemSimpleCallback<RI> = DiffItemSimpleCallback()
) : ListAdapter<RI, VH>(AsyncDifferConfig.Builder<RI>(diffUtilCallback).build()),
    ClickableRecyclerItemAdapter<RI>
        where RI : RecyclerItem, VH : BaseViewHolder<RI> {

    override var onItemClick: ((RI, Int) -> Unit)? = null
    override var onViewClick: ((RI, View, Int) -> Unit)? = null
    internal val viewHolders: MutableList<VH> = mutableListOf()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    private fun VH.addLifeCycleList() = this.apply {
        viewHolders.add(this)
    }

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder
            .addLifeCycleList()
            .setOnViewClick(onViewClick)
            .bind(getItem(position), onItemClick)

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetach()
    }

    override fun getItemViewType(position: Int) = getItem(position).layoutId

    open fun onLifecycleDestroyed() {
        viewHolders.forEach {
            it.onDestroyed()
        }
    }

}
