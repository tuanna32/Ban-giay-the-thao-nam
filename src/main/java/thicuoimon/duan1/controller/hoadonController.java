/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.controller;

import java.util.List;
import thicuoimon.duan1.entity.hoadon;
import thicuoimon.duan1.entity.hoadonchitiet;
import thicuoimon.duan1.entity.lichsuhoadon;
/**
 *
 * @author Asus
 */
public interface hoadonController extends CrudController<hoadon>{
    void selectTrangthai();

    void selectHinhthuc();

    void filltoall(List<hoadon> a);

    void timkiem();

    void locgia();

    void xuathoadon();

    void inhoadon();

    void taohoadon();

    int quetqr();

    void filltoallhdct(List<hoadonchitiet> b);

    void getdulieu();

    void filltoalllsdh(List<lichsuhoadon> c);
    void danhsachhoadondaxoa();
    void selectngay();
    void fillngay();
    List<hoadonchitiet> bolochdct();
List<hoadon>boloctthdvshttt();
    
}
