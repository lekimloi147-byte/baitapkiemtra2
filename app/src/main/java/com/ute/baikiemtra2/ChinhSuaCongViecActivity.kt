package com.ute.baikiemtra2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaCongViecActivity : AppCompatActivity() {
    private lateinit var edtSuaTen: EditText
    private lateinit var edtSuaMoTa: EditText
    private lateinit var spinnerUuTien: Spinner
    private lateinit var chkTrangThai: CheckBox
    private lateinit var btnLuu: Button
    private lateinit var btnHuy: Button
    private lateinit var congViecNhan: CongViec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_cong_viec)

        edtSuaTen = findViewById(R.id.edtSuaTen)
        edtSuaMoTa = findViewById(R.id.edtSuaMoTa)
        spinnerUuTien = findViewById(R.id.spinnerUuTien)
        chkTrangThai = findViewById(R.id.chkTrangThai)
        btnLuu = findViewById(R.id.btnLuu)
        btnHuy = findViewById(R.id.btnHuy)

        // Cấu hình Spinner mức độ ưu tiên
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

        // Xử lý sự kiện nút Lưu: Validate -> Tăng biến đếm -> Trả kết quả
        btnLuu.setOnClickListener {
            val tenMoi = edtSuaTen.text.toString().trim()
            val moTaMoi = edtSuaMoTa.text.toString().trim()
            val uuTienMoi = spinnerUuTien.selectedItem.toString()

            // Kiểm tra không cho phép tên để trống hoặc toàn khoảng trắng
            if (tenMoi.isBlank()) {
                edtSuaTen.error = "Tên công việc không được để trống!"
                edtSuaTen.requestFocus()
                return@setOnClickListener
            }

            // Cập nhật thông tin mới
            congViecNhan.tenCongViec = tenMoi
            congViecNhan.moTa = moTaMoi
            congViecNhan.mucDoUuTien = uuTienMoi
            congViecNhan.trangThai = chkTrangThai.isChecked

            // Chỉ tăng số lần chỉnh sửa khi lưu thành công (Đề bổ sung 3)
            congViecNhan.soLanChinhSua += 1

            val resultIntent = Intent().apply {
                putExtra("EXTRA_CONG_VIEC_MOI", congViecNhan)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        // Xử lý sự kiện nút Hủy: Không lưu, không tăng biến đếm, quay lại màn hình chi tiết
        btnHuy.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}