@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package com.aliucord.wrappers.users

import com.aliucord.utils.accessField
import com.discord.api.user.AvatarDecoration
import com.discord.api.user.Collectibles
import com.discord.models.member.GuildMember
import com.discord.api.guildmember.GuildMember as ApiGuildMember

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var ApiGuildMember.avatarDecorationData by accessField<AvatarDecoration?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var GuildMember.avatarDecorationData by accessField<AvatarDecoration?>()

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var ApiGuildMember.collectibles by accessField<Collectibles?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var GuildMember.collectibles by accessField<Collectibles?>()
