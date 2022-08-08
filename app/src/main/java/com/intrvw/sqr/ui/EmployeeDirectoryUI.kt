package com.intrvw.sqr.ui

import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.intrvw.sqr.data.EmployeeInfo
import java.util.*

@Composable
fun EmployeeListView() {
    val vm: DirectoryViewModel = viewModel()
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 6.dp,
            horizontal = 6.dp
        ),
        modifier = Modifier.background(Color.White),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text(text = "Employee Directory")
        }

        items(vm.state.value) {employee ->
            EmployeeListItem(employee = employee)
        }
    }
}

@Composable
fun EmployeeListItem(employee: EmployeeInfo) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                // uses Coil to load images
                painter = rememberAsyncImagePainter(model = employee.smallIcon!!),
                contentDescription = "employee photo thumbnail",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Column {
                Text( // employee name
                    text = employee.name!!,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    fontSize = 10.sp,
                    text = employee.email!!,
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    fontSize = 10.sp,
                    text = PhoneNumberUtils.formatNumber(employee.phone!!, Locale.US.country),
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    fontSize = 10.sp,
                    text = employee.teamName!!,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

//@Composable
//fun EmployeeInfoView(employee: Employee) {
////    Card {
////
////    }
//}

@Preview(name = "Employee UI Preview", showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    EmployeeListView()
}