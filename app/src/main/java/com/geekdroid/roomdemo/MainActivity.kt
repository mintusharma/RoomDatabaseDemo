package com.geekdroid.roomdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.geekdroid.roomdemo.ui.theme.RoomDemoTheme

class MainActivity : ComponentActivity() {

    private val viewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.init(this)

        setContent {
            RoomDemoTheme {
                val student = viewModel.getAllStudent()?.collectAsStateWithLifecycle(emptyList())

                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
                    StudentScreen(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        students = student?.value ?:emptyList(),
                        onSave = {name: String,age: String ->
                            viewModel.save(name, age)
                        },
                        onUpdate = {name: String,age: String,id: Int->
                            viewModel.update(name, age, id)
                        },
                        onDelete = {id-> viewModel.delete(id)}
                    )
                }
            }
        }
    }
}
