package teamD.project.Gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import teamD.project.Dao.Test.CartBookDaoTest;
import teamD.project.Dao.Test.NewBookDaoTest;
import teamD.project.Dao.Test.UsedBookDaoTest;
import teamD.project.Dto.CartBookDto;
import teamD.project.Dto.CustomerDto;
import teamD.project.Dto.NewBookDto;
import teamD.project.Dto.UsedBookDto;
import teamD.project.Main.MainService;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AdminBookListGui {

   private JFrame frame;
   private JList<Object> list;
  // private JTextField textField;
   private JTable NewBookTable;
   private JTable UsedBookTable;
   private DefaultTableModel newBookTableModel;
   private DefaultTableModel uesdBookTableModel;
   String[] newcols = {"등록번호","책이름","카테고리","저자","출판사","가격","수량"};
   String[] usedcols = {"등록번호","고객번호","책이름","카테고리","저자","출판사","가격","수량","등록날짜"};
   CustomerDto loginDto;
   /**
    * Launch the application.
    */
  /* public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               BookListGui window = new BookListGui();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   
   //관리자 권한으로 실행시키기 위함
   public AdminBookListGui() {
	   initialize();
	   frame.setVisible(true);	   
   }
   
 
   public AdminBookListGui(CustomerDto login) {
      initialize();
      this.loginDto = login;
      frame.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 635, 624);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
      tabbedPane.setBounds(0, 0, 619, 600);
      frame.getContentPane().add(tabbedPane);
      
      
      //새책 패널
      JPanel NewBookPanel = new JPanel();
      tabbedPane.addTab("New Book", null, NewBookPanel, null);
      NewBookPanel.setLayout(null);
      
      JScrollPane newscrollPane = new JScrollPane();
      newscrollPane.setBounds(12, 67, 590, 415);
      NewBookPanel.add(newscrollPane);
      
      newBookTableModel = new DefaultTableModel(null,newcols);
      NewBookTable = new JTable(newBookTableModel);
      NewBookTable.setBounds(12, 67, 590, 415);
      newscrollPane.setViewportView(NewBookTable);
      MainService.loadNewBooks(newBookTableModel);
      
      
      
      JLabel newlblNewLabel = new JLabel("도서 검색창");
      newlblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
      newlblNewLabel.setBounds(23,22,84,30);
      NewBookPanel.add(newlblNewLabel);
      
      JTextField newtextField = new JTextField();
      newtextField.setBounds(120,23,271,30);
      NewBookPanel.add(newtextField);
      newtextField.setColumns(10);
      
      String[] newtemp = {"도서명","카테고리"};
      JComboBox<String> newcomboBox = new JComboBox<>(newtemp);
      newcomboBox.setBounds(403, 27, 75, 23);
      NewBookPanel.add(newcomboBox);
      
      
      //새책 장바구니버튼
      JButton j1 = new JButton();
      j1.setText("장바구니 조회");
      j1.setBounds(478, 507, 112, 31);
      NewBookPanel.add(j1);
      j1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 CartBookDto carts = new CartBookDto();
        	 CartBookDaoTest dao = new CartBookDaoTest();
        	 try {
				dao.selectDB(loginDto.getCust_no());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            new CartPanel(loginDto);
            
         }
      });
      
      
      JButton j2 = new JButton();
      j2.setText("장바구니 추가");
      j2.setBounds(348, 507, 112, 31);
      NewBookPanel.add(j2);
      j2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 CartBookDto carts = new CartBookDto();
        	 CartBookDaoTest dao = new CartBookDaoTest();
        	 
        	 try {
				dao.selectDB(loginDto.getCust_no());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            
         }
      });
      
      
      //새책 도서검색
      JButton newbtnSearchButton = new JButton("도서 검색");
      newbtnSearchButton.setBounds(490, 23, 100, 30);
      NewBookPanel.add(newbtnSearchButton);
      newbtnSearchButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			 String bookname = newtextField.getText(); // 검색 키워드 가져오기
	         String searchCategory = (String) newcomboBox.getSelectedItem(); // 검색 카테고리 가져오기
	            
	            newBookTableModel.setRowCount(0);
	            
	              // 데이터베이스에서 도서 검색
	            NewBookDaoTest newdao = new NewBookDaoTest();
	            try {
	                if (searchCategory.equals("도서명")) {
	                    List<NewBookDto> newbooknames = newdao.selectByBookName(bookname);
	                    for (NewBookDto book : newbooknames) {
	                    	  String[] rowData = {
	                    			   book.getNew_book_code(),
	                    			   book.getBook_name(),
	                    			   book.getCategory(),
	                    			   book.getAuthor(),
	                    			   book.getPublisher(),
	                    			   String.valueOf(book.getPrice()),
	                    			   String.valueOf(book.getCnt())};
	                    	   newBookTableModel.addRow(rowData);
	                    }
	                } else if (searchCategory.equals("카테고리")) {
	                    List<NewBookDto> newcategory = newdao.selectByCategory(bookname);
	                    for (NewBookDto book : newcategory) {
	                    	  String[] rowData = {
	                    			   book.getNew_book_code(),
	                    			   book.getBook_name(),
	                    			   book.getCategory(),
	                    			   book.getAuthor(),
	                    			   book.getPublisher(),
	                    			   String.valueOf(book.getPrice()),
	                    			   String.valueOf(book.getCnt())};
	                    	   newBookTableModel.addRow(rowData);
	                    }
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	        }
	      });

      
      
      // 중고책 패널 //
      JPanel UsedBook = new JPanel();
      tabbedPane.addTab("Used Book", null, UsedBook, null);
      UsedBook.setLayout(null);
      
      
      
      //여기부터 CustomerBookList usedbook에 옮기기
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 67, 590, 415);
      UsedBook.add(scrollPane);
      
      uesdBookTableModel = new DefaultTableModel(null,usedcols);
      UsedBookTable = new JTable(uesdBookTableModel);
      UsedBookTable.setBounds(12, 67, 590, 415);
      scrollPane.setViewportView(UsedBookTable);
      MainService.loadUsedBooks(uesdBookTableModel);     //메소드화 usedbook 불러오기
 
    //여기까지
      
      
      JLabel lblNewLabel = new JLabel("도서 검색창");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel.setBounds(23,22,84,30);
      UsedBook.add(lblNewLabel);
      
      JTextField usedtextField = new JTextField();
      usedtextField.setBounds(120,23,271,30);
      UsedBook.add(usedtextField);
      usedtextField.setColumns(10);
      
      String[] temp = {"도서명","카테고리"};
      JComboBox<String> comboBox = new JComboBox<>(temp);
      comboBox.setBounds(403, 27, 75, 23);
      UsedBook.add(comboBox);
      
      
      //중고책 도서검색
      JButton usedbtnSearchButton = new JButton("도서 검색");
      usedbtnSearchButton.addActionListener(new ActionListener() {
    	  
         public void actionPerformed(ActionEvent e) {
        	 
            String bookname = usedtextField.getText(); // 검색 키워드 가져오기
            String searchCategory = (String) comboBox.getSelectedItem(); // 검색 카테고리 가져오기
            
            uesdBookTableModel.setRowCount(0);
            
              // 데이터베이스에서 도서 검색
            MainService.Searchusedbook(bookname, searchCategory, uesdBookTableModel);
         }
      });
    	  

      usedbtnSearchButton.setBounds(490, 23, 100, 30);
      UsedBook.add(usedbtnSearchButton);
      
      //장바구니 추가 버튼
      JButton btnAddButton = new JButton("장바구니 추가");
      btnAddButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 new CartPanel(loginDto);
        	 
         }
      });
      btnAddButton.setBounds(350, 507, 116, 31);
      UsedBook.add(btnAddButton);
      
      //책 판매 등록
      JButton btnsellButton = new JButton("중고책 판매");
      btnsellButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

        	 new Salebook(loginDto);
        	
 
         	 
         	 
          }
       });
      btnsellButton.setBounds(478, 507, 112, 31);
      UsedBook.add(btnsellButton);
      


   }
}