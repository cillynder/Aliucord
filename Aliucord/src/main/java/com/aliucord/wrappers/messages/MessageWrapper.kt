@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package com.aliucord.wrappers.messages

import com.discord.api.message.poll.MessagePoll
import java.lang.reflect.Field
import com.discord.api.message.Message as ApiMessage
import com.discord.models.message.Message as ModelMessage

private val apiPollField: Field = ApiMessage::class.java.getDeclaredField("poll")
private val modelPollField: Field = ModelMessage::class.java.getDeclaredField("poll")

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var ApiMessage.poll
    get() = apiPollField[this] as MessagePoll?
    set(it) = apiPollField.set(this, it)

@Deprecated("This accessor is no longer required; remove the import and access the field directly")
var ModelMessage.poll
    get() = modelPollField[this] as MessagePoll?
    set(it) = modelPollField.set(this, it)
