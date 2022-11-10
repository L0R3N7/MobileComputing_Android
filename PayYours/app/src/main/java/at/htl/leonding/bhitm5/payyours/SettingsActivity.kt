package at.htl.leonding.bhitm5.payyours

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import at.htl.leonding.bhitm5.payyours.databinding.ActivitySettingsBinding
import at.htl.leonding.bhitm5.payyours.model.Payment

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOk.setOnClickListener { onSave() }

        val payment = getStoredPayment(this)
        binding.numberFieldPricePerSeat.setText(payment.pricePerUnitText)
        binding.numberFieldSeats.setText(payment.courts.toString())
        binding.numberFieldPlayers.setText(payment.players.toString())
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




    private fun onSave() {

        storePayment(this, Payment(
            binding.numberFieldSeats.text.toString().toInt(),
            binding.numberFieldPlayers.text.toString().toInt(),
            binding.numberFieldPricePerSeat.text.toString()
        )
        )

        finish()
    }


}