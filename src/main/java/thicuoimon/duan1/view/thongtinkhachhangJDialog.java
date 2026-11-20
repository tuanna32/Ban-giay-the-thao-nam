/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package thicuoimon.duan1.view;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.prompt.PromptSupport;
import thicuoimon.duan1.dao.Impl.diachikhachhangDAOImpl;
import thicuoimon.duan1.dao.Impl.khachhangDAOImpl;
import thicuoimon.duan1.dao.diachikhachhangDAO;
import thicuoimon.duan1.dao.khachhangDAO;
import thicuoimon.duan1.entity.diachikhachhang;
import thicuoimon.duan1.entity.khachhang;
import thicuoimon.duan1.util.XDate;
import thicuoimon.duan1.util.XDialog;


/**
 *
 * @author Admin
 */
public class thongtinkhachhangJDialog extends javax.swing.JDialog {

    private DefaultTableModel mol = new DefaultTableModel();
    private int index = -1;
    List<khachhang> lstkhachhangs = List.of();
    khachhangDAO KHCN = new khachhangDAOImpl();
    List<diachikhachhang> lstdiachis = List.of();
    diachikhachhangDAO DCKH = new diachikhachhangDAOImpl();
    public khachhang setselectKH;
    private banhangJPanel panel;

    /**
     * Creates new form thongtinkhachhangJDialog
     */
    private khachhang khachhangDuocChon = null;

    public khachhang getKhachhangDuocChon() {
        return khachhangDuocChon;
    }

    public thongtinkhachhangJDialog(java.awt.Frame parent, boolean modal, banhangJPanel panel) {
        super(parent, modal);
        initComponents();
        this.panel = panel;
        this.setLocationRelativeTo(null);
        this.fillTable();
  PromptSupport.setPrompt("Tìm kiếm số điện thoại ...", txt_timkiem);
        txt_timkiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                timKiemTheoTen();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                timKiemTheoTen();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                timKiemTheoTen();
            }

            private void timKiemTheoTen() {
                String SDT = txt_timkiem.getText().trim();
                fillTableBySDT(SDT);
            }
        });
        // Gắn filter vào trường nhập số
        PlainDocument doc = (PlainDocument) txt_timkiem.getDocument();
        doc.setDocumentFilter(new NumericFilter());

    }

    public void fillTable() {
        mol = (DefaultTableModel) tbl_danhsachkhachhang.getModel();
        mol.setRowCount(0);
        lstkhachhangs = KHCN.findAll();
        int stt = 0;
        for (khachhang kh : lstkhachhangs) {
            stt++;
            diachikhachhang dc = DCKH.findById(kh.getIddiachi());  // Gọi 1 lần
           
            String sdt = kh.getSodienthoai();
            String email = kh.getEmail() != null ? kh.getEmail() : "";
            String diaChi;
            if (kh.getIddiachi() == null) {

                diaChi = "";
            } else {
                diaChi = dc.getDiachicuthe() + ", " + dc.getPhuongxa();
                if (dc.getQuanhuyen() != null && !dc.getQuanhuyen().isEmpty()) {
                    diaChi += ", " + dc.getQuanhuyen();
                }
                diaChi += ", " + dc.getTinhthanh();
            }
            Object[] toDataRow = new Object[]{
                stt,
                kh.getHovaten(),
                kh.getNgaysinh() != null ? XDate.format(kh.getNgaysinh(), XDate.PATTERN_SHORT_DAY) : "",
                sdt,
                email
            };
            mol.addRow(toDataRow);
        }
    }

    public void fillTableBySDT(String SDT) {
        mol = (DefaultTableModel) tbl_danhsachkhachhang.getModel();
        mol.setRowCount(0);

        lstkhachhangs = KHCN.timTheoSDT(SDT);
        int stt = 0;
        for (khachhang kh : lstkhachhangs) {
            stt++;
            String sdt = kh.getSodienthoai();
            String email = kh.getEmail() != null ? kh.getEmail() : "";

            Object[] toDataRow = new Object[]{
                stt,
                kh.getHovaten(),
                kh.getNgaysinh() != null ? XDate.format(kh.getNgaysinh(), XDate.PATTERN_SHORT_DAY) : "",
                sdt,
                email
            };
            mol.addRow(toDataRow);
        }
    }

    public void setform(khachhang entity) {
        txt_tenkhachhang.setText(entity.getHovaten());
        txt_sodienthoai.setText(entity.getSodienthoai());
        txt_ngaySinh.setText(entity.getNgaysinh() != null ? XDate.format(entity.getNgaysinh(), XDate.PATTERN_SHORT_DAY) : "");
        txt_email.setText(entity.getEmail());
    }

    public khachhang getform() {
//       if (txt_diachi.getText().trim().isEmpty()) {
//           XDialog.alert("Địa chỉ không được để trống");
//           return null;
//       }
//        if (txt_sodienthoai.getText().trim().isEmpty()) {
//            XDialog.alert("Số điện thoại không được để trống");
//            return null;
//        }
        if (txt_tenkhachhang.getText().trim().isEmpty()) {
            XDialog.alert("Tên khách hàng không được để trống");
            return null;

        }

        khachhang k = new khachhang();
        k.setHovaten(txt_tenkhachhang.getText().trim());
        k.setSodienthoai(txt_sodienthoai.getText().trim());
        k.setEmail(txt_email.getText().trim());
        String nsText = txt_ngaySinh.getText().trim();
        if (!nsText.isEmpty()) {
            k.setNgaysinh(XDate.parse(nsText, XDate.PATTERN_SHORT_DAY));
        }
        return k;
    }

    public void edit() {
        index = tbl_danhsachkhachhang.getSelectedRow();

        khachhang k = lstkhachhangs.get(index);
        if (k == null) {
            return;
        }
        this.setform(k);
        Tabs.setSelectedIndex(1);
    }

    public void create() {
        if (!XDialog.confirm("Bạn có muốn thêm không")) {
            return;
        }
        khachhang k = this.getform();
        if (k == null) {
            return;
        }
        if (txt_email.getText().trim().isEmpty() && txt_sodienthoai.getText().trim().isEmpty()) {
            XDialog.alert("Vui lòng nhập một trong hai ô nhập(Số điện thoại-Email)");
        }
//            biểu thức chính quy
        String regex = "^[\\p{L}\\s]+$";
        if (!k.getHovaten().matches(regex)) {
            XDialog.alert("Tên sản phẩm phải là chữ và không có kí tự đặc biệt");
            return;
        }
        String regexsdt = "^(0[3|5|7|8|9])[0-9]{8}$";
        if (!txt_sodienthoai.getText().trim().isEmpty() && !txt_sodienthoai.getText().trim().matches(regexsdt)) {
            XDialog.alert("Vui lòng nhập đúng định dạng SĐT");
            return;
        }
        String regexemail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!txt_email.getText().trim().isEmpty() && !txt_email.getText().trim().matches(regexemail)) {
            XDialog.alert("Vui lòng nhập đúng định dạng Email");
            return;
        }
        // check trùng
        for (khachhang kh : lstkhachhangs) {
            if (txt_sodienthoai.getText().trim().equalsIgnoreCase(kh.getSodienthoai())) {
                XDialog.alert("Trùng Số Điện Thoại");
                return;
            }
        }
        for (khachhang kh1 : lstkhachhangs) {
            if (txt_email.getText().trim().equalsIgnoreCase(kh1.getEmail())) {
                XDialog.alert("Trùng Email");
                return;
            }
        }
        khachhang h = this.getform();
        h.setMakhachhang(this.maKH());
        h.setNgaythem(XDate.toTimestamp(XDate.now()));
        KHCN.create1(h);
        XDialog.alert("Thêm Thành Công");
        Tabs.setSelectedIndex(0);
        this.fillTable();

    }

    public khachhang getformU() {
        if (txt_sodienthoai.getText().trim().isEmpty()) {
            XDialog.alert("Số điện thoại không được để trống");
            return null;
        }
        if (txt_tenkhachhang.getText().trim().isEmpty()) {
            XDialog.alert("Tên khách hàng không được để trống");
            return null;
        }
        index = tbl_danhsachkhachhang.getSelectedRow();
        if (index < 0) {
            XDialog.alert("Chưa chọn dòng nào");
            return null;
        }
        khachhang k = new khachhang();
        k.setHovaten(txt_tenkhachhang.getText().trim());
        String Sodienthoai = txt_sodienthoai.getText().trim();
        k.setSodienthoai(Sodienthoai);
        int idKH = lstkhachhangs.get(index).getIdkhachhang();
        k.setIdkhachhang(idKH);
        String email = txt_email.getText().trim();
        k.setEmail(email);
        String nsText = txt_ngaySinh.getText().trim();
        if (!nsText.isEmpty()) {
            k.setNgaysinh(XDate.parse(nsText, XDate.PATTERN_SHORT_DAY));
        }
        Date ngaysua = XDate.toTimestamp(XDate.now());
        k.setNgaysua(ngaysua);

        return k;
    }

    public void update() {
        if (!XDialog.confirm("Bạn có muốn Sửa không")) {
            return;
        }
        index = tbl_danhsachkhachhang.getSelectedRow();
        khachhang k = this.getformU();
        if (k == null) {
            return;
        }
        if (txt_email.getText().trim().isEmpty() && txt_sodienthoai.getText().trim().isEmpty()) {
            XDialog.alert("Vui lòng nhập một trong hai ô nhập(Số điện thoại-Email)");
        }
//            biểu thức chính quy
        String regex = "^[\\p{L}\\s]+$";
        if (!k.getHovaten().matches(regex)) {
            XDialog.alert("Tên sản phẩm phải là chữ và không có kí tự đặc biệt");
            return;
        }
        String regexsdt = "^(0[3|5|7|8|9])[0-9]{8}$";
        if (!txt_sodienthoai.getText().trim().isEmpty() && !txt_sodienthoai.getText().trim().matches(regexsdt)) {
            XDialog.alert("Vui lòng nhập đúng định dạng SĐT");
            return;
        }
        String regexemail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!txt_email.getText().trim().isEmpty() && !txt_email.getText().trim().matches(regexemail)) {
            XDialog.alert("Vui lòng nhập đúng định dạng Email");
            return;
        }
        if (index < 0 || index >= lstkhachhangs.size()) {
            XDialog.alert("Chưa chọn khách hàng để cập nhật");
            return;
        }
        int id = lstkhachhangs.get(index).getIdkhachhang();
        String sdt = txt_sodienthoai.getText().trim();
        for (khachhang kh : lstkhachhangs) {
            if (sdt.equalsIgnoreCase(kh.getSodienthoai().trim()) && kh.getIdkhachhang() != id) {
                XDialog.alert("Trùng số điện thoại");
                return;
            }
        }
        for (khachhang kh1 : lstkhachhangs) {
            if (txt_email.getText().trim().equalsIgnoreCase(kh1.getEmail()) && kh1.getIdkhachhang() != id) {
                XDialog.alert("Trùng Email");
                return;
            }
        }
        k.setNguoisua(null);
        KHCN.update1(k);
        XDialog.alert("Cập Nhật Thành Công");
        Tabs.setSelectedIndex(0);
        this.fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Tabs = new javax.swing.JTabbedPane();
        pnlthongtinkhachhang = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_timkiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhsachkhachhang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btn_chon = new javax.swing.JButton();
        pnlthietlapthongtinkhachhang = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_tenkhachhang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_sodienthoai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_capnhat = new javax.swing.JButton();
        btn_lammoi = new javax.swing.JButton();
        txt_ngaySinh = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(242, 242, 242)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tìm Kiếm");

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tbl_danhsachkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên Khách Hàng", "Ngày Sinh", "Số Điện Thoại", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_danhsachkhachhang.setRowHeight(40);
        tbl_danhsachkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_danhsachkhachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhsachkhachhang);
        if (tbl_danhsachkhachhang.getColumnModel().getColumnCount() > 0) {
            tbl_danhsachkhachhang.getColumnModel().getColumn(0).setMinWidth(50);
            tbl_danhsachkhachhang.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbl_danhsachkhachhang.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_danhsachkhachhang.getColumnModel().getColumn(1).setMinWidth(150);
            tbl_danhsachkhachhang.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbl_danhsachkhachhang.getColumnModel().getColumn(1).setMaxWidth(150);
            tbl_danhsachkhachhang.getColumnModel().getColumn(2).setMinWidth(100);
            tbl_danhsachkhachhang.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbl_danhsachkhachhang.getColumnModel().getColumn(2).setMaxWidth(100);
            tbl_danhsachkhachhang.getColumnModel().getColumn(3).setMinWidth(100);
            tbl_danhsachkhachhang.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbl_danhsachkhachhang.getColumnModel().getColumn(3).setMaxWidth(100);
            tbl_danhsachkhachhang.getColumnModel().getColumn(4).setMinWidth(150);
            tbl_danhsachkhachhang.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbl_danhsachkhachhang.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btn_chon.setBackground(new java.awt.Color(102, 102, 102));
        btn_chon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_chon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/click (1).png"))); // NOI18N
        btn_chon.setText("Chọn");
        btn_chon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(btn_chon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(btn_chon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlthongtinkhachhangLayout = new javax.swing.GroupLayout(pnlthongtinkhachhang);
        pnlthongtinkhachhang.setLayout(pnlthongtinkhachhangLayout);
        pnlthongtinkhachhangLayout.setHorizontalGroup(
            pnlthongtinkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlthongtinkhachhangLayout.setVerticalGroup(
            pnlthongtinkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Tabs.addTab("Danh Sách Khách Hàng", pnlthongtinkhachhang);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên Khách Hàng:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số Điện Thoại:");

        txt_sodienthoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sodienthoaiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Giới Tính:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Email:");

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });

        btn_them.setBackground(new java.awt.Color(255, 183, 77));
        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-removebg-preview.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_capnhat.setBackground(new java.awt.Color(255, 183, 77));
        btn_capnhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_capnhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tools.png"))); // NOI18N
        btn_capnhat.setText("Cập Nhật");
        btn_capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhatActionPerformed(evt);
            }
        });

        btn_lammoi.setBackground(new java.awt.Color(255, 183, 77));
        btn_lammoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/load-removebg-preview (1).png"))); // NOI18N
        btn_lammoi.setText("Làm Mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        txt_ngaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngaySinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tenkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                            .addComponent(txt_sodienthoai)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(96, 96, 96)
                        .addComponent(txt_email))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(71, 71, 71)
                        .addComponent(txt_ngaySinh)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btn_capnhat)
                .addGap(42, 42, 42)
                .addComponent(btn_lammoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_capnhat, btn_lammoi, btn_them});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_capnhat)
                    .addComponent(btn_lammoi))
                .addGap(33, 33, 33))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_capnhat, btn_lammoi, btn_them});

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 942, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlthietlapthongtinkhachhangLayout = new javax.swing.GroupLayout(pnlthietlapthongtinkhachhang);
        pnlthietlapthongtinkhachhang.setLayout(pnlthietlapthongtinkhachhangLayout);
        pnlthietlapthongtinkhachhangLayout.setHorizontalGroup(
            pnlthietlapthongtinkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlthietlapthongtinkhachhangLayout.setVerticalGroup(
            pnlthietlapthongtinkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Tabs.addTab("Thiết Lập Thông Tin Khách Hàng", pnlthietlapthongtinkhachhang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
        if (txt_timkiem.getText().trim().isEmpty()) {
            XDialog.alert("Mời Nhập số cần tìm");
            return;
        }
        String regexsdt = "^(0[3|5|7|8|9])[0-9]{8}$";
        if (!txt_timkiem.getText().trim().matches(regexsdt)) {
            XDialog.alert("Vui lòng nhập đúng định dạng SĐT");
            return;
        }
        String SDT = txt_timkiem.getText().trim();
        this.fillTableBySDT(SDT);
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void tbl_danhsachkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhsachkhachhangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            index = tbl_danhsachkhachhang.getSelectedRow();
            if (index < 0) {
                XDialog.alert("Bạn Chưa Chọn Dòng Nào");
                return;
            }
            setselectKH = lstkhachhangs.get(index);   // hoặc lấy từ model
            khachhangDuocChon = setselectKH; // gán vào biến để lấy về JFrame cha

        } else if (evt.getClickCount() == 2) {
            this.edit();
        }


    }//GEN-LAST:event_tbl_danhsachkhachhangMouseClicked

    private void btn_chonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonActionPerformed
        // TODO add your handling code here:
        if (setselectKH != null) {

            panel.setfromkh(setselectKH);
            this.dispose();
        } else {
            XDialog.alert("Vui lòng chọn Khách hàng");
        }

    }//GEN-LAST:event_btn_chonActionPerformed

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        txt_sodienthoai.setText("");
        txt_tenkhachhang.setText("");
        txt_ngaySinh.setText("");
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btn_capnhatActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        this.create();
    }//GEN-LAST:event_btn_themActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_sodienthoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sodienthoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sodienthoaiActionPerformed

    private void txt_ngaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngaySinhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                banhangJPanel fakePanel = new banhangJPanel(); // tạo panel giả
                thongtinkhachhangJDialog dialog = new thongtinkhachhangJDialog(new javax.swing.JFrame(), true, fakePanel);
                dialog.setVisible(true);
            }
        });
    }

    public String maDCKH() {
        String prefix = "DCKH-";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index1 = random.nextInt(chars.length());
            sb.append(chars.charAt(index1));
        }

        return prefix + sb.toString();
    }

    public String maKH() {
        String prefix = "KH-";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index1 = random.nextInt(chars.length());
            sb.append(chars.charAt(index1));
        }

        return prefix + sb.toString();
    }
    // Chỉ cho phép nhập số 0-9

    public static class NumericFilter extends DocumentFilter {

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string != null) {
                string = string.replaceAll("[^0-9]", ""); // Loại bỏ ký tự không phải số
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text != null) {
                text = text.replaceAll("[^0-9]", ""); // Loại bỏ ký tự không phải số
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_chon;
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlthietlapthongtinkhachhang;
    private javax.swing.JPanel pnlthongtinkhachhang;
    private javax.swing.JTable tbl_danhsachkhachhang;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_ngaySinh;
    private javax.swing.JTextField txt_sodienthoai;
    private javax.swing.JTextField txt_tenkhachhang;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
