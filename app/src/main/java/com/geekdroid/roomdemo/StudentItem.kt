package com.geekdroid.roomdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StudentItem(
    modifier: Modifier = Modifier,
    id: Int,
    name: String,
    age: String,
    onEdit: () -> Unit,
    onDelete: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE5EAEA))
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(name, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(12.dp))
            Text(age, style = MaterialTheme.typography.bodyLarge)
        }

        IconButton(onClick = onEdit) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }

        IconButton(onClick = { onDelete(id) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Red)
        }
    }
}

@Preview
@Composable
fun StudentItemPreview(modifier: Modifier = Modifier) {
    StudentItem(
        modifier = modifier,
        id = 1,
        name = "Geek",
        age = "29",
        onEdit = { },
        onDelete = { }
    )
}
