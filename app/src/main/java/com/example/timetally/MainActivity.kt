package com.example.timetally

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.timetally.ViewModel.EmployeeViewModel
import com.example.timetally.Fragment.MainFragment
import com.example.timetally.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.Green)
       // window.navigationBarColor = ContextCompat.getColor(this, R.color.colorBlue)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.FrameLayoutID, MainFragment())
                .commit()
        }
    }
}