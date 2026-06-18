package com.geekdroid.roomdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentScreen(
    modifier: Modifier = Modifier,
    students: List<StudentEntity> = emptyList(),
    onSave: (String, String) -> Unit,
    onUpdate: (String, String, Int) -> Unit,
    onDelete: (Int) -> Unit
) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var selectedStudent by remember { mutableStateOf<StudentEntity?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                selectedStudent = null
                name = ""
                age = ""
            },
            title = { Text(if (selectedStudent == null) "Add Student" else "Update Student") },
            text = {
                Column {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text("Enter your name...") }
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = age,
                        onValueChange = { age = it },
                        placeholder = { Text("Enter your age...") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (selectedStudent == null) {
                        onSave.invoke(name, age)
                    } else {
                        onUpdate.invoke(name, age, selectedStudent!!.id)
                    }
                    showDialog = false
                    selectedStudent = null
                    name = ""
                    age = ""
                }) {
                    Text(if (selectedStudent == null) "Save" else "Update")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    selectedStudent = null
                    name = ""
                    age = ""
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Room Database") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        "Student List",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 24.dp)
                            .padding(bottom = 12.dp)
                            .fillMaxWidth()
                    )
                }
                items(students) { student ->

                    StudentItem(
                        modifier = Modifier.padding(8.dp),
                        id = student.id,
                        name = student.name,
                        age = student.age,
                        onEdit = {
                            selectedStudent = student
                            name = student.name
                            age = student.age
                            showDialog = true
                        },
                        onDelete = { onDelete.invoke(student.id) }
                    )

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun StudentScreenPreview(modifier: Modifier = Modifier) {

    StudentScreen(
        modifier = modifier,
        onSave = { a, b -> },
        onUpdate = { a, b, c -> },
        onDelete = {},
    )
}