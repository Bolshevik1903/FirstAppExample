package pe.edu.upc.screennavigationapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.upc.screennavigationapp.R

data class MyMessage(val title:String, val body: String)

private val messages: List<MyMessage> = listOf(
    MyMessage("Hello 1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 4", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 5", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 6", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 7", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 8", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 9", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 10", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 11", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 12", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 13", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 14", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    MyMessage("Hello 15", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    )

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessageScreen(messages: List<MyMessage>, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Here is your messages")
        }
    }) {
        MyMessages(messages = messages)
    }
}

@Composable
fun MyText(text: String, color: Color, style: TextStyle, lines:Int = Int.MAX_VALUE ) {
    Text(text = text, color = color, style = style, maxLines = lines)
}

@Composable
fun Words(message: MyMessage) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    //var expanded = remember {mutableStateOf(false)}
    Column(
        modifier = Modifier.clickable {
            expanded = !expanded
        }
    ){
        MyText(text = message.title, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(3.dp))
        MyText(text = message.body, color = MaterialTheme.colors.onBackground, style = MaterialTheme.typography.subtitle2, if(expanded) Int.MAX_VALUE else 1)

    }
}

@Composable
fun DrawImage() {
    Image(
        painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "That's an image",
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
    )

}

@Composable
fun Components(message: MyMessage) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp)

    ) {
        DrawImage()
        Spacer(modifier = Modifier.width(4.dp))
        Words(message)
    }
}

@Composable
fun MyMessages(messages: List<MyMessage>){
    LazyColumn {
        items(messages) {message ->
            Components(message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyListScreenPreview() {
    //MessageScreen(messages = messages)
}
