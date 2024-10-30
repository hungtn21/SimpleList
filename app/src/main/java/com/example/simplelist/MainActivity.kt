package com.example.simplelist

import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplelist.ui.theme.SimpleListTheme
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNumber = findViewById<EditText>(R.id.edtNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val txtError = findViewById<TextView>(R.id.txtError)

        btnShow.setOnClickListener {
            txtError.visibility = TextView.GONE
            val inputText = edtNumber.text.toString()

            if (TextUtils.isEmpty(inputText)) {
                txtError.text = "Vui lòng nhập số nguyên dương"
                txtError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                txtError.text = "Số nhập vào không hợp lệ"
                txtError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val selectedId = radioGroup.checkedRadioButtonId
            val resultList = mutableListOf<Int>()

            when (selectedId) {
                R.id.radioEven -> {
                    for (i in 0..n step 2) {
                        resultList.add(i)
                    }
                }
                R.id.radioOdd -> {
                    for (i in 1..n step 2) {
                        resultList.add(i)
                    }
                }
                R.id.radioSquare -> {
                    for (i in 0..n) {
                        if (sqrt(i.toDouble()).toInt().toDouble() == sqrt(i.toDouble())) {
                            resultList.add(i)
                        }
                    }
                }
                else -> {
                    txtError.text = "Vui lòng chọn loại số"
                    txtError.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }
}
