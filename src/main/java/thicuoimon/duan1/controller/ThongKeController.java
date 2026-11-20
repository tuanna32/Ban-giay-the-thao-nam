/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.controller;


/**
 *
 * @author Admin
 */
import java.util.Date;
import java.util.List;
import thicuoimon.duan1.entity.ThongKe;

public interface ThongKeController extends CrudController<ThongKe>{
      List<ThongKe> getThongKeTheoNamVaThang(Date from, Date to);
}
