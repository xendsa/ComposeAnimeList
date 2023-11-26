package com.kai.composeanimelist.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kai.composeanimelist.R
import com.kai.composeanimelist.di.Injection
import com.kai.composeanimelist.ui.common.UiState
import com.kai.composeanimelist.ui.screen.ViewModelFactory
import com.kai.composeanimelist.ui.theme.ComposeAnimeListTheme

@Composable
fun DetailScreen(
    animeId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBackHome: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAnimeById(animeId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.anime.image,
                    data.anime.title,
                    data.anime.description,
                    onBackClick = navigateBackHome,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes
    image: Int,
    title: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(modifier = modifier) {
        Column(modifier = modifier
            .verticalScroll(rememberScrollState())
            .weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onBackClick() }
            )
            Box {
                Image(painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .padding(10.dp)
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomStart = 30.dp, bottomEnd = 30.dp))
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = description,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    ComposeAnimeListTheme {
        DetailContent(
            R.drawable.tate,
            "Tate No Yuusha",
            "After defeating the Spirit Tortoise, Naofumi has no time for rest. An attack from the next Guardian Beast is imminent, but the three other Cardinal Heroes have gone missing. So, Naofumi and his party set out to search for the legendary trio.",
            onBackClick = {},

        )
    }
}