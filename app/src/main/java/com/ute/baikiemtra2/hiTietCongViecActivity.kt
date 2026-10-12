package com.ute.baikiemtra2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChiTietCongViecActivity : AppCompatActivity() {
    private lateinit var tvHienThiTen: TextView
    private lateinit var tvHienThiMoTa: TextView
    private lateinit var btnQuayLai: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        tvHienThiTen = findViewById(R.id.tvHienThiTen)
        tvHienThiMoTa = findViewById(R.id.tvHienThiMoTa)
        btnQuayLai = findViewById(R.id.btnQuayLai)

        // Nhan du lieu tu Intent
        val tenCV = intent.getStringExtra("EXTRA_TEN") ?: ""
        val moTaCV = intent.getStringExtra("EXTRA_MOTA") ?: ""

        tvHienThiTen.text = "Ten cong viec: $tenCV"
        tvHienThiMoTa.text = "Mo ta: $moTaCV"

        btnQuayLai.setOnClickListener { finish() }
    }
}