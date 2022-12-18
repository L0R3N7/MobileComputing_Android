package at.htl.leonding.bhitm5.requistermarathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.navController = findNavController(R.id.fragmentContainerView)
        NavigationUI.setupActionBarWithNavController(this, this.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return this.navController.navigateUp()
    }
}