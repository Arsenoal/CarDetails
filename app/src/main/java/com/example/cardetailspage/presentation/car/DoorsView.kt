package com.example.cardetailspage.presentation.car

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardetailspage.R
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.repository.common.asString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun DoorsView(
    carDoorState: DoorState,
    currentCar: CarDetails,
    onDoorStateChange: (String, DoorState) -> Unit,
) {

    Column {
        Row {
            Text(text = stringResource(id = R.string.doors),
                fontSize = 16.sp,
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold
            )

            Divider(modifier = Modifier
                .padding(8.dp, 0.dp)
                .width(2.dp)
                .height(20.dp),
                color = MaterialTheme.colors.primaryVariant,
                thickness = 2.dp)

            Text(text =  carDoorState.asString(),
                fontSize = 16.sp,
                color = MaterialTheme.colors.primaryVariant
            )
        }
        Card(modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp),
            backgroundColor = MaterialTheme.colors.background,
            shape = RoundedCornerShape(4.dp)
        ) {

            val scope = rememberCoroutineScope()

            Row(modifier = Modifier.padding(10.dp)) {

                var isLockedLoading by rememberSaveable { mutableStateOf(false) }
                var isUnlockedLoading by rememberSaveable { mutableStateOf(false) }

                var isLocking by rememberSaveable { mutableStateOf(false) }
                var isUnlocking by rememberSaveable { mutableStateOf(false) }

                var isShowUnlockDialog by remember { mutableStateOf(false) }
                var isShowLockDialog by remember { mutableStateOf(false) }

                if(isUnlocking) {
                    isShowUnlockDialog = false
                    isUnlockedLoading = true
                    isLockedLoading = false
                    LaunchedEffect(key1 = "Unlocking") {
                        withContext(Dispatchers.Main) {
                            onDoorStateChange.invoke(currentCar.id, DoorState.Processing)
                            currentCar.doorState = DoorState.Processing
                        }
                        delay(4800)
                        withContext(Dispatchers.Main) {
                            onDoorStateChange.invoke(currentCar.id, DoorState.Unlocked)
                            currentCar.doorState = DoorState.Unlocked
                        }
                        delay(200)
                        isUnlockedLoading = false
                        isLockedLoading = false
                        isUnlocking = false
                    }
                }

                if(isLocking) {
                    isShowLockDialog = false
                    isLockedLoading = true
                    isUnlockedLoading = false
                    LaunchedEffect(key1 = "Locking") {
                        withContext(Dispatchers.Main) {
                            onDoorStateChange.invoke(currentCar.id, DoorState.Processing)
                            currentCar.doorState = DoorState.Processing
                        }
                        delay(4800)
                        withContext(Dispatchers.Main) {
                            onDoorStateChange.invoke(currentCar.id, DoorState.Locked)
                            currentCar.doorState = DoorState.Locked
                        }
                        delay(200)
                        isUnlockedLoading = false
                        isLockedLoading = false
                        isLocking = false
                    }
                }

                if(isShowUnlockDialog) {
                    DoorStateAlertDialog(
                        onDismissRequest = { isShowUnlockDialog = false },
                        title = stringResource(id = R.string.are_you_sure),
                        message = stringResource(id = R.string.please_confirm_unlock, currentCar.name),
                        negativeButtonText = stringResource(id = R.string.cancel),
                        positiveButtonText = stringResource(id = R.string.yes_unlock),
                        onPositiveButtonClick = { isUnlocking = true }
                    )
                }

                if (isShowLockDialog) {
                    DoorStateAlertDialog(
                        onDismissRequest = { isShowLockDialog = false },
                        title = stringResource(id = R.string.are_you_sure),
                        message = stringResource(id = R.string.please_confirm_lock, currentCar.name),
                        negativeButtonText = stringResource(id = R.string.cancel),
                        positiveButtonText = stringResource(id = R.string.yes_lock),
                        onPositiveButtonClick = { isLocking = true }
                    )
                }

                Card(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 5.dp, 0.dp)
                        .size(70.dp)
                        .clickable {
                            if (carDoorState != DoorState.Processing) {
                                isShowLockDialog = true
                            }
                        },
                    shape = RoundedCornerShape(35.dp),
                    backgroundColor =
                    if(isLockedLoading) MaterialTheme.colors.background
                    else if(carDoorState == DoorState.Locked) MaterialTheme.colors.primary
                    else MaterialTheme.colors.secondary) {

                    if(isLockedLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(5.dp),
                            color = MaterialTheme.colors.primary,
                            strokeWidth = 4.dp
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(15.dp),
                            painter = painterResource(id = R.drawable.act_lock),
                            contentDescription = null)
                    }
                }

                Card(
                    modifier = Modifier
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .size(70.dp)
                        .clickable {
                            if (carDoorState != DoorState.Processing) {
                                isShowUnlockDialog = true
                            }
                        },
                    shape = RoundedCornerShape(35.dp),
                    backgroundColor =
                    if(isUnlockedLoading) MaterialTheme.colors.background
                    else if(carDoorState == DoorState.Unlocked) MaterialTheme.colors.primary
                    else MaterialTheme.colors.secondary) {

                    if(isUnlockedLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(5.dp),
                            color = MaterialTheme.colors.primary,
                            strokeWidth = 4.dp
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(15.dp),
                            painter = painterResource(id = R.drawable.act_unlock),
                            contentDescription = null)
                    }
                }
            }
        }
    }
}