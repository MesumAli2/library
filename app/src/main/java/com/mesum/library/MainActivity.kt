package com.mesum.library

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.mesum.library.data.Item
import com.mesum.library.ui.theme.LibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryTheme {

                    BookList()
//                    Greeting("Android")

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibraryTheme {
        Greeting("Android")
    }
}

@Composable
fun BookList() {


    val viewModel : BookViewModel = viewModel()
    val books by viewModel.books.collectAsState()
    LazyColumn {
        Log.d("BooksSize", books.size.toString())

        items(books.size) { book ->
            BookItem(book = books.get(book))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BookItem(book: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = book.volumeInfo.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = book?.volumeInfo?.authors?.joinToString(", ") ?: "", fontSize = 14.sp)
            Text(text = book?.volumeInfo?.description ?: "", fontSize = 14.sp)
            SubcomposeAsyncImage(
                model = book.volumeInfo.imageLinks.thumbnail,
                contentDescription = null,
                loading = {
                    CircularProgressIndicator()
                }
            )

        }
    }
}