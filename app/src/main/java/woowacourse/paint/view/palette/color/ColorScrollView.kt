package woowacourse.paint.view.palette.color

import android.content.Context
import android.widget.HorizontalScrollView
import android.widget.LinearLayout

class ColorScrollView private constructor(
    context: Context,
) : HorizontalScrollView(context) {
    private var onColorSelected: (PaletteColor) -> Unit = {}
        set(value) {
            field = value
            setupPaletteColorViews()
        }

    private val paletteColorViews = LinearLayout(context).apply {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        orientation = LinearLayout.HORIZONTAL
    }

    init {
        addView(paletteColorViews)
    }

    private fun setupPaletteColorViews() {
        PaletteColor.values().forEach { paletteColor ->
            paletteColorViews.addView(ColorView.create(context, paletteColor, onColorSelected))
        }
    }

    companion object {
        fun create(
            context: Context,
            onColorSelected: (PaletteColor) -> Unit,
        ): ColorScrollView = ColorScrollView(context).also { view ->
            view.onColorSelected = onColorSelected
        }
    }
}
