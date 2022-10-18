package at.htl.leonding.bhitm5.payyours

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_settings.*
import java.text.DecimalFormat
import java.util.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        //numberFieldSeats.setText(this.sharedPref.getInt(getString(R.string.savedSeats), -1));
        //numberFieldPlayers.setText(this.sharedPref.getInt(getString(R.string.savedPlayers), -1));
        //numberFieldPricePerSeat.setText(this.sharedPref.getInt(getString(R.string.savedPricePerSeat), -1));

        buttonOk.setOnClickListener { onSave() }
        val payment = getStoredPayment(this)
        numberFieldPricePerSeat.setText(payment.pricePerUnitText)
        numberFieldSeats.setText(payment.courts.toString())
        numberFieldPlayers.setText(payment.players.toString())
    }

    companion object{
        val LOG_TAG = SettingsActivity::class.java.simpleName

        val PREFERENCE_FILENAME = "PayYoursPreferences"
        val PRICE_PER_UNIT_KEY = "PRICE_PER_UNIT"
        val PLAYERS_KEY = "DEFAULT_PLAYERS"
        val COURTS_KEY = "DEFAULT_COURTS"

        val DEFAULT_COURTS = 1
        val DEFAULT_PLAYERS = 1
        val DEFAULT_PRICE_PER_UNIT = "1.0";

        fun getStoredPayment(context: Context) : Payment {
            val preferences = context.applicationContext
                .getSharedPreferences(PREFERENCE_FILENAME, Context.MODE_PRIVATE)
            val payment = Payment(
                preferences.getInt(COURTS_KEY, DEFAULT_COURTS),
                preferences.getInt(PLAYERS_KEY, DEFAULT_PLAYERS),
                preferences.getString(PRICE_PER_UNIT_KEY, DEFAULT_PRICE_PER_UNIT).toString()
            )

            Log.e(LOG_TAG, "getStoredPayment(): $payment")
            return payment;
        }

        fun storePayment(context: Context, payment: Payment) {
            val preferences = context.applicationContext
                .getSharedPreferences(PREFERENCE_FILENAME, Context.MODE_PRIVATE)
                .edit()
            preferences.putString(PRICE_PER_UNIT_KEY, payment.pricePerUnitText)
            preferences.putInt(PLAYERS_KEY, payment.players)
            preferences.putInt(COURTS_KEY, payment.courts)
            preferences.commit();
        }
    }

    data class Payment(var courts: Int, var players: Int, var pricePerUnitText: String) : java.io.Serializable {
        val amount: Double
            get() {
                val decimalFormat = DecimalFormat.getInstance(Locale.getDefault()) as DecimalFormat
                val pricePerUnit = decimalFormat.parse(pricePerUnitText) ?: return -1.0
                return courts * pricePerUnit.toDouble() / players
            }
    }


    private fun onSave() {

        storePayment(this, Payment(
            numberFieldSeats.text.toString().toInt(),
            numberFieldPlayers.text.toString().toInt(),
            numberFieldPricePerSeat.text.toString()
        ))

        finish()
    }


}