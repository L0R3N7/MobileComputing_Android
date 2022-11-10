package at.htl.leonding.bhitm5.payyours

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import at.htl.leonding.bhitm5.payyours.databinding.ActivityMainBinding
import at.htl.leonding.bhitm5.payyours.model.Payment
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val PAYMENT_KEY = "PAYMENT"
    private lateinit var binding: ActivityMainBinding;
    private lateinit var payment: Payment;


    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                val value = it.data?.getStringExtra("input")
            }
            payment = SettingsActivity.getStoredPayment(this)
            updatePaymentTextView()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddCourts.setOnClickListener { add(binding.editTextCourts, 1) }
        binding.btnRemoveCourts.setOnClickListener { add(binding.editTextCourts, -1) }
        binding.btnAddPlayer.setOnClickListener { add(binding.editTextPlayer, 1) }
        binding.btnRemovePlayer.setOnClickListener { add(binding.editTextPlayer, -1) }

        payment = if (savedInstanceState == null) {
            SettingsActivity.getStoredPayment(this)
        }else{
            savedInstanceState.get(PAYMENT_KEY) as Payment
        }

        Log.d("Test Serizalation", "$payment")

        binding.editTextCourts.setText("${payment.courts}")
        binding.editTextPlayer.setText("${payment.players}")
        updatePaymentTextView()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.d(TAG, "onSaveInstanceState(), $payment")
        outState.putSerializable(PAYMENT_KEY, payment)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    /*override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState()")
        super.onRestoreInstanceState(savedInstanceState)
        payment = savedInstanceState.getSerializable(PAYMENT_KEY) as Payment
    }*/

    private fun add(textView: TextView, i: Int) {
        val newValue = textView.text.toString().toInt() + i;
        textView.setText("${newValue}")
        updatePaymentTextView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item?.itemId){
            R.id.menu_item_quit -> {
                finish()
                true
            }
            R.id.menu_item_settings -> {
                Log.d("MainActivity", "Settings menu called");
                val intent = Intent(this, SettingsActivity::class.java)
                getResult.launch(intent)
                //startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun updatePaymentTextView() {
        payment.players = binding.editTextPlayer.text.toString().toInt()
        payment.courts = binding.editTextCourts.text.toString().toInt()
        val amountText = if(payment.amount > 0){
            String.format(Locale.getDefault(), "%.2f", payment.amount)
        }else{
            "-"
        }
        binding.tvAmountToPay.setText(amountText)
        binding.btnRemoveCourts.isEnabled = payment.courts > 1
        binding.btnRemovePlayer.isEnabled = payment.players > 1
        Log.d(TAG, "updatePaymentTextView(); Payment updated: $payment")
    }
}