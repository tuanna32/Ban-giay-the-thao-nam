/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thicuoimon.duan1.dao;

import java.util.List;
import thicuoimon.duan1.entity.lichsuhoadon;

/**
 *
 * @author Asus
 */
public interface lichsuhoadonDAO extends CrudDAO<lichsuhoadon, Integer>{
    List<lichsuhoadon> ssngay(Integer id);
}
