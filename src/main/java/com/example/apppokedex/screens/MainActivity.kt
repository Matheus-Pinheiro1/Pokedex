package com.example.apppokedex.screens

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apppokedex.R
import com.example.apppokedex.ui.theme.AppPokedexTheme

class MainActivity : ComponentActivity() {


    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        listView = findViewById(R.id.listView)

        val items: List<String> = listOf("Item 1", "Item 2")
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items )
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }


            @OptIn(ExperimentalMaterial3Api::class)
            @Composable
            fun MainScreen() {
                val navController = rememberNavController()
                AppPokedexTheme {
                    Scaffold(topBar = {
                        AppBar(navController) // Chama a função AppBar
                    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }

            @OptIn(ExperimentalMaterial3Api::class)
            @Composable
            fun AppBar(navController: NavHostController) {
                var title by remember { mutableStateOf("") }

                LaunchedEffect(navController.currentBackStackEntryFlow) {
                    navController.currentBackStackEntryFlow.collect {
                        title = it.destination.route ?: ""
                    }
                }

                Surface(shadowElevation = 3.dp) {
                    TopAppBar(
                        title = {
                            Text(
                                "Home",
//                    getTitleForRoute(title).uppercase(),
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        modifier = Modifier,
                        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
                    )
                }
            }

            private fun Any.uppercase(): Any {
                TODO("Not yet implemented")
            }

            fun getTitleForRoute(title: Any): Any {
                TODO("Not yet implemented")
            }

            @Composable
            fun Greeting(name: String, modifier: Modifier = Modifier) {
                Text(
                    text = "Hello $name!",
                    modifier = modifier
                )
            }
        }
        )
}}


