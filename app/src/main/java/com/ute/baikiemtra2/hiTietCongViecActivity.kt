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

    private lateinit var chinhSuaLauncher: ActivityResultLauncher<Intent>
    private lateinit var congViecHienTai: CongViec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        tvHienThiTen = findViewById(R.id.tvHienThiTen)
        tvHienThiMoTa = findViewById(R.id.tvHienThiMoTa)
        btnSua = findViewById(R.id.btnSua)
        btnQuayLai = findViewById(R.id.btnQuayLai)
        congViecHienTai = intent.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec
            ?: CongViec("", "", false)

        hienThiThongTin()

        chinhSuaLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val cvMoi = result.data?.getSerializableExtra("EXTRA_CONG_VIEC_MOI") as? CongViec
                if (cvMoi != null) {
                    congViecHienTai = cvMoi
                    hienThiThongTin()
                }
            }
        }

        btnSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("EXTRA_CONG_VIEC_CU", congViecHienTai)
            }
            chinhSuaLauncher.launch(intent)
        }

        btnQuayLai.setOnClickListener { finish() }
    }

    private fun hienThiThongTin() {
        tvHienThiTen.text = "Ten cong viec: ${congViecHienTai.tenCongViec}"
        tvHienThiMoTa.text = congViecHienTai.layThongTinChiTiet()
    }
}