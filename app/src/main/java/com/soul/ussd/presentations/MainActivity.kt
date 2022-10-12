package com.soul.ussd.presentations

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.soul.ussd.R
import com.soul.ussd.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.soul.ussd.utils.gone
import com.soul.ussd.utils.visible
import com.soul.ussd.presentations.main.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val viewModel1: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(Color.WHITE)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> {
                    binding.toolbar.gone()
                }
                else -> {
                    binding.toolbar.visible()
                }
            }
            val upArrow = ResourcesCompat.getDrawable(
                resources, R.drawable.ic_baseline_arrow_back_ios_24, theme
            )
            supportActionBar!!.setHomeAsUpIndicator(upArrow)
        }
        viewModel1.onColorChange.observe(this) { color ->
            binding.toolbar.setBackgroundColor(
                ResourcesCompat.getColor(
                    resources,
                    color,
                    this.theme
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}