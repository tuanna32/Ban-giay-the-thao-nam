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
     public static nhanvien user = nhanvien.builder() 
    .idvaitro(1)
    .hovaten("Nguyễn Văn A")
    .manhanvien("NV01")
    .email("a@gmail.com")
    .matkhau("123")
    .trangthaian(true)
    .build(); // biến user này sẽ được thay thế sau khi đăng nhập
}
