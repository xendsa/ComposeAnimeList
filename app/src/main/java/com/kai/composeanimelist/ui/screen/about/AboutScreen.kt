package com.kai.composeanimelist.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kai.composeanimelist.R
import com.kai.composeanimelist.ui.theme.ComposeAnimeListTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.kai), contentDescription = null,
            modifier = modifier
                .padding(16.dp)
                .size(300.dp)
                .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                .shadow(10.dp, CircleShape)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Hirzil Kaisan",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "hirzilkaisan3@gmail.com",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Bangkit 2023 Batch 2 Cohort that still have to learn lots of things",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfilePreview() {
    ComposeAnimeListTheme {
        AboutScreen()
    }
}