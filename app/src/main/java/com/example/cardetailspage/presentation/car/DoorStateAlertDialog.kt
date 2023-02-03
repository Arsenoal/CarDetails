package com.example.cardetailspage.presentation.car

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DoorStateAlertDialog(
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    negativeButtonText: String,
    positiveButtonText: String,
    onNegativeButtonClick: () -> Unit = { onDismissRequest.invoke() },
    onPositiveButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest.invoke() },
        title = { Text(fontSize = 20.sp, text = title) },
        text = { Text(fontSize = 18.sp, text = message) },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.clickable { onNegativeButtonClick.invoke() },
                    color = MaterialTheme.colors.secondaryVariant,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    text = negativeButtonText
                )

                Row(modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.secondaryVariant)
                    .clickable { onPositiveButtonClick.invoke() }) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        color = MaterialTheme.colors.background,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = positiveButtonText
                    )
                }
            }
        }
    )
}