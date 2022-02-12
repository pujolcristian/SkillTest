package com.condorlabs.skill_test.core.models

data class Artist(
    val items: MutableList<Item>
)

data class Item(
    val images: MutableList<Image>,
    val name: String,
    val popularity: Int,
    val followers: Follower
)