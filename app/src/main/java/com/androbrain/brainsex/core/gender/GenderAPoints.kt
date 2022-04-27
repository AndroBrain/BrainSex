package com.androbrain.brainsex.core.gender

import com.androbrain.brainsex.core.Points
import kotlinx.parcelize.Parcelize

private const val A_MALE_POINTS = 15
private const val A_FEMALE_POINTS = 10

@Parcelize
object GenderAPoints : Points<Gender> {
    override fun getPointsByFilter(filter: Gender) = when (filter) {
        Gender.MALE -> A_MALE_POINTS
        Gender.FEMALE -> A_FEMALE_POINTS
    }
}
