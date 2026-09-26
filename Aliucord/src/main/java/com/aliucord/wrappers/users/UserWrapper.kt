/*
 * This file is part of Aliucord, an Android Discord client mod.
 * Copyright (c) 2025 Juby210 & Vendicated
 * Licensed under the Open Software License version 3.0
 */

@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package com.aliucord.wrappers.users

import com.aliucord.utils.accessField
import com.aliucord.utils.accessGetter
import com.discord.api.user.*
import com.discord.models.user.CoreUser
import com.discord.models.user.MeUser
import com.discord.models.user.User as ModelUser

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var User.globalName by accessField<String?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var CoreUser.globalName by accessField<String?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var MeUser.globalName by accessField<String?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
val ModelUser.globalName by accessGetter<String?>()

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var User.avatarDecorationData by accessField<AvatarDecoration?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var CoreUser.avatarDecorationData by accessField<AvatarDecoration?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var MeUser.avatarDecorationData by accessField<AvatarDecoration?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
val ModelUser.avatarDecorationData by accessGetter<AvatarDecoration?>()

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var User.collectibles by accessField<Collectibles?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var CoreUser.collectibles by accessField<Collectibles?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var MeUser.collectibles by accessField<Collectibles?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
val ModelUser.collectibles by accessGetter<Collectibles?>()

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var User.displayNameStyles by accessField<DisplayNameStyle?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var CoreUser.displayNameStyles by accessField<DisplayNameStyle?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var MeUser.displayNameStyles by accessField<DisplayNameStyle?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
val ModelUser.displayNameStyles by accessGetter<DisplayNameStyle?>()

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var User.primaryGuild by accessField<PrimaryGuild?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var CoreUser.primaryGuild by accessField<PrimaryGuild?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var MeUser.primaryGuild by accessField<PrimaryGuild?>()
@Deprecated("This accessor is no longer required; remove the import and access the field directly")
val ModelUser.primaryGuild by accessGetter<PrimaryGuild?>()
