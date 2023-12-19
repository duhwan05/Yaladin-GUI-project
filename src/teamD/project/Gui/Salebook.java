package teamD.project.Gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import teamD.project.Dao.Test.UsedBookDaoTest;
import teamD.project.Dto.CustomerDto;
import teamD.project.Dto.UsedBookDto;
import teamD.project.Main.MainService;

import javax.swing.JTextField;

public class Salebook {
    private JFrame frame;
    private JTextField bookNameJTextField;
    private JComboBox<String> categoryComboBox;
    private JTextField authorJTextField;
    private JTextField publisherJTextField;
    private JTextField priceJTextField;
    private JRadioButton agreementRadioButton;
    private CustomerDto customer;
    private JTextField JtextField;
    private int index;
    private static final String[] IMAGES= { 
    	".lamp.png/"	
    };
  /*  public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Salebook window = new Salebook(login);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
   


    
    public Salebook(CustomerDto login) {
       this.customer = login;
       initialize();

    }

    public void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(232, 232, 255));
        frame.setBackground(new Color(232, 232, 255));
        frame.setBounds(400,100,1100,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //화면 이미지
        JLabel lblImage = new JLabel();
        lblImage.setBackground(new Color(232, 232, 255));
        lblImage.setIcon(new ImageIcon("D:\\iclass0419\\java_workspace\\teamD.project\\image\\lamp.png"));
        lblImage.setHorizontalAlignment(SwingConstants.RIGHT);
        lblImage.setBounds(0, 0, 1100, 800
        		);
        frame.getContentPane().add(lblImage);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(232, 232, 255));
        panel.setBounds(0, 0, 1084, 761);
        frame.getContentPane().add(panel);
        
        JLabel lblNewLabelnnn = new JLabel(customer.getCust_name() + " 님 환영합니다. 판매할 서적을 등록해주세요.");
        lblNewLabelnnn.setFont(new Font("휴먼편지체", Font.PLAIN, 17));
        lblNewLabelnnn.setBounds(44, 106, 558, 33);
        panel.add(lblNewLabelnnn);
        
        JButton btnNewButton = new JButton("판매 등록");
        btnNewButton.setBounds(868, 692, 193, 43);
        panel.setLayout(null);
        panel.add(btnNewButton);

        JLabel lblNewLabel_1 = new JLabel("도서명");
        lblNewLabel_1.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(44, 164, 73, 43);
        panel.add(lblNewLabel_1);

        bookNameJTextField = new JTextField();
        bookNameJTextField.setBounds(189, 164, 413, 43);
        panel.add(bookNameJTextField);


        JLabel lblNewLabel_2 = new JLabel("카테고리");
        lblNewLabel_2.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(44, 251, 73, 43);
        panel.add(lblNewLabel_2);
        
        categoryComboBox = new JComboBox<>();
        categoryComboBox.setBounds(189, 251, 413, 43);
        panel.add(categoryComboBox);
        categoryComboBox.addItem("자기계발");
        categoryComboBox.addItem("소설");
        categoryComboBox.addItem("인문학");
        categoryComboBox.addItem("19+");
        categoryComboBox.addItem("만화");


        JLabel lblNewLabel_3 = new JLabel("저자");
        lblNewLabel_3.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(44, 338, 73, 46);
        panel.add(lblNewLabel_3);

        authorJTextField = new JTextField();
        authorJTextField.setBounds(189, 338, 413, 46);
        panel.add(authorJTextField);

        JLabel lblNewLabel_4 = new JLabel("출판사");
        lblNewLabel_4.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
        lblNewLabel_4.setBounds(44, 439, 73, 33);
        panel.add(lblNewLabel_4);
        
        publisherJTextField =new JTextField();
        publisherJTextField.setBounds(189, 429, 413, 43);
        panel.add(publisherJTextField);
        
        
       // textField.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("가격");
        lblNewLabel_8.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
        lblNewLabel_8.setBounds(44, 525, 73, 33);
        panel.add(lblNewLabel_8);
        
        priceJTextField = new JTextField();
        priceJTextField.setBounds(189, 515, 413, 43);
        panel.add(priceJTextField);

        agreementRadioButton = new JRadioButton("위 주의사항에 동의하십니까?");
        agreementRadioButton.setBackground(new Color(232, 232, 255));
        agreementRadioButton.setSelected(true);
        agreementRadioButton.setBounds(21, 712, 193, 23);
        panel.add(agreementRadioButton);

        JRadioButton agreementRadioButton_1 = new JRadioButton("아니요");
        agreementRadioButton_1.setBackground(new Color(232, 232, 255));
        agreementRadioButton_1.setEnabled(false);
        agreementRadioButton_1.setSelected(true);
        agreementRadioButton_1.setBounds(316, 712, 193, 23);
        panel.add(agreementRadioButton_1);

        JLabel lblNewLabel = new JLabel("📙 판매할 상품 등록 후 도서 정가 및 출간일을 비교해 주시기 바랍니다.");
        lblNewLabel.setBounds(21, 603, 387, 24);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_5 = new JLabel("📙 확인하신 정보와 다른 구판인 경우 매입이 되지 않을 수 있습니다.");
        lblNewLabel_5.setBounds(21, 637, 387, 24);
        panel.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("📙 판매 신청한 상품과 실제상품이 일치하지 않으면 매입불가 가능성 있습니다.");
        lblNewLabel_6.setBounds(21, 671, 431, 24);
        panel.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("중고 서적 판매", SwingConstants.CENTER);
        lblNewLabel_7.setBounds(231, 10, 643, 89);
        Font font = new Font("맑은 고딕", Font.PLAIN, 400);
        lblNewLabel_7.setForeground(new Color(0, 0, 0));
        lblNewLabel_7.setFont(new Font("함초롬바탕", Font.PLAIN, 60));
        panel.add(lblNewLabel_7);
        
        
        
        //등록버튼
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               UsedBookDto usedbook = new UsedBookDto();
               UsedBookDaoTest dao = new UsedBookDaoTest();
               MainService ms = new MainService();      // 코드화 메소드
               String bookcode;         
                         
               
                String bookName = bookNameJTextField.getText();
                usedbook.setBook_name(bookName);
                String category = (String) categoryComboBox.getSelectedItem();
                usedbook.setCategory(category);
                bookcode = ms.bookCode(category);      //코드화
                usedbook.setUsed_book_code(bookcode);      //북코드에 저장
                String author= authorJTextField.getText();
                usedbook.setAuthor(author);
                String publish = publisherJTextField.getText();
                usedbook.setPublisher(publish);
                int price = Integer.parseInt(priceJTextField.getText());
                usedbook.setUsed_price(price);
                int custno = customer.getCust_no();
                usedbook.setCust_no(custno);
                
                if (price > 0) {
                   int choice = JOptionPane.showConfirmDialog(null, "등록 하시겠습니까?", "판매 등록", JOptionPane.YES_NO_OPTION);
                   if (choice == JOptionPane.YES_OPTION) {
                   JOptionPane.showMessageDialog(null, "등록 완료되었습니다.");
                   frame.dispose();
                   } 
                    
                }//if
                else if(price==0) {
                  JOptionPane.showMessageDialog(null, "등록할 수 없습니다.");
                  
            
                }
                
                try {
               dao.addUsedBook(usedbook);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
                
               
            }
        });

 /*       categoryComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
               
                String selectedValue = (String) categoryComboBox.getSelectedItem();
            }
        });*/
        frame.setVisible(true);
    }
}