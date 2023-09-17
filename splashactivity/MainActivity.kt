package com.example.splashactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splashactivity.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    var isBackButtonBlue by remember { mutableStateOf(true) }

    NavHost(navController, startDestination = "main") {
        composable("main") {
            Greeting(navController, isBackButtonBlue)
        }
        composable("blankPage") {
            SecondPageImages(navController, isBackButtonBlue)
        }
    }
}

@Composable
fun Greeting(navController: NavController, isBackButtonBlue: Boolean) {
    // Section for managing various states and variables
    var customText by remember { mutableStateOf("Your Event Name") }
    var customEventDate by remember { mutableStateOf("January 1st 2023") }

    // Fonts for custom text
    val arapeyRegularFont = FontFamily(Font(R.font.arapey_regular))
    val pinyonScriptFont = FontFamily(Font(R.font.pinyonscript_regular))
    val feltpenFont = FontFamily(Font(R.font.feltpen_))
    val nickainleyNormalFont = FontFamily(Font(R.font.nickainley_normal))
    val porcelainFont = FontFamily(Font(R.font.porcelain))

    // Dimensions for the landing image
    val landingImageWidth = 950.dp
    val landingImageHeight = 400.dp

    // Various flags to control UI elements' visibility and behavior
    var showBlueBackground by remember { mutableStateOf(false) }
    var landingImageColor by remember { mutableStateOf(Color.White) }
    var selectedImage by remember { mutableStateOf(R.drawable.landingscreenbg) }
    var useCustomFont by remember { mutableStateOf(false) }
    var isTitleIconClicked by remember { mutableStateOf(false) }
    var selectedBackgroundColor by remember { mutableStateOf(Color.White) }
    var selectedFont by remember { mutableStateOf(FontFamily(Font(R.font.pinyonscript_regular))) }

    var isDateIconBlue by remember { mutableStateOf(false) }
    var showDateFontImages by remember { mutableStateOf(isDateIconBlue) }
    var showTitleImages by remember { mutableStateOf(false) }

    // Lists of image resources for various UI elements
    val landingImageResources = listOf(
        R.drawable.landingscreenbgaura,
        R.drawable.landingscreenbgbali,
        R.drawable.landingscreenbgcapri,
        R.drawable.landingscreenbg,
        R.drawable.landingscreenbgslate
    )

    val backgroundResources = listOf(
        R.drawable.aura_landing_page,
        R.drawable.bali_landing_page,
        R.drawable.capri_landing_page,
        R.drawable.scandi_landing_page,
        R.drawable.slate_landing_page
    )

    val titleImageResources = listOf(
        R.drawable.font_arapey_your_event_name,
        R.drawable.font_linotype_feltpen_your_eventy_name,
        R.drawable.font_nickainley_your_event_name,
        R.drawable.font_pinyon_script_your_event_name,
        R.drawable.font_pony_club_your_event_name,
        R.drawable.font_porcelain_your_event_name
    )

    val dateFontImageResources = listOf(
        R.drawable.date_font_arapey,
        R.drawable.date_font_black_mango,
        R.drawable.date_font_linotype_feltpen,
        R.drawable.date_font_nickainley,
        R.drawable.date_font_pinyon_script,
        R.drawable.date_font_porcelain,
    )

    // The main UI layout begins here...
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(selectedBackgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = if (!showBlueBackground) R.drawable.background_image_icon else R.drawable.blue_background_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(vertical = 20.dp)
                        .clickable {
                            showBlueBackground = !showBlueBackground
                            landingImageColor = if (showBlueBackground) Color.White else Color.Transparent
                            // Remove this line to keep the landing page images visible
                            // showDateFontImages = false
                        }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(id = if (isTitleIconClicked) R.drawable.blue_title_icon else R.drawable.title_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(vertical = 12.dp)
                        .clickable {
                            isTitleIconClicked = !isTitleIconClicked

                            if (isTitleIconClicked) {
                                showBlueBackground = false
                                landingImageColor = Color.White
                                useCustomFont = false
                                showTitleImages = true

                            } else {
                                showTitleImages = false
                            }

                        }
                )

                Spacer(modifier = Modifier.height(12.dp))



                Image(
                    painter = painterResource(id = if (isDateIconBlue) R.drawable.blue_date_icon else R.drawable.date_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(vertical = 0.dp)
                        .clickable {
                            isDateIconBlue = !isDateIconBlue
                            // Toggle the showDateFontImages variable based on the current state of isDateIconBlue
                            showDateFontImages = !isDateIconBlue
                        }
                )
                if (showDateFontImages) {
                    // Render date font images
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 175.dp, end = 150.dp) // Adjusted padding here
                    ) {
                        dateFontImageResources.take(3).forEachIndexed { index, imageResource ->
                            Image(
                                painter = painterResource(id = imageResource),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(150.dp)
                                    .clickable {
                                        when (index) {
                                            0 -> selectedFont = arapeyRegularFont
                                            1 -> selectedFont = feltpenFont
                                            2 -> selectedFont = nickainleyNormalFont
                                        }
                                    }
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 175.dp, end = 150.dp) // Adjusted padding here
                    ) {
                        dateFontImageResources.drop(3).forEachIndexed { index, imageResource ->
                            Image(
                                painter = painterResource(id = imageResource),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(0.dp)
                                    .clickable {
                                        when (index + 3) {
                                            3 -> selectedFont = pinyonScriptFont
                                            4 -> selectedFont = porcelainFont
                                            5 -> selectedFont = porcelainFont
                                            6 -> selectedFont = porcelainFont // Added for date_icon
                                        }
                                    }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(id = selectedImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(landingImageWidth)
                    .height(landingImageHeight)
                    .background(landingImageColor)
            )
        }

        if (showBlueBackground) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 200.dp,
                        top = 560.dp,
                        end = 200.dp,
                        bottom = 0.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                backgroundResources.forEachIndexed { index, imageResource ->
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(end = 0.dp)
                            .clickable {
                                selectedBackgroundColor = Color.White
                                if (index < landingImageResources.size) {
                                    selectedImage = landingImageResources[index]
                                }
                            }
                    )
                }
            }
        }

        if (showTitleImages) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 200.dp, top = 500.dp, bottom = 0.dp, end = 200.dp)
                    .align(Alignment.CenterEnd),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    titleImageResources.take(3).forEachIndexed { index, imageResource ->
                        Image(
                            painter = painterResource(id = imageResource),
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp)
                                .clickable {
                                    when (index) {
                                        0 -> selectedFont = arapeyRegularFont
                                        1 -> selectedFont = feltpenFont
                                        2 -> selectedFont = nickainleyNormalFont
                                    }
                                }
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    titleImageResources.drop(3).forEachIndexed { index, imageResource ->
                        Image(
                            painter = painterResource(id = imageResource),
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp)
                                .padding(0.dp)
                                .clickable {
                                    when (index + 3) {
                                        3 -> selectedFont = pinyonScriptFont
                                        4 -> selectedFont = porcelainFont
                                        5 -> selectedFont = porcelainFont
                                    }
                                }
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 0.dp, top = 0.dp, bottom = 16.dp)
                .align(Alignment.CenterEnd),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isTitleIconClicked) {
                BasicTextField(
                    value = customText,
                    onValueChange = { customText = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
                    ),
                    modifier = Modifier
                        .padding(start = 150.dp)
                        .width(550.dp)
                        .height(56.dp),
                    textStyle = TextStyle(
                        fontSize = 50.sp,
                        color = Color.Black,
                        fontFamily = selectedFont
                    )
                )
            } else {
                Text(
                    text = customText,
                    modifier = Modifier
                        .padding(start = 150.dp)
                        .width(550.dp)
                        .height(56.dp),
                    style = TextStyle(
                        fontSize = 50.sp,
                        color = Color.Black,
                        fontFamily = selectedFont
                    )
                )
            }

            Spacer(
                modifier = Modifier
                    .width(280.dp)
                    .height(2.dp)
                    .background(Color.Black)
            )

            BasicTextField(
                value = customEventDate,
                onValueChange = { customEventDate = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
                ),
                modifier = Modifier
                    .padding(start = 90.dp)
                    .width(300.dp)
                    .height(50.dp),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 35.sp,
                    color = Color.Black,
                    fontFamily = arapeyRegularFont
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.start_event),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(start = 20.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    navController.navigate("blankPage")
                }
        )
    }
}

@Composable
fun SecondPageImages(navController: NavController, isBackButtonBlue: Boolean) {
    // Use the book image as the background
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.book_page_two),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )

        // Create a vertical layout to position the elements from top to bottom
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start // Align the elements to the left
        ) {
            // Add some empty space at the top to move the picture frame down
            Spacer(modifier = Modifier.height(120.dp)) // Adjust the height as needed

            // Picture frame with vertical padding
            Image(
                painter = painterResource(id = R.drawable.picture_frame),
                contentDescription = null,
                modifier = Modifier
                    .size(500.dp) // Adjust the size of the picture frame
                   
            )

            // Drawing area on the right
            Box(
                modifier = Modifier
                    .weight(1f) // Occupy the right part of the screen
                    .background(Color.Transparent) // Make it transparent for drawing
                    .clickable {
                        // Handle drawing actions here if needed
                    }
            )
        }

        // Add back button or other content on top of the book image
        Image(
            painter = painterResource(id = if (isBackButtonBlue) R.drawable.back_button else R.drawable.background_image_icon),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .clickable {
                    navController.navigate("main")
                }
        )
    }
}
