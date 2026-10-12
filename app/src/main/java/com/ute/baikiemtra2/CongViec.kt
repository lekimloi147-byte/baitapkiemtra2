package com.ute.baikiemtra2

import java.io.Serializable

class CongViec(
    var maCongViec: String = System.currentTimeMillis().toString(), // Thuộc tính mã công việc mới
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false
) : Serializable {

    fun laDaHoanThanh(): Boolean {
        return trangThai
    }
    fun layThongTinChiTiet(): String {
        val trangThaiText = if (laDaHoanThanh()) "Da hoan thanh" else "Chua hoan thanh"
        return "Ma CV: $maCongViec\nCong viec: $tenCongViec\nMo ta: $moTa\nTrang thai: $trangThaiText"
    }
}