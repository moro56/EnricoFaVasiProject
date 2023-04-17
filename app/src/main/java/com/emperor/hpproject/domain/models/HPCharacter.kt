package com.emperor.hpproject.domain.models

import androidx.annotation.StringRes
import com.emperor.hpproject.R

data class HPCharacter(
    val id: String,
    val name: String,
    val alternateNames: List<String>,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String?,
    val yearOfBirth: Int?,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val wand: HPWand,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    val alternate_actors: List<String>,
    val alive: Boolean,
    val image: String
) {
    val isWizardStringResource: Int
        @StringRes
        get() = if (wizard) R.string.yes else R.string.no

    val wandString: String
        get() = "${wand.core}, ${wand.wood} ${if (wand.length != null) "(${wand.length})" else ""}"
}
