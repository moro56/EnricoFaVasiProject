package com.emperor.hpproject.domain.ext

import com.emperor.hpproject.data.network.models.RemoteHPCharacter
import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.domain.models.HPWand

/**
 * Converts Data model to Domain model
 */
fun RemoteHPCharacter.toHPCharacter() = HPCharacter(
    id = this.id,
    name = this.name,
    alternateNames = this.alternateNames,
    species = this.species,
    gender = this.gender,
    house = this.house,
    dateOfBirth = this.dateOfBirth,
    yearOfBirth = this.yearOfBirth,
    wizard = this.wizard,
    ancestry = this.ancestry,
    eyeColour = this.eyeColour,
    hairColour = this.hairColour,
    wand = HPWand(wood = this.wand.wood, core = this.wand.core, length = this.wand.length),
    patronus = this.patronus,
    hogwartsStudent = this.hogwartsStudent,
    hogwartsStaff = this.hogwartsStaff,
    actor = this.actor,
    alternate_actors = this.alternate_actors,
    alive = this.alive,
    image = this.image
)

/**
 * Converts list of Data models to list of Domain models
 */
fun List<RemoteHPCharacter>.toHPCharacterList() = this.map { it.toHPCharacter() }