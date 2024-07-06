package com.ftq.pricetag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.ui.NavigationUI
import com.ftq.pricetag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
//
//        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
//        }


        val drawerLayout: DrawerLayout = binding.drawerLayout
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.retailerFragment, R.id.nav_branch, R.id.nav_product, R.id.nav_history
            ), drawerLayout
        )


        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_exit ->{
                    exitAlertDialog()
                    true
                }
                R.id.nav_about ->{
                    aboutDialog(this)
                    true
                }
                else->{
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun aboutDialog(context: Context) {
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_layout, null)

        AlertDialog.Builder(context)
            .setTitle("About")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    private fun exitAlertDialog() {
        // Buat instance dari AlertDialog.Builder
        val alertDialogBuilder = AlertDialog.Builder(this)

        // Set judul dan pesan untuk alert dialog
        alertDialogBuilder.setTitle("Notice")
        alertDialogBuilder.setMessage("Are you sure to exit?")

        // Menambahkan tombol positif pada alert dialog
        alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
            // Tombol "Ya" ditekan, tutup aplikasi
            finish()
        }

        // Menambahkan tombol negatif pada alert dialog
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            // Tombol "Tidak" ditekan, tutup alert dialog
            dialog.dismiss()
        }

        // Buat dan tampilkan alert dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}