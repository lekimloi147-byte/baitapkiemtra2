package com.ute.baikiemtra2

import java.io.Serializable

class CongViec(
    var maCongViec: String = System.currentTimeMillis().toString(),
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false,
    var mucDoUuTien: String = "Trung bình",
    var soLanChinhSua: Int = 0 // Thuộc tính mới cho Đề bổ sung 3
) : Serializable {

    fun laDaHoanThanh(): Boolean {
        return trangThai
    }

    fun layThongTinChiTiet(): String {
        val trangThaiText = if (laDaHoanThanh()) "Đã hoàn thành" else "Chưa hoàn thành"
        return "Mã CV: $maCongViec\nCông việc: $tenCongViec\nMô tả: $moTa\nƯu tiên: $mucDoUuTien\nTrạng thái: $trangThaiText\nSố lần chỉnh sửa: $soLanChinhSua"
    }
}