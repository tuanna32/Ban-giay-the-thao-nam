/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import thicuoimon.duan1.view.KhachHang;
import thicuoimon.duan1.view.NhanVien;

/**
 *
 * @author honga
 */
public interface Duan1Controller {

    default void showJDialog(JDialog dialog) {
        dialog.setVisible(true);
    }
    default void KhachHangJDialog(JFrame jFrame) {
        this.showJDialog(new KhachHang(jFrame, true));
    }
        default void NhanVienJDialog(JFrame jFrame) {
        this.showJDialog(new NhanVien(jFrame, true));
    }
}
