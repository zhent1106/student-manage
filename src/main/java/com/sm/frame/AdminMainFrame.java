package com.sm.frame;
import com.sm.entity.Admin;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.service.CClassService;
import com.sm.service.DepartmentService;
import com.sm.ui.ImgPanel;
import com.sm.utils.AlioSSUtil;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class AdminMainFrame extends JFrame {
    private int departmentId=0;
    private ImgPanel rootPanel;
    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JPanel studentPanel;
    private JPanel rewardPunsishPanel;
    private JPanel classPanel;
    private JButton 新增院系Button;
    private JButton 刷新列表Button;
    private JPanel leftPanel;
    private JTextField departmentField;
    private JButton chooseButton;
    private JButton 新增Button;
    private JPanel contentPanel;
    private JLabel adminNameLabel;
    private JLabel timeLabel;
    private JLabel localpeopelLabel;
    private JLabel nameLabel;
    private JButton delButton;
    private Admin admin;
    private TimerTask clockTask;
    private Timer timer;
    private  String uploadFileUrl;
    private File file;
    private JPanel depPanel;
    private  JLabel logoLabel;
    private JPanel topPanel;
    private JScrollPane centerJScrollPanel;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private JComboBox<Department> depcomboBox;
    private JTextField classField;
    private JButton 新增班级Button;
    private Department department;
    private  JList jList;
    private JPopupMenu jPopupMenu;
    private DepartmentService departmentService;
    private CClassService cClassService;
    private JMenuItem item2;
    public AdminMainFrame (Admin admin) {
        departmentService = ServiceFactory.getDepartmentServiceInstance();
        cClassService=ServiceFactory.getCCLssServiceInstance();
        this.admin = admin;
        adminNameLabel.setText("欢迎您：" + admin.getAdminName() + "    " + "辅导员（班级）管理");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        clockTask = new TimerTask() {
            @Override
            public void run () {
                Date currentTime = new Date();
                String a = format.format(currentTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeLabel.setText(a);
            }
        };
        timer = new Timer();
        timer.schedule(clockTask, 0, 1000);
        setTitle("管理员主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootPanel.setFileName("45.jpg");
        rootPanel.repaint();
        setVisible(true);
        //调用显示所有院系方法
        showDepartments();
        //获取centerPanel,获取的是LayoutManager,向下转型CardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        院系管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                cardLayout.show(centerPanel, "Card1");
            }
        });
        班级管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                cardLayout.show(centerPanel, "Card4");
                showClassPanel();
            }
        });
        刷新列表Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                showDepartments();
            }
        });
        departmentField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                departmentField.setText("");
            }
        });
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建icon对象
//                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                    //将按钮隐藏
                    chooseButton.setVisible(false);
                }
            }
        });
        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                //上传文件到OSS并返回外链
                uploadFileUrl = AlioSSUtil.ossUpload(file);
                //创建Department对象，并设置相应属性
                Department department = new Department();
                department.setDepartmentName(departmentField.getText().trim());
                department.setLogo(uploadFileUrl);
                //调用service实现新增功能
                int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    //新增成功后，将侧边栏隐藏
                    leftPanel.setVisible(false);
                    //刷新界面数据
                    showDepartments();
                    //将图片预览标签隐藏
                    logoLabel.setVisible(false);
                    //将选择图片的按钮可见
                    chooseButton.setVisible(true);
                    //清空文本框
                    departmentField.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系失败");
                }
            }
        });
        新增院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                boolean flag = leftPanel.isVisible();
                if (flag == true) {
                    leftPanel.setVisible(false);
                } else {
                    leftPanel.setVisible(true);
                }
            }
        });
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建icon对象
                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                    //将按钮隐藏
                    chooseButton.setVisible(false);
                }
            }
        });
        classField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
               classField.setText("");
            }
        });
        depcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                //得到选中项的索引
                int index = depcomboBox.getSelectedIndex();
         Department department= (Department) depcomboBox.getItemAt(index);
          departmentId=department.getId();
            }
        });

        新增班级Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                CClass cClass = new CClass();
                cClass.setDepartmentId(departmentId);
                cClass.setClassName(classField.getText().trim());
                //调用service实现新增功能
                int n = ServiceFactory.getCCLssServiceInstance().addClass(cClass);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增班级成功");
                    //清空文本框
                    classField.setText("");
                    //刷新界面数据
                    showClassPanel();
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增班级失败");
                }
            }
        });
    }

    /**
     * 展示院系的数据
     */
    public void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        int len = departmentList.size();
        int row = len % 1 == 0 ? len / 1 : len /1 + 1;
        GridLayout gridLayout = new GridLayout(row, 1, 15, 15);
        contentPanel.setLayout(gridLayout);
        for (Department department : departmentList) {
            //给每个院系对象创建一个面板
            depPanel = new JPanel();
            depPanel.setPreferredSize(new Dimension(200, 200));
            //depPanel.setLayout(new GridLayout(2,1,0,5));
            //将院系名称设置给面板标题
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            //新建一个Label用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "' width=200 height=200/></html>");
            //图标标签加入院系面板
            delButton = new JButton("删除");
            delButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, "是否确定要删除这行数据");
                    // 判断用户是否点击
                    if (result == JOptionPane.OK_OPTION) {
                        int id = department.getId();
                        //删除这行记录
                        departmentService.deleteDepartment(id);
                        //从流式面板移除当前这个人的布局
                        contentPanel.remove(depPanel);
                        showDepartments();
                    }
                }
            });
            delButton.setBackground(new Color(180,42,1));
            delButton.setBounds(600,200,100,80);
            depPanel.add(logoLabel);
            depPanel.add(delButton);
            //院系面板加入主体内容面板
           contentPanel.add(depPanel);
            //刷新主体内容面板
            contentPanel.revalidate();
        }
    }

    /**
     * 展示班级的数据
     */
    public void showClassPanel() {
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showCombobox(departmentList);
        showTree(departmentList);
        showClasses(departmentList);
    }

    private void showCombobox(List<Department> departmentList) {
        for (Department department : departmentList
        ) {
            depcomboBox.addItem(department);
        }
    }

    private void showTree(List<Department> departmentList) {
        treePanel.removeAll();
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("南工院");
        for (Department department :
                departmentList) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            top.add(group);
            List<CClass> cClassList = ServiceFactory.getCCLssServiceInstance().selectByDepartmentId(department.getId());
            for (CClass cClass :
                    cClassList) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(cClass.getClassName());
                group.add(node);
            }
        }
        final JTree tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        treePanel.add(tree);
        treePanel.revalidate();
    }

    private void showClasses(List<Department> departmentList) {
        classContentPanel.removeAll();
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 20);
        for (Department department :
                departmentList) {
            ImgPanel depPanel = new ImgPanel();
            depPanel.setFileName("71.jpg");
            depPanel.repaint();
            depPanel.setPreferredSize(new Dimension(350, 500));
            depPanel.setLayout(null);
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setBounds(110, 60, 200, 30);
            List<CClass> cClassList = ServiceFactory.getCCLssServiceInstance().selectByDepartmentId(department.getId());
            DefaultListModel listModel = new DefaultListModel();
            for (CClass  cClass :
                    cClassList) {
                listModel.addElement(cClass);
            }
            JList<CClass> jList = new JList<>(listModel);
            JScrollPane listScrollPanel = new JScrollPane(jList);
            listScrollPanel.setBounds(50, 120, 250, 300);
            depPanel.add(depNameLabel);
            depPanel.add(listScrollPanel);
            classContentPanel.add(depPanel);
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem item1 = new JMenuItem("修改");
            JMenuItem item2 = new JMenuItem("删除");
            jPopupMenu.add(item1);
            jPopupMenu.add(item2);
            jList.add(jPopupMenu);
            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int index = jList.getSelectedIndex();
                    if (e.getButton() == 3) {
                        jPopupMenu.show(jList,e.getX(),e.getY());
                        CClass cClass = jList.getModel().getElementAt(index);
                        item2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choice = JOptionPane.showConfirmDialog(depPanel, "确定删除吗？");
                                if (choice == 0){
                                    ServiceFactory.getCCLssServiceInstance().deleteCClassById(cClass.getId());
                                    listModel.remove(index);
                                    showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                                }
                            }
                        });
                    }
                }
            });
        }
        //新增班级数据
        depcomboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //得到选中项的索引
                int index = depcomboBox.getSelectedIndex();
                //按照索引取出项，就是一个Department对象，然后取出其id备用
                departmentId = depcomboBox.getItemAt(index).getId();
            }
        });
    }
    public static void main(String[] args)throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("chengdi"));
    }
    }