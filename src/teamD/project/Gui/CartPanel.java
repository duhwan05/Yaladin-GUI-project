package teamD.project.Gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import teamD.project.Dao.Test.BuyBookDaoTest;
import teamD.project.Dao.Test.CartBookDaoTest;
import teamD.project.Dao.Test.NewBookDaoTest;
import teamD.project.Dao.Test.UsedBookDaoTest;
import teamD.project.Dto.BuyBookDto;
import teamD.project.Dto.CartBookDto;
import teamD.project.Dto.CustomerDto;
import teamD.project.Dto.NewBookDto;
import teamD.project.Dto.UsedBookDto;
import teamD.project.Main.MainService;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class CartPanel {
	private CustomerDto customer;
	private JFrame frame;
	private JTable cartBookTable;
	private DefaultTableModel cartBookTableModel;
	String[] cartscols = { "등록번호", "고객번호", "책이름", "저자", "가격", "수량" };
	List<NewBookDto> allNewBooks = new ArrayList<>();
	List<UsedBookDto> allUsedBooks = new ArrayList<>();
	UsedBookDaoTest usedbookdao = new UsedBookDaoTest();
	NewBookDaoTest newbookdao = new NewBookDaoTest();
	CustomerDto loginDto;
	UsedBookDto ub;

	public CartPanel(CustomerDto login) {
		this.customer = login;
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(232, 232, 255));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(400, 100, 1100, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// 구매할 상품을 담을 스크롤 패널- 최상위컨테이너
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 106, 1061, 539);
		frame.getContentPane().add(scrollPane);

		// 제목
		JLabel lblNewLabel = new JLabel("🛒🛒  장바구니 내역  🛒🛒");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 32));
		lblNewLabel.setBounds(191, 38, 713, 58);
		frame.getContentPane().add(lblNewLabel);

		// 홈으로가기
		JButton btnNewButton_1 = new JButton("홈으로가기");
		btnNewButton_1.setBounds(162, 671, 134, 49);
		frame.getContentPane().add(btnNewButton_1);

		// 장바구니 내역 삭제
		JButton btnNewButton_2 = new JButton("장바구니 삭제");
		btnNewButton_2.setBounds(465, 671, 134, 49);
		frame.getContentPane().add(btnNewButton_2);

		// 결제하기
		JButton btnNewButton_3 = new JButton("결제하기");
		btnNewButton_3.setBounds(738, 671, 134, 49);
		frame.getContentPane().add(btnNewButton_3);

		// 장바구니 조회
		cartBookTableModel = new DefaultTableModel(null, cartscols);
		cartBookTable = new JTable(cartBookTableModel);
		cartBookTable.setBounds(12, 67, 590, 415);
		scrollPane.setViewportView(cartBookTable);

		try {
			allNewBooks = newbookdao.selectAll();
			allUsedBooks = usedbookdao.selectAll();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		MainService.CartList(cartBookTableModel, allNewBooks, allUsedBooks, customer.getCust_no());

		// 홈으로가는 버튼 동작처리
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		CartBookDto dto = new CartBookDto();

		cartBookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

			}
		});

		// 장바구니 내역 삭제 동작처리
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartBookDaoTest dao = new CartBookDaoTest();

				int r = cartBookTable.getSelectedRow();
				
//	        	 dto.setNew_book_code((String)cartBookTable.getValueAt(r, 0));
				// 등록번호 가져온 값이 새책 중 코드가 같은 책이 있으면
				// 해당 새책의 newbookcode 저장
				for (NewBookDto nb : allNewBooks) {
					if (nb.getNew_book_code().equals((String) cartBookTable.getValueAt(r, 0)))
						dto.setNew_book_code((String) cartBookTable.getValueAt(r, 0));
				}

				// 등록번호 가져온 값이 중고책 중 코드가 같은 책이 있으면
				// 해당 중고책의 usedbookcode 저장
				for (UsedBookDto ub : allUsedBooks) {
					if (ub.getUsed_book_code().equals((String) cartBookTable.getValueAt(r, 0)))
						dto.setUsed_book_code((String) cartBookTable.getValueAt(r, 0));
				}

				
				
				dto.setCust_no(customer.getCust_no());
				dto.setBook_name(((String) cartBookTable.getValueAt(r, 2)));
				dto.setAuthor(((String) cartBookTable.getValueAt(r, 4)));
				dto.setCnt(Integer.parseInt((String) cartBookTable.getValueAt(r, 5)));
				
				// 실질적 삭제기능
				try {
					if (dto.getCnt() > 0)
						dao.minusCartCount(customer.getCust_no(), (String) cartBookTable.getValueAt(r, 0));
					else if (dto.getCnt() == 0) {
						if (dto.getNew_book_code() != null && dto.getUsed_book_code() == null)
							dao.delete(dto); // cartbook테이블에서 delete하는 메서드 실행
						if (dto.getNew_book_code() == null && dto.getUsed_book_code() != null)
							dao.delete2(dto);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		// 결제하기 버튼 동작 처리
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cartbook dao 생성
				CartBookDaoTest cartDao = new CartBookDaoTest();
				// buybook dao 생성
				BuyBookDaoTest buyDao = new BuyBookDaoTest();
				BuyBookDto buyDto;
//				List<BuyBookDto> buybooks = new ArrayList<>();
//                int count = 0;
//				count++;
//				cart.setCount(count);
				String buyno=null;
				try {
					// 로그인된 회원의 장바구니 목록 가져오기
					List<CartBookDto> cartlist = cartDao.selectDB(customer.getCust_no());
					List<NewBookDto> allNewBooks = newbookdao.selectAll();
					List<UsedBookDto> allUsedBooks = usedbookdao.selectAll();
					MainService.CartList(cartlist, allNewBooks, allUsedBooks);
					// 장바구니 목록 반복 돌리면서 하나씩 buy_book테이블에 insert
					
					 buyno = buyDao.insertBuyno(cartlist);
					 
					 
					 
					 
				//	 buybooks=buyDao.selectBuyBook(customer.getCust_no());
					 // (3) update문으로 status
					 
					 //	(5) 결제된 상품중에 status가 'y'인 중고책 번호들 뽑아서 used_book 테이블에서 delete
					 //	Ubdelete
					 
					 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
//				new BuyPanel(customer,buyno);
			}
		});
		Font font = new Font("맑은 고딕", Font.PLAIN, 400);
		frame.setVisible(true);
	}

	// cartTable.getSelectedRow();

	//

}
