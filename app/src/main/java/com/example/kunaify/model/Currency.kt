package com.example.kunaify.model

import com.example.kunaify.R

sealed class Currency {

    abstract val name: Int
    abstract val icon: Int

    object Euro : Currency() {
        override val name: Int = R.string.eur
        override val icon: Int = R.drawable.ic_eur
    }

    data class Hrk(
        override val name: Int = R.string.kn,
        override val icon: Int = R.drawable.ic_hrk
    ) : Currency()
}