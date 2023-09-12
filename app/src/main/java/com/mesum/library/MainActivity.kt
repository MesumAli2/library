package com.mesum.library

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.mesum.library.data.network.BookViewModel
import com.mesum.library.data.network.Item
import com.mesum.library.ui.theme.LibraryTheme

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

    LazyColumn(modifier = modifier.padding(top = 8.dp),contentPadding = PaddingValues(0.dp)) {
        val filterdBooks =  books.filterNot { it?.volumeInfo?.imageLinks?.thumbnail.isNullOrEmpty() }
        filterdBooks.size.let {
            items(it) { book ->
                filterdBooks[book]?.let { it1 -> BookItem(book = it1) }

            }
        }

    }
}

@Composable
fun BookItem(book: Item, modifier: Modifier = Modifier.padding(bottom = 16.dp) ){
    Log.d("itemBook", book.toString())

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {

            SubcomposeAsyncImage(model =book?.volumeInfo?.imageLinks?.thumbnail ,
                contentDescription = null, loading = { CircularProgressIndicator()}, modifier = Modifier
                    .height(150.dp)
                    .width(150.dp))
            book.volumeInfo?.title?.let { Text(text = it, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp)) }

        }

}



@OptIn(ExperimentalMaterial3Api::class)
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
    val backgroundColor = Color(0xFF333333)
    val contentColor =  Color.White
    var searchRememberQuery by remember { mutableStateOf("") }




        Image(imageVector =Icons.Default.Search , contentDescription = null)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 4.dp, end = 4.dp),
                value = searchRememberQuery,
                onValueChange = {
                                searchRememberQuery =it
                    onSearchQueryChanged(it)
                                },
                placeholder = {
                    Text(text = "Search Books")
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },

                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
              keyboardActions = KeyboardActions (
                  onSearch = {onSearchQuerySubmit(searchRememberQuery)}
                      ),
                shape =
                RoundedCornerShape(10.dp),
            )






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
