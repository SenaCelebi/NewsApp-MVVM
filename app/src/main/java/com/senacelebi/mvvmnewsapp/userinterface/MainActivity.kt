package com.senacelebi.mvvmnewsapp.userinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.senacelebi.mvvmnewsapp.R
import com.senacelebi.mvvmnewsapp.database.ArticleDB
import com.senacelebi.mvvmnewsapp.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = Repository(ArticleDB(this))
        val vmProviderFactory = VMProvider(repo)
        viewModel = ViewModelProvider(this, vmProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(NavigationHostFragment.findNavController())
    }
}
