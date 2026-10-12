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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_cong_viec)

        edtSuaTen = findViewById(R.id.edtSuaTen)
        edtSuaMoTa = findViewById(R.id.edtSuaMoTa)
        btnLuu = findViewById(R.id.btnLuu)
        val tenCu = intent.getStringExtra("EXTRA_TEN_CU") ?: ""
        val moTaCu = intent.getStringExtra("EXTRA_MOTA_CU") ?: ""

        edtSuaTen.setText(tenCu)
        edtSuaMoTa.setText(moTaCu)
        btnLuu.setOnClickListener {
            val tenMoi = edtSuaTen.text.toString().trim()
            val moTaMoi = edtSuaMoTa.text.toString().trim()

            val resultIntent = Intent().apply {
                putExtra("EXTRA_TEN_MOI", tenMoi)
                putExtra("EXTRA_MOTA_MOI", moTaMoi)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}