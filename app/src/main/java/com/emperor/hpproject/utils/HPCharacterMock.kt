package com.emperor.hpproject.utils

import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.domain.models.HPWand

val hpCharacterMock = HPCharacter(
    id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
    name = "Harry Potter",
    alternateNames = listOf("The Boy Who Lived", "The Chosen One"),
    species = "human",
    gender = "male",
    house = "Gryffindor",
    dateOfBirth = "31-07-1980",
    yearOfBirth = 1980,
    wizard = true,
    ancestry = "half-blood",
    eyeColour = "green",
    hairColour = "black",
    wand = HPWand(wood = "holly", core = "phoenix feather", length = 100f),
    patronus = "stag",
    hogwartsStudent = true,
    hogwartsStaff = false,
    actor = "Daniel Radcliffe",
    alternate_actors = listOf(),
    alive = true,
    image = "https://ik.imagekit.io/hpapi/harry.jpg"
)