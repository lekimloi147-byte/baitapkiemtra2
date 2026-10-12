package com.ute.baikiemtra2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edtTenCongViec: EditText
    private lateinit var edtMoTa: EditText
    private lateinit var btnTaoCongViec: Button
    private lateinit var btnXemDanhSach: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtTenCongViec = findViewById(R.id.edtTenCongViec)
        edtMoTa = findViewById(R.id.edtMoTa)
        btnTaoCongViec = findViewById(R.id.btnTaoCongViec)
        btnXemDanhSach = findViewById(R.id.btnXemDanhSach)
        btnTaoCongViec.setOnClickListener {
            val ten = edtTenCongViec.text.toString().trim()
            val mota = edtMoTa.text.toString().trim()
            if (ten.isEmpty()) {
                edtTenCongViec.error = "Ten cong viec khong duoc de trong!"
                edtTenCongViec.requestFocus()
                return@setOnClickListener
            }
            val congViecMoi = CongViec(ten, mota, false)
            val intent = Intent(this, ChiTietCongViecActivity::class.java).apply {
                putExtra("EXTRA_CONG_VIEC", congViecMoi)
            }
            startActivity(intent)
        }
        btnXemDanhSach.setOnClickListener {
        }
    }
}