package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()//function call here
                }
            }
        }
    }
}




//composable function
@Composable
fun UnitConverter(){
    //state in compose that update value
    var iExpande by remember {mutableStateOf(false)}
    var oExpande by remember {mutableStateOf(false)}
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var inputvalue by remember {mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    val conversin = remember{ mutableStateOf(1.00) }
    val oconversin = remember{ mutableStateOf(1.00) }

     // function that perform unit conversion
    fun conversion(){
         // ?: means if user enter invalid value then it will store 0.00 instead
        val inputvalueDouble = inputvalue.toDoubleOrNull() ?: 0.00
         //formula to convert unit
        val result = (inputvalueDouble*conversin.value*100.00 /oconversin.value)/100
        outputValue = result.toString()
    }


    //User inferface
    Column(
        //to allign items in center of a screen
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //composable in column
        Text(text = "Unit Converter" ,
            style = TextStyle(fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.15.sp,
            color = Color(0xFF64B5F6)
            // You can add more style attributes here (e.g., fontFamily, color, etc.)
        )
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = inputvalue ,
            onValueChange = {
            inputvalue = it},
            label = { Text(text = "Enter Value")}
        )

        Spacer(modifier = Modifier.height(25.dp))
        Row {
            //inputBox
            Box{
                //input Button
                Button(onClick = {iExpande = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                }
                //Dropdown
                DropdownMenu(expanded = iExpande, onDismissRequest = { iExpande = false})
                {
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            inputUnit = "Meter"
                            iExpande = false
                            conversin.value = 1.00
                            conversion()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            inputUnit = "Centimeter"
                            iExpande = false
                            conversin.value = 0.01
                            conversion()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Mimlimeter") },
                        onClick = {
                            inputUnit = "Mimlimeter"
                            iExpande = false
                            conversin.value = 0.001
                            conversion()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            inputUnit = "Feet"
                            iExpande = false
                            conversin.value = 0.3048
                            conversion()
                        })
                }
             }

            //outputBOx
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = {oExpande = true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
                }
                DropdownMenu(expanded = oExpande, onDismissRequest = { oExpande=false })
                {
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            outputUnit = "Meter"
                            oExpande = false
                            oconversin.value = 1.00
                            conversion()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Centieter") },
                        onClick = {
                            outputUnit = "Centimeter"
                            oExpande = false
                            oconversin.value = 0.01
                            conversion()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Mimlimeter") },
                        onClick = {
                            outputUnit = "Mimlimeter"
                            oExpande = false
                            oconversin.value = 0.001
                            conversion()/*TODO*/
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            outputUnit = "Feet"
                            oExpande = false
                            oconversin.value = 0.3048
                            conversion()
                        })
                }
            }

        }

        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Result $outputValue $outputUnit",
            style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.15.sp,
            color = Color.Black
            // You can add more style attributes here (e.g., fontFamily, color, etc.)
        ))//output
    }
}



//preview
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}