package com.aliucord.coreplugins.componentsv2

import android.content.res.ColorStateList
import android.webkit.URLUtil
import com.aliucord.utils.DimenUtils.dp
import com.aliucord.utils.R
import com.aliucord.wrappers.messages.AttachmentWrapper
import com.discord.api.botuikit.UnfurledMediaItem
import com.discord.api.message.attachment.MessageAttachment
import com.discord.utilities.color.ColorCompat
import com.google.android.material.card.MaterialCardView

fun MaterialCardView.applyEmbedStyle() {
    setCardBackgroundColor(ColorCompat.getThemedColor(context, R.attr.colorBackgroundSecondary))
    radius = 8.dp.toFloat()
    elevation = 0f
    rippleColor = ColorStateList.valueOf(ColorCompat.getThemedColor(this, R.attr.primary_400_alpha_30))
    strokeColor = ColorCompat.getThemedColor(this, R.attr.primary_700_alpha_60)
    strokeWidth = resources.getDimensionPixelSize(R.dimen.chat_embed_card_stroke_width)
}

fun UnfurledMediaItem.asAttachment(
    name: String = URLUtil.guessFileName(url, null, contentType),
    size: Long = 0,
): MessageAttachment {
    return AttachmentWrapper.create(
        name,
        size,
        proxyUrl,
        url,
        width,
        height,
    ).also { it.contentType = contentType }
}
