package com.example.cardetailspage.presentation.car

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardetailspage.R

@Composable
fun EngineView() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp, 0.dp)) {

        Column {
            Row {
                Text(
                    text = stringResource(id = R.string.engine),
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.Bold
                )
            }
            Card(
                modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp),
                backgroundColor = MaterialTheme.colors.background,
                shape = RoundedCornerShape(4.dp)
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    Card(
                        modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp).size(70.dp),
                        shape = RoundedCornerShape(35.dp),
                        backgroundColor = MaterialTheme.colors.secondary) {

                        Row(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
                                text = stringResource(id = R.string.start).uppercase(),
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.background
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp).size(70.dp),
                        shape = RoundedCornerShape(35.dp),
                        backgroundColor = MaterialTheme.colors.secondary) {

                        Row(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
                                text = stringResource(id = R.string.stop).uppercase(),
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.background
                            )
                        }
                    }
                }
            }
        }
    }
}