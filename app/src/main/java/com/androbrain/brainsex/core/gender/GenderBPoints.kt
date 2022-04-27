package com.androbrain.brainsex.core.gender

import com.androbrain.brainsex.core.Points
import kotlinx.parcelize.Parcelize

private const val B_POINTS = 5

@Parcelize
object GenderBPoints : Points<Gender> {
    override fun getPointsByFilter(filter: Gender) = B_POINTS
}
