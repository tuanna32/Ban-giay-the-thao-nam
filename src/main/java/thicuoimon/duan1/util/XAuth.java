/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimon.duan1.util;

import thicuoimon.duan1.entity.nhanvien;

/**
 *
 * @author Asus
 */
public class XAuth {
     public static nhanvien user;

    static {
        user = new nhanvien();
        user.setIdvaitro(1);
        user.setManhanvien("NV01");
        user.setHovaten("Nguyễn Văn A");
        user.setEmail("a@gmail.com");
        user.setMatkhau("123");
        user.setTrangthaian(true);
    }
}
