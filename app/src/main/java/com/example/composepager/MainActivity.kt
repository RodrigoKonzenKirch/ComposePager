package com.example.composepager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepager.ui.theme.ComposePagerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val imageList = listOf(R.drawable.river00, R.drawable.river01, R.drawable.river02, R.drawable.river03)
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val pageCount by remember {
        mutableStateOf(imageList.size)
    }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) { page ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(imageList[pagerState.currentPage]),
                    modifier = Modifier.size(500.dp),
                    contentDescription = ""
                )
                Text(text = "Image $page")
            }
        }
        Row(Modifier.padding(16.dp)) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            ) {
                Text(text = "First")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pageCount)
                    }
                }
            ) {
                Text(text = "Last")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePagerTheme {
        AppScreen()
    }
}