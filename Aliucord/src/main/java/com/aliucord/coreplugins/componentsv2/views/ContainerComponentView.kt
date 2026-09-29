package com.aliucord.coreplugins.componentsv2.views

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.core.graphics.ColorUtils
import com.aliucord.coreplugins.componentsv2.models.ContainerMessageComponent
import com.aliucord.utils.DimenUtils.dp
import com.aliucord.utils.R
import com.aliucord.utils.ViewUtils.addTo
import com.aliucord.widgets.LinearLayout
import com.discord.api.botuikit.ComponentType
import com.discord.utilities.color.ColorCompat
import com.discord.widgets.botuikit.ComponentProvider
import com.discord.widgets.botuikit.views.ComponentActionListener
import com.discord.widgets.botuikit.views.ComponentView
import com.discord.widgets.chat.list.adapter.WidgetChatListAdapterItemBotComponentRow
import com.discord.widgets.chat.list.adapter.WidgetChatListAdapterItemBotComponentRowKt
import com.google.android.material.card.MaterialCardView

class ContainerComponentView(ctx: Context) : ConstraintLayout(ctx), ComponentView<ContainerMessageComponent> {
    override fun type() = ComponentType.CONTAINER

    companion object {
        private val accentDividerId = View.generateViewId()

        fun MaterialCardView.applyEmbedStyle() {
            setCardBackgroundColor(ColorCompat.getThemedColor(context, R.attr.colorBackgroundSecondary))
            radius = 8.dp.toFloat()
            elevation = 0f
            rippleColor = ColorStateList.valueOf(ColorCompat.getThemedColor(this, R.attr.primary_400_alpha_30))
            strokeColor = ColorCompat.getThemedColor(this, R.attr.primary_700_alpha_60)
            strokeWidth = resources.getDimensionPixelSize(R.dimen.chat_embed_card_stroke_width).toInt()
        }
    }

    private lateinit var accentDivider: View
    private lateinit var contentView: LinearLayout
    private lateinit var spoilerView: SpoilerView

    init {
        MaterialCardView(ctx).addTo(this) {
            applyEmbedStyle()
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                startToStart = PARENT_ID
                bottomMargin = ctx.resources.getDimension(R.dimen.chat_cell_vertical_spacing_padding).toInt()
            }
            ConstraintLayout(ctx).addTo(this) {
                accentDivider = View(ctx).addTo(this) {
                    id = accentDividerId
                    layoutParams = LayoutParams(3.dp, 0).apply {
                        bottomToBottom = PARENT_ID
                        startToStart = PARENT_ID
                        topToTop = PARENT_ID
                    }
                }
                contentView = LinearLayout(ctx).addTo(this) {
                    setPadding(8.dp, 8.dp, 8.dp, 8.dp)
                    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                        startToEnd = accentDividerId
                        endToEnd = PARENT_ID
                        topToTop = PARENT_ID
                        constrainedWidth = true
                    }
                }
                spoilerView = SpoilerView(ctx, 1).addTo(this) {
                    layoutParams = SpoilerView.constraintLayoutParamsAround(PARENT_ID)
                }
            }
        }
    }

    override fun configure(component: ContainerMessageComponent, provider: ComponentProvider, listener: ComponentActionListener) {
        val item = listener as WidgetChatListAdapterItemBotComponentRow
        val entry = item.entry

        val configuredViews = component.components.mapIndexed { index, child ->
            provider.getConfiguredComponentView(listener, child, contentView, index)
        }.filterNotNull()
        WidgetChatListAdapterItemBotComponentRowKt.replaceViews(contentView, configuredViews)

        val color = component.accentColor?.let { ColorUtils.setAlphaComponent(it, 255) }
            ?: ColorCompat.getThemedColor(context, R.attr.colorBackgroundModifierAccent)
        accentDivider.setBackgroundColor(color)

        spoilerView.configure(entry, component)
    }
}
