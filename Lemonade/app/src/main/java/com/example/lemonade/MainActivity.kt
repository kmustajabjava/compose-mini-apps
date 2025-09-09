package com.example.lemonade
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }
    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background,
        ) {
            when(currentStep){
                1 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.Lemon_tree,
                        drawableResourceId = R.drawable.lemon_tree,
                        contentDescriptionResourceId = R.string.Lemon_tree
                        , onImageClick = {
                            currentStep =2
                            squeezeCount =(2..4).random()
                            println(message = squeezeCount)
                        }
                    )
                }
                2 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.Lemon,
                        drawableResourceId = R.drawable.lemon_squeeze,
                        contentDescriptionResourceId = R.string.Lemon
                        , onImageClick = {
                            squeezeCount--
                            println(message = squeezeCount)
                            if(squeezeCount==0) {
                                currentStep =3
                            }
                        }
                    )
                }
                3 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.Glass_of_lemonade,
                        drawableResourceId = R.drawable.lemon_drink,
                        contentDescriptionResourceId = R.string.Glass_of_lemonade
                        , onImageClick = {
                                currentStep =4
                        }
                    )
                }
                4 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.Empty_glass,
                        drawableResourceId = R.drawable.lemon_restart,
                        contentDescriptionResourceId = R.string.Empty_glass
                        , onImageClick = {
                            currentStep =1
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun LemonTextAndImage(
    textLabelResourceId:Int,
    drawableResourceId:Int,
    contentDescriptionResourceId:Int,
    onImageClick:()->Unit,
    modifier: Modifier=Modifier
){
    Box(modifier=modifier){
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()){

            Button(onClick = onImageClick, shape = RoundedCornerShape(40.dp),
            colors= ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer))
            {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    modifier = Modifier
                        .width(128.dp)
                        .height(160.dp)
                        .padding(24.dp)

                )
            }

            Spacer(modifier=Modifier.height(32.dp))
            Text(text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}