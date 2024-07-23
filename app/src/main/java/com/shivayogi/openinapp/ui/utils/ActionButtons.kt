package com.shivayogi.openinapp.ui.dashboard.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shivayogi.openinapp.R
import com.shivayogi.openinapp.ui.theme.DashboardTheme
import com.shivayogi.openinapp.ui.theme.TextColor_BLACK
import com.shivayogi.openinapp.ui.theme.color_background_green
import com.shivayogi.openinapp.ui.theme.color_green


@Composable
fun ActionButtons(
    displayText: String,
    innerColor: Color,
    outlineColor: Color,
    textColor: Color,
    imageResource: Int,
    imageColor: Color,
    onClick: () -> Unit
) {
        OutlinedButton(

            shape = RoundedCornerShape(8),
            contentPadding = PaddingValues(16.dp),
            onClick = { onClick() },
            border = BorderStroke(2.dp, outlineColor),
            modifier = Modifier
                .padding(16.dp,6.dp)
                .fillMaxWidth()
                .background(innerColor)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Icon(
                    painter = painterResource(id = imageResource),
                    contentDescription = "link Icon",
                    modifier = Modifier.size(24.dp),
                    tint = imageColor
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    displayText,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )
            }


    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DashboardTheme {
        ActionButtons(
            "Talk with us",
            color_background_green,
            color_green,
            TextColor_BLACK,
            R.drawable.ic_whatsapp,
            color_green
        ) {
            //WhatsApp link action Intent
        }
    }
}