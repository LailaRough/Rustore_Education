package com.example.rustore_education

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)

        findViewById<Button>(R.id.btnOpen).setOnClickListener {
            val text = editText.text.toString()
            if (text.isBlank()) {
                toast("Введите текст")
            } else {
                startActivity(Intent(this, SecondActivity::class.java).putExtra("text", text))
            }
        }

        findViewById<Button>(R.id.btnCall).setOnClickListener {
            val phoneName = editText.text.toString().trim()
            if (!phoneName.matches(Regex("^(\\+7|8)[0-9]{10}$"))) {
                toast("Введите номер в формате +79991234567 или 89991234567")
            } else {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneName")))
            }
        }

        findViewById<Button>(R.id.btnShare).setOnClickListener {
            val text = editText.text.toString()
            if (text.isBlank()) {
                toast("Введите текст")
            } else {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                }
                startActivity(Intent.createChooser(intent, "Поделиться через..."))
            }
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}