package com.discord.models.user

import com.discord.api.premium.PremiumTier

interface User {
    val premiumTier: PremiumTier
    val id: Long
    val discriminator: Int
    val username: String
}
