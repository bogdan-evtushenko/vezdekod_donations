package com.example.vkdonations.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.example.vkdonations.R
import kotlinx.android.synthetic.main.input_field.view.*

class InputField(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    enum class Type {
        EMAIL,
        PASSWORD,
        TEXT,
        TEXT_MULTILINE;

        val textViewInputType: Int
            get() = when (this) {
                EMAIL -> InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                PASSWORD -> InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
                TEXT -> InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                TEXT_MULTILINE -> InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_FLAG_MULTI_LINE or
                        InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            }

        val maxInputLines: Int
            get() = when (this) {
                TEXT_MULTILINE -> Int.MAX_VALUE
                else -> 1
            }
    }

    sealed class Action(
        val imeActionId: Int
    ) {

        open val onClick: (() -> Unit)? = null

        data class Done(
            override val onClick: (() -> Unit)
        ) : Action(EditorInfo.IME_ACTION_DONE)

        data class Go(
            override val onClick: (() -> Unit)
        ) : Action(EditorInfo.IME_ACTION_GO)

        object Next : Action(EditorInfo.IME_ACTION_NEXT)
    }

    init {
        View.inflate(context, R.layout.input_field, this)
    }

    var input: EditText? = null

    fun configure(
        labelText: CharSequence? = null,
        labelTextResId: Int? = null,
        inputHint: CharSequence? = null,
        inputHintResId: Int? = null,
        inputText: CharSequence? = null,
        type: Type = Type.TEXT,
        minLines: Int = 1,
        action: Action? = null,
        secondLabelText: CharSequence = "",
        secondLabelOnClickListener: (() -> Unit)? = null
    ) {
        input = editText
        input?.let { input ->
            input.hint = when {
                inputHint != null -> inputHint
                inputHintResId != null && inputHintResId != 0 -> resources.getString(inputHintResId)
                else -> null
            }

            input.setText(inputText ?: "", TextView.BufferType.EDITABLE)
            input.setSelection(0, input.text?.length ?: 0)
            input.inputType = type.textViewInputType
            input.maxLines = type.maxInputLines
            input.minLines = minLines
            if (action != null) {
                input.imeOptions = action.imeActionId
                input.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == action.imeActionId && action.onClick != null) {
                        action.onClick?.invoke()
                        true
                    } else {
                        false
                    }
                }
            } else {
                input.imeOptions = EditorInfo.IME_ACTION_NONE
                input.setOnEditorActionListener(null)
            }
        }
        label.text = when {
            labelText != null -> labelText
            labelTextResId != null && labelTextResId != 0 -> resources.getString(labelTextResId)
            else -> ""
        }
        label.visibility = if (label.text?.any() == true) View.VISIBLE else View.GONE
        secondLabel.text = secondLabelText
        secondLabel.setOnClickListener { secondLabelOnClickListener?.invoke() }
    }
}
