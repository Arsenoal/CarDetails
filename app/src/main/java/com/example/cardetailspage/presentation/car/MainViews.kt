package com.example.cardetailspage.presentation.car

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cardetailspage.R
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.copyWithDateLoss
import com.example.cardetailspage.entity.car.getLastUpdateTime
import com.example.cardetailspage.presentation.common.DotsIndicator
import com.example.cardetailspage.repository.common.asString
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@ExperimentalPagerApi
@Composable
fun HomePage(
    carDetails: List<CarDetails>,
    onSnackbarText: (String) -> Unit,
    onUpdateEvent: (String) -> Unit,
    onDoorStateChange: (String, DoorState) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 75.dp)) {
        item {
            val pagerState = rememberPagerState()

            var currentCar by remember { mutableStateOf(carDetails[0]) }

            var carDoorState by remember { mutableStateOf(currentCar.doorState) }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalPager(
                    modifier = Modifier
                        .height(350.dp)
                        .fillMaxWidth(),
                    count = carDetails.size,
                    state = pagerState) { page ->

                    Scaffold(topBar = {
                        TopAppBar(backgroundColor = MaterialTheme.colors.background) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = carDetails[page].name,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.secondary,
                                    fontSize = 22.sp)

                                Divider(
                                    modifier = Modifier
                                        .padding(15.dp, 0.dp)
                                        .width(2.dp)
                                        .height(30.dp),
                                    color = MaterialTheme.colors.primary,
                                    thickness = 2.dp)
                                Image(
                                    painter = painterResource(id = R.drawable.notif_gas),
                                    contentDescription = null)

                                Text(
                                    text = "${carDetails[page].fuel}mi",
                                    color = MaterialTheme.colors.secondary,
                                    fontWeight = FontWeight.Bold)

                            }
                        }
                    }) {

                        Column(modifier = Modifier
                            .padding(0.dp, 25.dp, 0.dp, 0.dp)
                            .height(250.dp)
                            .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {

                            currentCar = carDetails[page]
                            carDoorState = currentCar.doorState

                            var lastUpdatedTime by remember { mutableStateOf(currentCar.getLastUpdateTime()) }

                            Row(
                                modifier = Modifier
                                    .clickable(
                                        onClick = {
                                            onUpdateEvent.invoke(currentCar.id)
                                            currentCar = currentCar.copyWithDateLoss()
                                            carDetails[page].dateUpdated = System.currentTimeMillis()
                                            lastUpdatedTime = currentCar.getLastUpdateTime()
                                        }
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Image(
                                    painter = painterResource(id = R.drawable.btn_refresh),
                                    contentDescription = null
                                )

                                Text(
                                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                                    text = stringResource(id = R.string.main_update, lastUpdatedTime)
                                )

                                rememberCoroutineScope().launch {
                                    while(true) {
                                        delay(1000L)
                                        withContext(Dispatchers.Main) {
                                            lastUpdatedTime = currentCar.getLastUpdateTime()
                                        }
                                    }
                                }
                            }

                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                model = carDetails[page].image,
                                contentDescription = null
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(4.dp))

                DotsIndicator(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally),
                    totalDots = carDetails.size,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = MaterialTheme.colors.primary,
                    unSelectedColor = MaterialTheme.colors.primaryVariant
                )
            }



            Spacer(modifier = Modifier.padding(15.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 0.dp)) {

                DoorsView(
                    carDoorState = carDoorState,
                    currentCar = currentCar) { id, doorState ->
                    onSnackbarText.invoke(doorState.asString())
                    onDoorStateChange.invoke(id, doorState)
                }

                EngineView()
            }
        }
    }

}