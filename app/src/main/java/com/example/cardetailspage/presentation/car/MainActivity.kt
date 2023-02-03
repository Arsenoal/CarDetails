package com.example.cardetailspage.presentation.car

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.cardetailspage.R
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.empty
import com.example.cardetailspage.presentation.base.AppTheme
import com.example.cardetailspage.presentation.common.BottomNavItems
import com.example.cardetailspage.repository.common.asString
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

                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    scaffoldState = scaffoldState,
                    bottomBar = { BottomAppBar(backgroundColor = MaterialTheme.colors.background) { BottomNavItems() } }
                ) {
                    var carDetails by remember { mutableStateOf(listOf(CarDetails.empty())) }

                    BoxWithConstraints(
                        modifier = Modifier.fillMaxSize()) {
                            var snackText by remember { mutableStateOf("") }

                            HomePage(
                                carDetails = carDetails,
                                onUpdateEvent = { carId -> mainViewModel.setUpdateDate(carId) },
                                onSnackbarText = { snackbarText -> snackText = snackbarText }
                            ) { id, state -> mainViewModel.changeDoorState(id, state) }

                            if(snackText.isNotBlank() && snackText != DoorState.Processing.asString()) {
                                Snackbar(modifier = Modifier
                                    .padding(5.dp, 0.dp, 0.dp, 57.dp)
                                    .align(Alignment.BottomCenter)
                                    .zIndex(2f)
                                ) {
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

                    mainViewModel.carDetailsLiveData.observe(this) { mList -> carDetails = mList }

                    mainViewModel.getCarDetails()
                }
            }
        }
    }
}