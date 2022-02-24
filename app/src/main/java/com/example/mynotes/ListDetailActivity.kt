package com.example.mynotes

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.mynotes.databinding.ListDetailActivityBinding
import com.example.mynotes.ui.detail.ListDetailFragment
import com.example.mynotes.ui.main.MainViewModel
import com.example.mynotes.ui.main.MainViewModelFactory

class ListDetailActivity : AppCompatActivity() {
    private lateinit var binding: ListDetailActivityBinding
    private lateinit var viewModel: MainViewModel
    lateinit var plainText: EditText
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        )
            .get(MainViewModel::class.java)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = viewModel.list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {

        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE)
        var loadnote = sharedPreferences.getString(viewModel.list.name,"")
        plainText = findViewById(R.id.plainText)
        plainText.setText(loadnote)

        super.onPostCreate(savedInstanceState)
    }


    override fun onBackPressed() {

        plainText = findViewById(R.id.plainText)
        val insertedText = plainText.text.toString()
        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(viewModel.list.name,insertedText).apply()


        super.onBackPressed()
    }
}