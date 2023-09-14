package com.example.assignment_2.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment_2.R
import com.example.assignment_2.model.ActorsDetailsItem


class ActorsAdapter (private val fragmentManager: FragmentManager)  : RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>(){

    private val actorsList: MutableList<ActorsDetailsItem> = ArrayList()


    fun setData(actors: List<ActorsDetailsItem>) {
        actorsList.clear()
        actorsList.addAll(actors)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_fragment, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
       val actor = actorsList[position]
       holder.bind(actor)

      holder.itemView.setOnClickListener {
        val bottomSheetFragment = ShowBottomSheet(actor)
        bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
    }
}

    override fun getItemCount(): Int {
        return actorsList.size
    }

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val actorTextView: TextView = itemView.findViewById(R.id.ActorName)
        private val dobTextView: TextView = itemView.findViewById(R.id.DOB)
        private val imageView: ImageView = itemView.findViewById(R.id.imageIv)

        @SuppressLint("SetTextI18n")
        fun bind(actors: ActorsDetailsItem) {
            nameTextView.text = "Name : ${actors.name}"
            actorTextView.text = "Actor Name : ${actors.actor}"
            dobTextView.text = "DOB : ${actors.dateOfBirth}"

            Glide.with(itemView.context).load(actors.image).placeholder(R.drawable.error_image_placeholder).into(imageView)
        }

    }

}

