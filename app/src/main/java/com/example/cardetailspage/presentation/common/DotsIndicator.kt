package com.example.cardetailspage.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(
    modifier: Modifier,
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
){
    LazyRow(
        modifier = modifier
    ) {

        items(totalDots) { index ->
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(35.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(if (index == selectedIndex) selectedColor else unSelectedColor)
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}