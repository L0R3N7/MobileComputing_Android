package at.htl.leonding.bhitm5.payyours.model

import java.text.DecimalFormat
import java.util.*

data class Payment(var courts: Int, var players: Int, var pricePerUnitText: String) : java.io.Serializable {
    val amount: Double
        get() {
            val decimalFormat = DecimalFormat.getInstance(Locale.getDefault()) as DecimalFormat
            val pricePerUnit = decimalFormat.parse(pricePerUnitText) ?: return -1.0
            return courts * pricePerUnit.toDouble() / players
        }
}