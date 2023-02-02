package com.example.cardetailspage.presentation.car

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            Text(
                text = stringResource(id = R.string.doors),
                fontSize = 16.sp,
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold
            )

            Divider(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .width(2.dp)
                    .height(20.dp),
                color = MaterialTheme.colors.primaryVariant,
                thickness = 2.dp)

            Text(
                text =  carDoorState.asString(),
                fontSize = 16.sp,
                color = MaterialTheme.colors.primaryVariant
            )
        }
        Card(
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp),
            backgroundColor = MaterialTheme.colors.background,
            shape = RoundedCornerShape(4.dp)
        ) {

            val scope = rememberCoroutineScope()

            Row(modifier = Modifier.padding(10.dp)) {

                var isLockedLoading by remember { mutableStateOf(false) }

                var isUnlockedLoading by remember { mutableStateOf(false) }

                var isShowUnlockDialog by remember { mutableStateOf(false) }

                var isShowLockDialog by remember { mutableStateOf(false) }

                if(isShowUnlockDialog) {
                    AlertDialog(
                        onDismissRequest = { isShowUnlockDialog = false },
                        title = { Text(
                            fontSize = 20.sp,
                            text = stringResource(id = R.string.are_you_sure))
                        },
                        text = { Text(
                            fontSize = 18.sp,
                            text = stringResource(id = R.string.please_confirm_unlock, currentCar.name))
                        },
                        buttons = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    modifier = Modifier.clickable { isShowUnlockDialog = false },
                                    color = MaterialTheme.colors.secondaryVariant,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    text = stringResource(id = R.string.cancel)
                                )

                                Row(modifier = Modifier
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colors.secondaryVariant)
                                    .clickable {
                                        isShowUnlockDialog = false
                                        isUnlockedLoading = true
                                        isLockedLoading = false
                                        currentCar.doorState = DoorState.Processing
                                        scope.launch {
                                            delay(4800)
                                            withContext(Dispatchers.Main) {
                                                onDoorStateChange.invoke(
                                                    currentCar.id,
                                                    DoorState.Unlocked
                                                )
                                                currentCar.doorState = DoorState.Unlocked
                                            }
                                            delay(200)
                                            isUnlockedLoading = false
                                            isLockedLoading = false
                                        }
                                    }) {
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        color = MaterialTheme.colors.background,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        text = stringResource(id = R.string.yes_unlock)
                                    )
                                }
                            }
                        }
                    )
                }

                if (isShowLockDialog) {
                    AlertDialog(
                        onDismissRequest = { isShowLockDialog = false },
                        title = { Text(
                            fontSize = 20.sp,
                            text = stringResource(id = R.string.are_you_sure))
                        },
                        text = { Text(
                            fontSize = 18.sp,
                            text = stringResource(id = R.string.please_confirm_lock, currentCar.name))
                        },
                        buttons = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    modifier = Modifier.clickable { isShowLockDialog = false },
                                    color = MaterialTheme.colors.secondaryVariant,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    text = stringResource(id = R.string.cancel)
                                )

                                Row(modifier = Modifier
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colors.secondaryVariant)
                                    .clickable {
                                        isShowLockDialog = false
                                        isLockedLoading = true
                                        isUnlockedLoading = false
                                        currentCar.doorState = DoorState.Processing
                                        scope.launch {
                                            delay(4800)
                                            withContext(Dispatchers.Main) {
                                                onDoorStateChange.invoke(
                                                    currentCar.id,
                                                    DoorState.Locked
                                                )
                                                currentCar.doorState = DoorState.Locked
                                            }
                                            delay(200)
                                            isUnlockedLoading = false
                                            isLockedLoading = false
                                        }
                                    }) {
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        color = MaterialTheme.colors.background,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        text = stringResource(id = R.string.yes_lock)
                                    )
                                }
                            }
                        }
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