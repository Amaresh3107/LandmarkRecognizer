package com.cg.thirdeye.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cg.thirdeye.presentation.navigation.NavigationRoute
import com.cg.thirdeye.presentation.ui.theme.BG_V1
import com.cg.thirdeye.presentation.ui.theme.BLUE_V1
import com.cg.thirdeye.presentation.ui.theme.Purple40

@Composable
fun HomeScreenComposable(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BG_V1)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "Third Eye",
                    style = TextStyle(
                        color = Purple40,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Default.AddCircle,
                    tint = BLUE_V1,
                    contentDescription = "More"
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                modifier = Modifier
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BLUE_V1
                ),
                onClick = {
                    navHostController.navigate(NavigationRoute.CAPTURE.route)
                }
            ) {
                Text(
                    text = "Capture",
                    style = TextStyle(
                        color = BG_V1,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}
