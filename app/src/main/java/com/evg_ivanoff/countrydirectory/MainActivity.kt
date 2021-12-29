package com.evg_ivanoff.countrydirectory

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.evg_ivanoff.countrydirectory.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.resultLayout.visibility = View.INVISIBLE

        binding.searchButton.setOnClickListener {
            val countryName = binding.countryNameEditText.text.toString()

            //корутина - легковесный поток, выполняется отдельно от основного потока приложения
            lifecycleScope.launch {
                try {
                    val countries = restCountriesAPI.getCountryByName(countryName)
                    val country = countries[0]

                    binding.countryNameTextView.text = country.name
                    binding.areaTextView.text = formatNumber(country.area)
                    binding.capitalTextView.text = country.capital
                    binding.populationTextView.text = formatNumber(country.population)
                    binding.languagesTextView.text = languagesToString(country.languages)

                    loadSvg(binding.imageView, country.flag)

                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                } catch (e:Exception){
                    binding.statusTextView.text = "Country is not found"
                    binding.statusImageView.setImageResource(R.drawable.ic_baseline_not_interested_24)
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }

        }
    }


}

