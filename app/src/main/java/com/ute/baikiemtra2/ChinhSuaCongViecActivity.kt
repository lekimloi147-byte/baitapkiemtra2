package com.ute.baikiemtra2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaCongViecActivity : AppCompatActivity() {
    private lateinit var edtSuaTen: EditText
    private lateinit var edtSuaMoTa: EditText
    private lateinit var btnLuu: Button
    private lateinit var congViecNhan: CongViec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_cong_viec)

        edtSuaTen = findViewById(R.id.edtSuaTen)
        edtSuaMoTa = findViewById(R.id.edtSuaMoTa)
        btnLuu = findViewById(R.id.btnLuu)

        congViecNhan = intent.getSerializableExtra("EXTRA_CONG_VIEC_CU") as? CongViec
            ?: CongViec("", "", false)

        edtSuaTen.setText(congViecNhan.tenCongViec)
        edtSuaMoTa.setText(congViecNhan.moTa)

        btnLuu.setOnClickListener {
            congViecNhan.tenCongViec = edtSuaTen.text.toString().trim()
            congViecNhan.moTa = edtSuaMoTa.text.toString().trim()

            val resultIntent = Intent().apply {
                putExtra("EXTRA_CONG_VIEC_MOI", congViecNhan)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}