package com.jaredzhang.fancybrokenui.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaredzhang.fancybrokenui.R
import com.jaredzhang.fancybrokenui.entity.HomeItem
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home.*

class HomeItemAapter(val homeItemClickListener: HomeItemClickListener) : RecyclerView.Adapter<HomeItemAapter.HomeItemViewHolder>() {

    var homeItems: List<HomeItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var isActivated = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_home,
                parent, false)
        return HomeItemViewHolder(inflatedView)
    }

    override fun getItemCount() = homeItems.size

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.setData(homeItems[position])
    }

    inner class HomeItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            itemView.setOnClickListener {
                if (isActivated) {
                    homeItemClickListener.onItemClick(it, adapterPosition, homeItems[adapterPosition])
                }
            }
        }

        fun setData(homeItem: HomeItem) {
            if (homeItem.thumnbnailUrl.isNotBlank()) {
                Picasso.with(itemView.context).load(homeItem.thumnbnailUrl).into(ivThumbnail)
            } else {
                ivThumbnail.setImageResource(R.drawable.placeholder)
            }
            //ivThumbnail.clipPercent = clipPercent
            tvTitle.text = homeItem.title
            tvDescription.text = homeItem.description
        }
    }
}

interface HomeItemClickListener {
    fun onItemClick(view: View, position: Int, homeItem: HomeItem)
}