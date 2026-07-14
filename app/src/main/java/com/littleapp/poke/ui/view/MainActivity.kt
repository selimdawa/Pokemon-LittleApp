package com.littleapp.poke.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.littleapp.poke.R
import com.littleapp.poke.databinding.ActivityMainBinding
import com.littleapp.poke.domain.SelectedListener
import com.littleapp.poke.utils.THEME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SelectedListener {

    private lateinit var binding: ActivityMainBinding
    var context = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSelected(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}