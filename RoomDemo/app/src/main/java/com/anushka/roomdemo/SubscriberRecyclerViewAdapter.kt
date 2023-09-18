package com.anushka.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.databinding.ListItemBinding
import com.anushka.roomdemo.db.Subscriber

class SubscriberRecyclerViewAdapter(
    private val onCLickListener: (Subscriber) -> Unit
) :
    RecyclerView.Adapter<SubscriberRecyclerViewAdapter.SubscriberViewHolder>() {
    private val subscribersList = ArrayList<Subscriber>()

    inner class SubscriberViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: Subscriber) {
            binding.nameTextView.text = subscriber.name
            binding.emailTextView.text = subscriber.email

            binding.itemCardView.setOnClickListener {
                onCLickListener(subscriber)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return SubscriberViewHolder(binding)
    }

    override fun getItemCount(): Int = subscribersList.size

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(subscribersList[position])
    }

    fun setList(subscribers: List<Subscriber>) {
        subscribersList.clear()
        subscribersList.addAll(subscribers)
    }
}