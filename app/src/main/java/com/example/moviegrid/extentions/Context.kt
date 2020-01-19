package com.example.moviegrid.extentions

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog


fun Context.alertDialog(
    title: String? = null,
    message: String? = null,
    positiveButtonTitle: String,
    negativeButtonTitle: String? = null,
    positiveAction: (() -> Unit)? = null,
    negativeAction: (() -> Unit)? = null
): Dialog {

    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonTitle) { _, _ ->
            positiveAction?.invoke()
        }
    if (negativeButtonTitle != null && negativeButtonTitle.isNotBlank()) {
        builder.setNegativeButton(negativeButtonTitle) { _, _ ->
            negativeAction?.invoke()
        }
    }
    return builder.create()
}