package com.ute.baikiemtra2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaCongViecActivity : AppCompatActivity() {
    private lateinit var edtSuaTen: EditText
    private lateinit var edtSuaMoTa: EditText
    private lateinit var spinnerUuTien: Spinner
    private lateinit var chkTrangThai: CheckBox
    private lateinit var btnLuu: Button
    private lateinit var congViecNhan: CongViec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_cong_viec)

        edtSuaTen = findViewById(R.id.edtSuaTen)
        edtSuaMoTa = findViewById(R.id.edtSuaMoTa)
        spinnerUuTien = findViewById(R.id.spinnerUuTien)
        chkTrangThai = findViewById(R.id.chkTrangThai)
        btnLuu = findViewById(R.id.btnLuu)
        val options = arrayOf("Thấp", "Trung bình", "Cao")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        spinnerUuTien.adapter = adapter

        congViecNhan = intent.getSerializableExtra("EXTRA_CONG_VIEC_CU") as? CongViec
            ?: CongViec(tenCongViec = "", moTa = "", trangThai = false)

        edtSuaTen.setText(congViecNhan.tenCongViec)
        edtSuaMoTa.setText(congViecNhan.moTa)
        chkTrangThai.isChecked = congViecNhan.trangThai
        val index = options.indexOf(congViecNhan.mucDoUuTien)
        if (index >= 0) spinnerUuTien.setSelection(index)

        btnLuu.setOnClickListener {
            val tenMoi = edtSuaTen.text.toString().trim()
            val moTaMoi = edtSuaMoTa.text.toString().trim()
            val uuTienMoi = spinnerUuTien.selectedItem.toString()
            if (tenMoi.isBlank()) {
                edtSuaTen.error = "Tên công việc không được để trống hoặc chỉ chứa khoảng trắng!"
                edtSuaTen.requestFocus()
                return@setOnClickListener
            }
            congViecNhan.tenCongViec = tenMoi
            congViecNhan.moTa = moTaMoi
            congViecNhan.mucDoUuTien = uuTienMoi
            congViecNhan.trangThai = chkTrangThai.isChecked
            val resultIntent = Intent().apply {
                putExtra("EXTRA_CONG_VIEC_MOI", congViecNhan)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}