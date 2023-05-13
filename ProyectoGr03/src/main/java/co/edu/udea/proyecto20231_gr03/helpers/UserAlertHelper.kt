package co.edu.udea.proyecto20231_gr03.helpers

import android.content.Context
import androidx.appcompat.app.AlertDialog
import io.konform.validation.Validation
import io.konform.validation.ValidationResult

object UserAlertHelper {
    fun showErrorDialog(context: Context, errorMessage: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(errorMessage)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }

    fun <T> showValidationErrorsDialog(context: Context, validation: ValidationResult<T>) {
        val errorMessage = validation.errors.joinToString("\n") { it.message }
        showErrorDialog(context, errorMessage)
    }
}