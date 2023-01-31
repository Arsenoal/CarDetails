package com.example.cardetailspage.car.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cardetailspage.R
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.empty
import com.example.cardetailspage.car.presentation.base.AppTheme
import com.example.cardetailspage.car.presentation.common.BottomNavItem
import com.example.cardetailspage.car.repository.common.asString
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    @OptIn(ExperimentalPagerApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent  {
            AppTheme {

                var carDetails by remember { mutableStateOf(listOf(CarDetails.empty())) }

                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomAppBar(backgroundColor = MaterialTheme.colors.background) {

                            //For future dynamic approach we can define current  selected state and in isSelected
                            // variable initialization we can compare current state with BottomNavItem state, and we can change
                            // the state on item click, also the same state should be used to draw current page
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                BottomNavItem(
                                    stringResource(id = R.string.main_home),
                                    Icons.Filled.Home,
                                    isSelected = true
                                )
                                BottomNavItem(
                                    stringResource(id = R.string.main_vehicle),
                                    Icons.Filled.Home
                                )
                                BottomNavItem(
                                    stringResource(id = R.string.main_location),
                                    Icons.Filled.LocationOn
                                )
                                BottomNavItem(
                                    stringResource(id = R.string.main_more),
                                    Icons.Filled.MoreHoriz
                                )
                            }
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween) {
                        var snackText by remember { mutableStateOf("") }

                        HomePage(
                            carDetails = carDetails,
                            onSnackbarText = { snackbarText ->
                                snackText = snackbarText
                            }) { id, state -> mainViewModel.changeDoorState(id, state) }

                        if(snackText.isNotBlank() && snackText != DoorState.Processing.asString()) {
                            Snackbar(modifier = Modifier
                                .padding(5.dp, 0.dp, 0.dp, 57.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = "${stringResource(id = R.string.doors)} $snackText",
                                        color = MaterialTheme.colors.background)

                                    Image(painter = painterResource(id = R.drawable.sym_check_fill), contentDescription = null)
                                }
                            }

                            rememberCoroutineScope().launch {
                                delay(2000)
                                snackText = ""
                            }
                        }
                    }
                }

                mainViewModel.carDetailsLiveData.observe(this) { mList -> carDetails = mList }

                mainViewModel.getCarDetails()
            }
        }
    }
}