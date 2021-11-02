package com.StudioNeko.Appka

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.motion.utils.ViewOscillator
import com.StudioNeko.Appka.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.content_main.*
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val PrimeTextSize = 20f
    private val NonPrimeTextSize = 11f

    private var cookies = 1

    private var ClickerUpgradeLevel = 1
    private var ClickerCost = 100

    private var TimerUpgradeLevel = 1
    private var TimerCost = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        btn_1.setOnClickListener {
            //CheckNumber()
        }

        btn_2.setOnClickListener {
            cookies += ClickerUpgradeLevel
        }
        btn_3.setOnClickListener {
            ClickerUpgrade()
        }
        btn_4.setOnClickListener {
            TimerUpgrade()
        }
        AutoPlay()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun CheckNumber(){
        if(PrimeNumber(NumberInput.text.toString().toInt())){
            PrimeTextView.text = "Number is Prime and its position is " + WhichPrimeNumber(NumberInput.text.toString().toInt())
            PrimeTextView.setTextColor(Color.RED)
            PrimeTextView.textSize = PrimeTextSize
        } else{
            PrimeTextView.text = "Number is not Prime"
            PrimeTextView.setTextColor(Color.GREEN)
            PrimeTextView.textSize = NonPrimeTextSize
        }
    }

    fun PrimeNumber(a: Int): Boolean{
        var flags = 0
        if(a == 1)
            return false

        for(i in 1..a){
            if(a % i == 0){
                flags++
            }
            if(flags > 2){
                return false
            }
        }
        return true
    }

    fun WhichPrimeNumber(a: Int): Int{
        var position = 0
        for(i in 2..a){
            var index = 0
            for(j in 1..i/2){
                if(i % j == 0){
                    index++
                }
            }
            if(index < 2){
                position++
            }
        }
        return position
    }

    fun AutoPlay(){
        PrimeTextView.text = cookies.toString()
        cookies += TimerUpgradeLevel

        Timer("SettingUp", false).schedule(500){
            AutoPlay()
        }
    }

    fun ClickerUpgrade(){
        if(cookies >= 100 * ClickerUpgradeLevel) {
            cookies -= 100 * ClickerUpgradeLevel
            ClickerUpgradeLevel++
        }
    }

    fun TimerUpgrade(){
        if(cookies >= 500 * TimerUpgradeLevel) {
            cookies -= 500 * TimerUpgradeLevel
            TimerUpgradeLevel++
        }
    }
}