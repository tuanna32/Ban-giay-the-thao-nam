/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.controller;

import thicuoimon.duan1.entity.chatlieu;
import thicuoimon.duan1.entity.kichco;
import thicuoimon.duan1.entity.kieudang;
import thicuoimon.duan1.entity.loaidegiay;
import thicuoimon.duan1.entity.loaigiay;
import thicuoimon.duan1.entity.mausac;
import thicuoimon.duan1.entity.sanpham;
import thicuoimon.duan1.entity.thuonghieu;
import thicuoimon.duan1.entity.xuatxu;

/**
 *
 * @author Asus
 */
public interface sanphamController extends CrudController<sanpham> {

    public void filltoTableSanPhamChiTiet();

    public void filltoTableSanPhamChiTietByIDSP(int id);

    void setFormSanPhamChiTiet();

    void getFormSanPhamChiTiet();

    void fillComboboxHangSPCT();

    void fillComboboxXuatXuSPCT();

    void fillComboboxLoaiGiaySPCT();

    

    void fillComboboxmauSacSPCT();

    void fillComboboxkichCoSPCT();

    void fillComboboxchatLieuSPCT();

    void fillComboboxkieuDangSPCT();

    void fillComboboxmaSPCT();

    public void fillTableTH();

    public void fillTableKC();

    public void fillTableKD();

    public void fillTableCL();

    public void fillTableMS();

    

    public void fillTableXX();

    public void fillTableGT();

    public void fillTableLDG();

    public void fillTableByTT();

    public void themTT();

    public void suatt();

    public void xoatt();

    public void resttt();

    public void AddThuocTinhThuongHieu();

    public void AddThuocTinhChatLieu();

    public void AddThuocTinhDayGiay();

    public void AddThuocTinhGioiTinh();

    public void AddThuocTinhKichCo();

    public void AddThuocTinhKieuDang();

    public void AddThuocTinhLoaiDeGiay();

    public void AddThuocTinhLoaiDay();

    public void AddThuocTinhMauSac();

    public void AddThuocTinhXuatXu();

    public thuonghieu getFormTH(boolean update, int id);

    public kichco getFormKC(boolean update, int id);

    public kieudang getFormKD(boolean update, int id);

    public loaidegiay getFormLDG(boolean update, int id);

    public loaigiay getFormLG(boolean update, int id);

    public chatlieu getFormCL(boolean update, int id);

    public mausac getFormMS(boolean update, int id);


    

    public xuatxu getFormXX(boolean update, int id);


    

    public void setFormTT();

    public void DownloadQR();
}
