package com.mesum.library

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.mesum.library.data.Item
import com.mesum.library.ui.theme.LibraryTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryTheme {
                val viewModel : BookViewModel = viewModel()
                    Scaffold(topBar = {RoundedSearchTopBar(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        onSearchQueryChanged = {viewModel.searchBooks(it)},
                        hintText = "Search", query = viewModel.searchedQuerryField.collectAsState().value)}) {
                        BookList(Modifier.padding(it))

                    }



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
fun BookList(modifier: Modifier) {


    val viewModel : BookViewModel = viewModel()
    val books by viewModel.books.collectAsState()
    LazyColumn(modifier = modifier) {
        books?.size?.let {
            items(it) { book ->
                BookItem(book = books.get(book))
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BookItem(book: Item) {
    Log.d("itemBook", book.toString())
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(text = book.volumeInfo.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = book?.volumeInfo?.authors?.joinToString(", ") ?: "", fontSize = 14.sp)
            Text(maxLines = 3, text = book?.volumeInfo?.description ?: "", fontSize = 14.sp)
            SubcomposeAsyncImage(model =book?.volumeInfo?.imageLinks?.thumbnail ,
                contentDescription = null, loading = { CircularProgressIndicator()})

            AsyncImage(model =book?.volumeInfo?.imageLinks?.thumbnail.toString() , contentDescription = "")


            Log.d("imageLog", book?.volumeInfo?.imageLinks?.thumbnail.toString())


        }
    }
}



@Composable
fun RoundedSearchTopBar(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    hintText: String = "Search",
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchQuerySubmit: (String) -> Unit = {},
    query : String
) {
    // Define light and dark color variants
    val backgroundColor = if (!isSystemInDarkTheme()) Color(0xFFE0E0E0) else Color(0xFF333333)
    val contentColor = if (!isSystemInDarkTheme()) Color.Black else Color.White

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundColor, shape)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row {


        Image(imageVector =Icons.Default.Search , contentDescription = null)

        BasicTextField(
            value = query,
            onValueChange = {
                onSearchQueryChanged(it)
            },
            textStyle = TextStyle(fontSize = 16.sp, color = contentColor),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchQuerySubmit(query)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp)
                .background(Color.Transparent)
                .align(Alignment.CenterVertically)
                .onFocusChanged { focusState ->
                    // You can add any specific behavior when the focus state changes
                }
        )

        if (query.isEmpty()) {
            Text(
                text = hintText,
                color = contentColor.copy(alpha = 0.5f),
                fontSize = 16.sp
            )
        }
    }
    }
}

@Preview
@Composable
fun RoundedSearchTopBarPreview() {
    LibraryTheme() {
        RoundedSearchTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            hintText = "Search",
            query = ""
        )
    }
}
