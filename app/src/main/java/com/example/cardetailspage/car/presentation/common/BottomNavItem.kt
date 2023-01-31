package com.example.cardetailspage.car.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavItem(
    itemText: String,
    itemImage: ImageVector,
    isSelected: Boolean = false,
    onItemClick: () -> Unit = {}
) {

    val itemFillColor = if(isSelected) MaterialTheme.colors.primary
                else MaterialTheme.colors.secondary

    Column(
        modifier = Modifier
            .clickable(onClick = { onItemClick.invoke() }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            modifier = Modifier.width(50.dp),
            color = if(isSelected) itemFillColor else MaterialTheme.colors.background,
            thickness = 2.dp)

        Image(
            imageVector = itemImage,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = itemFillColor)
        )

        Text(
            text = itemText,
            color = itemFillColor,
            fontSize = 14.sp
        )
    }
}