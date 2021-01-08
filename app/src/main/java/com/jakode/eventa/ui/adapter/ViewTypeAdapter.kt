package com.jakode.eventa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jakode.eventa.BR
import com.jakode.eventa.R
import com.jakode.eventa.model.Event
import com.jakode.eventa.utils.isPersian

class ViewTypeAdapter<E : ViewType<*>>(
    private var list: MutableList<E> = mutableListOf(),
    private val onItemActionListener: OnItemActionListener? = null
) : RecyclerView.Adapter<ViewTypeAdapter<E>.ViewTypeHolder>(), BindableAdapter<List<E>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return ViewTypeHolder(binding, onItemActionListener)
    }

    override fun onBindViewHolder(holder: ViewTypeHolder, position: Int) {
        holder.bindItem(list[position])
        setMargins(position, holder)
    }

    private fun setMargins(
        position: Int,
        holder: ViewTypeHolder
    ) {
        if (list[position].data() is Event) { // Event list
            val cardView = holder.itemView.findViewById<CardView>(R.id.cardView)
            val layoutParams = cardView.layoutParams as ConstraintLayout.LayoutParams
            if (isPersian()) {
                when (position) {
                    0 -> layoutParams.setMargins(25, 25, 45, 25)
                    itemCount - 1 -> layoutParams.setMargins(45, 25, 25, 25)
                    else -> layoutParams.setMargins(25, 25, 25, 25)
                }
            } else {
                when (position) {
                    0 -> layoutParams.setMargins(45, 25, 25, 25)
                    itemCount - 1 -> layoutParams.setMargins(25, 25, 45, 25)
                    else -> layoutParams.setMargins(25, 25, 25, 25)
                }
            }
            cardView.layoutParams = layoutParams
        }
    }

    override fun getItemViewType(position: Int): Int = list[position].layoutId()

    override fun getItemCount(): Int = list.size

    override fun setList(list: List<E>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setUpdatedList(list: MutableList<E>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun insertElement(data: E, position: Int? = null) {
        if (position != null) {
            this.list.add(position, data)
            notifyItemInserted(position)
        } else {
            this.list.add(data)
            notifyItemInserted(this.list.size - 1)
        }
    }

    fun updateElement(data: E, position: Int) {
        this.list[position] = data
        notifyItemChanged(position)
    }

    fun updateElements(data: List<E>, startIndex: Int) {
        var start = startIndex
        for (i in data.indices) {
            if (start >= this.list.size) {
                this.list.add(data[i])
            } else {
                this.list[start] = data[i]
            }
            start++
        }
        notifyItemRangeChanged(startIndex, data.size)
    }

    fun insertElements(data: List<E>, position: Int? = null) {
        if (position != null) {
            this.list.addAll(position, data)
            notifyItemRangeInserted(position, data.size)
        } else {
            val index = this.list.size - 1
            this.list.addAll(data)
            notifyItemRangeInserted(index, this.list.size - 1)
        }
    }

    fun removeElements() {
        list.clear()
        notifyDataSetChanged()
    }

    fun removeElements(startIndex: Int, endIndex: Int = this.list.size - 1) {
        val iterator = this.list.listIterator(startIndex)
        var end = endIndex
        while (iterator.hasNext()) {
            iterator.next()
            if (startIndex <= end) {
                iterator.remove()
                end--
            } else {
                break
            }
        }

        notifyItemRangeRemoved(startIndex, endIndex - startIndex)
    }

    fun removeElement(index: Int) {
        if (index in 0 until list.size) {
            list.removeAt(index)
            notifyItemRemoved(index)
            notifyItemRangeChanged(index, list.size)
        }
    }

    fun updateLastElement(data: E) {
        this.list[this.list.size - 1] = data
        notifyItemChanged(this.list.size - 1)
    }

    inner class ViewTypeHolder(
        private val binding: ViewDataBinding,
        private val onItemActionListener: OnItemActionListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: ViewType<*>) {
            val model = item.data()

            when (model) {
                is Event -> {
                    binding.setVariable(BR.position, adapterPosition)
                    binding.setVariable(BR.actionItemListener, onItemActionListener)
                }
            }
            binding.setVariable(BR.model, model)
            binding.executePendingBindings()
        }
    }

    fun interface OnItemActionListener {
        fun onItemClicked(position: Int)
    }

    interface OnLongActionListener : OnItemActionListener {
        fun onItemLongClicked(view: View): Boolean
    }
}