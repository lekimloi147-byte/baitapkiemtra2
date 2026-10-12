package com.ute.baikiemtra2

import java.io.Serializable

class CongViec(
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false
) : Serializable {
    fun layThongTinChiTiet(): String {
        val trangThaiText = if (trangThai) "Da hoan thanh" else "Chua hoan thanh"
        return "Cong viec: $tenCongViec\nMo ta: $moTa\nTrang thai: $trangThaiText"
    }
}