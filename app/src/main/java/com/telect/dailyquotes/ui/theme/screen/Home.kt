package com.telect.dailyquotes.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.telect.dailyquotes.utils.Utils
import com.telect.dailyquotes.vm.MainViewModel


@Composable
fun HomeScreen(viewModel: MainViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (viewModel.loading.collectAsState().value) {
            Log.i("TAG", "HomeScreen: loading")
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            if (viewModel.error.collectAsState().value.isNotEmpty()) {
                Log.i("TAG", "HomeScreen: error")
                val error = viewModel.error.collectAsState().value
                LaunchedEffect(viewModel) {
                    viewModel.snackBarHostState.showSnackbar(
                        message = error, actionLabel = "OK", duration = SnackbarDuration.Indefinite
                    )
                }
            }
            val data = viewModel.data.collectAsState().value
            Log.i("TAG", "HomeScreen: ${data.size}")
            if (data.isNotEmpty()) {
                LazyColumn {
                    items(data.size) {
                        val quote = data[it]
                        Log.i("TAG", "HomeScreen: $quote")
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(Utils.convertHtmlToString(quote.html))
                            if (it != data.size - 1) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                        }
                    }
                }
            } else {
                Log.i("TAG", "HomeScreen: empty")
                Text(
                    text = "No data found", modifier = Modifier.align(Alignment.Center)
                )
            }
        }


    }


}