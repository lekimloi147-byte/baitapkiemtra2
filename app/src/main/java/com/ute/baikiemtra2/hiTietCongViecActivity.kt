package com.ute.baikiemtra2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ChiTietCongViecActivity : AppCompatActivity() {
    private lateinit var tvHienThiTen: TextView
    private lateinit var tvHienThiMoTa: TextView
    private lateinit var btnSua: Button
    private lateinit var btnQuayLai: Button

    // Khai bao ActivityResultLauncher dung chuan Activity Result API
    private lateinit var chinhSuaLauncher: ActivityResultLauncher<Intent>

    // Bien luu tru thong tin hien tai
    private var tenHienTai: String = ""
    private var moTaHienTai: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        tvHienThiTen = findViewById(R.id.tvHienThiTen)
        tvHienThiMoTa = findViewById(R.id.tvHienThiMoTa)
        btnSua = findViewById(R.id.btnSua)
        btnQuayLai = findViewById(R.id.btnQuayLai)
        tenHienTai = intent.getStringExtra("EXTRA_TEN") ?: ""
        moTaHienTai = intent.getStringExtra("EXTRA_MOTA") ?: ""

        tvHienThiTen.text = "Ten cong viec: $tenHienTai"
        tvHienThiMoTa.text = "Mo ta: $moTaHienTai"
        chinhSuaLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                tenHienTai = data?.getStringExtra("EXTRA_TEN_MOI") ?: tenHienTai
                moTaHienTai = data?.getStringExtra("EXTRA_MOTA_MOI") ?: moTaHienTai
                tvHienThiTen.text = "Ten cong viec: $tenHienTai"
                tvHienThiMoTa.text = "Mo ta: $moTaHienTai"
            }
        }
        btnSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("EXTRA_TEN_CU", tenHienTai)
                putExtra("EXTRA_MOTA_CU", moTaHienTai)
            }
            chinhSuaLauncher.launch(intent)
        }

        btnQuayLai.setOnClickListener {
            finish()
        }
    }
}