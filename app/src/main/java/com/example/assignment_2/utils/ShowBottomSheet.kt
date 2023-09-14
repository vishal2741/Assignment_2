package com.example.assignment_2.utils

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.assignment_2.R
import com.example.assignment_2.model.ActorsDetailsItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowBottomSheet(private var actors: ActorsDetailsItem) : BottomSheetDialogFragment() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.show_bottom_sheet, container, false)

        val nameTextView: TextView = view.findViewById(R.id.name)
        val actorTextView: TextView = view.findViewById(R.id.actor)
        val dobTextView: TextView = view.findViewById(R.id.dateOfBirth)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val alternateNamesTextView: TextView = view.findViewById(R.id.alternate_names)
        val genderTextView: TextView = view.findViewById(R.id.gender)
        val houseTextView: TextView = view.findViewById(R.id.house)
        val yearOfBirthTextView: TextView = view.findViewById(R.id.yearOfBirth)
        val eyeColourTextView: TextView = view.findViewById(R.id.eyeColour)
        val ancestryTextView: TextView = view.findViewById(R.id.ancestry)
        val hairColourTextView: TextView = view.findViewById(R.id.hairColour)
        val patronusTextView: TextView = view.findViewById(R.id.patronus)
        val aliveTextView: TextView = view.findViewById(R.id.alive)

        nameTextView.text = "Name: ${actors.name}"
        actorTextView.text = "Actor: ${actors.actor}"
        dobTextView.text = "DOB: ${actors.dateOfBirth}"
        alternateNamesTextView.text="Alternate Name: ${actors.alternate_names}"
        genderTextView.text="Gender : ${actors.gender}"
        houseTextView.text="House : ${actors.house}"
        yearOfBirthTextView.text="Year Of Birth : ${actors.yearOfBirth}"
        eyeColourTextView.text="Eye Colour : ${actors.eyeColour}"
        ancestryTextView.text="Ancestry : ${actors.ancestry}"
        hairColourTextView.text="Hair Colour : ${actors.hairColour}"
        patronusTextView.text="Patronus : ${actors.patronus}"
        aliveTextView.text="Alive : ${actors.alive}"

        Glide.with(view.context).load(actors.image).placeholder(R.drawable.error_image_placeholder).into(imageView)

        return view
    }


}