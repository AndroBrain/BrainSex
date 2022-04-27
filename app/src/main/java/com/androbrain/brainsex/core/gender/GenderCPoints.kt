package com.androbrain.brainsex.core.gender

import com.androbrain.brainsex.core.Points
import kotlinx.parcelize.Parcelize

private const val C_POINTS = -5

@Parcelize
object GenderCPoints : Points<Gender> {
    override fun getPointsByFilter(filter: Gender) = C_POINTS
}
