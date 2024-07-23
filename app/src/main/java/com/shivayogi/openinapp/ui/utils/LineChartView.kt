package com.shivayogi.openinapp.ui.dashboard.utils


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


fun hexToColor(hex: String): Int {
    return Color.parseColor(hex)
}

@Composable
fun LineChartView(
    data: List<Pair<String, Float>>, // List of month and value pairs
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                // Configure chart properties
                setBackgroundColor(Color.TRANSPARENT)
                setDrawGridBackground(false)
                setDrawBorders(false)
                setDrawMarkers(false)
                setTouchEnabled(true)

                isDragEnabled = true
                isScaleXEnabled = true
                isScaleYEnabled = false
                setPinchZoom(true)

                description.isEnabled = false
                // Create and set data
                val entries = data.mapIndexed { index, pair -> Entry(index.toFloat(), pair.second) }
                val dataSet = LineDataSet(entries, "Data")


                // Create gradient drawable programmatically
                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        hexToColor("#0E6FFF"),// Start color for gradient
                        hexToColor("#D0E0FF"),// Middle color for gradient
                        hexToColor("#FFFFFF")  // End color for gradient
                    )
                )

                // Set gradient drawable to the data set
                dataSet.setFillDrawable(gradientDrawable)
                dataSet.setDrawFilled(true)


                dataSet.color = hexToColor("#0E6FFF")
                dataSet.valueTextColor = Color.BLACK
                dataSet.setDrawFilled(true)
                dataSet.fillAlpha = 60
                dataSet.setDrawCircles(false) // Remove the points
                dataSet.setDrawValues(false)
                dataSet.lineWidth = 2f


                val lineData = LineData(dataSet)
                this.data = lineData

                // Set X-axis
                xAxis.apply {
                    valueFormatter = IndexAxisValueFormatter(data.map { it.first })
                    granularity = 1f
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(true)  // Enable X-axis grid lines
                    setDrawAxisLine(false)
                    gridColor = Color.GRAY // Customize grid line color
                    textColor = Color.BLACK // Customize text color
                }

                // Set Y-axis
                axisLeft.apply {
                    setDrawGridLines(true)  // Enable Y-axis grid lines
                    setDrawAxisLine(false)
                    setLabelCount(5, true)
                    axisMinimum = 0f
                    axisMaximum = 100f
                    granularity = 25f
                    gridColor = Color.GRAY // Customize grid line color
                    textColor = Color.BLACK // Customize text color
                }

                axisRight.isEnabled = false
            }
        },
        modifier = modifier
    )
}

@Composable
fun displayChart() {
    // Example data
    val data = listOf(
        Pair("Jan", 20f),
        Pair("Feb", 40f),
        Pair("Mar", 80f),
        Pair("Apr", 70f),
        Pair("May", 60f),
        Pair("Jun", 90f),
        Pair("Jul", 80f),
        Pair("Aug", 30f),
        Pair("Sep", 30f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LineChartView(data = data, modifier = Modifier.fillMaxSize())
    }
}



