package teamD.project.Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import javax.swing.JScrollPane;

public class CustomerBookList {

//	private List<CartBookDto> selectList = new ArrayList<>();
	private JFrame frame;
	private JTable UsedBookTable;
	private DefaultTableModel uesdBookTableModel;
	String[] usedcols = { "등록번호", "고객번호", "책이름", "카테고리", "저자", "출판사", "가격", "수량", "등록날짜" };
	String[] buyscols = { "주문 번호", "장바구니 번호", "총 금액", "구매날짜" };
	CustomerDto loginDto;
	UsedBookDto ub;
	private DefaultTableModel buyTableModel;

	// 사용자 권한으로 실행
	public CustomerBookList(CustomerDto login) {
		this.loginDto = login;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(232, 232, 255));
		frame.getContentPane().setBackground(new Color(232, 232, 255));
		frame.setBounds(400, 100, 1100, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(232, 232, 255));
		tabbedPane.setBounds(0, 0, 1084, 761);
		frame.getContentPane().add(tabbedPane);

		// 판매중인 새 책 도서 항목
		JPanel Bpanel = new JPanel();
		Bpanel.setBackground(new Color(232, 232, 255));
		tabbedPane.addTab("NewBook", null, Bpanel, null);
		Bpanel.setLayout(null);

		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBackground(new Color(255, 255, 255));
		tab.setBounds(0, 0, 1079, 732);
		Bpanel.add(tab);

		// 자기계발
		JPanel IPVpanel = new JPanel();
		IPVpanel.setBackground(new Color(232, 232, 255));
		tab.addTab("자기계발도서", null, IPVpanel, null);
		IPVpanel.setLayout(null);
		// 자기계발 스크롤
		JScrollPane ipvscrollPane = new JScrollPane();
		ipvscrollPane.setBounds(0, 0, 1074, 624);
		IPVpanel.add(ipvscrollPane);

		// 자기계발 추가
		IpvPanel IpvPanel = new IpvPanel(loginDto);
		ipvscrollPane.setViewportView(IpvPanel.getPanel_Ipv());

		// 자기계발 장바구니 버튼
		JButton btnNewButton = new JButton("장바구니 조회");
		btnNewButton.setBounds(883, 650, 150, 43);
		IPVpanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CartPanel(loginDto);
			}
		});

		// 소설 패널
		JPanel nvpanel = new JPanel();
		nvpanel.setBackground(new Color(232, 232, 255));
		tab.addTab("소설", null, nvpanel, null);
		nvpanel.setLayout(null);

		// 소설 스크롤 패널 생성
		JScrollPane nvscrollPane = new JScrollPane();
		nvscrollPane.setBounds(0, 0, 1074, 624);
		nvpanel.add(nvscrollPane);

		// 소설 추가
		NvPanel NvPanel = new NvPanel(loginDto);
		nvscrollPane.setViewportView(NvPanel.getPanel_Nv());

		// 소설 장바구니 버튼
		JButton btnNewButton2 = new JButton("장바구니 조회");
		btnNewButton2.setBounds(883, 650, 150, 43);
		nvpanel.add(btnNewButton2);
		btnNewButton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CartPanel(loginDto);
			}
		});

		// 인문학 패널
		JPanel hmpanel = new JPanel();
		hmpanel.setBackground(new Color(232, 232, 255));
		tab.addTab("인문학", null, hmpanel, null);
		hmpanel.setLayout(null);

		JScrollPane hmscrollPane = new JScrollPane();
		hmscrollPane.setBounds(0, 0, 1074, 624);
		hmpanel.add(hmscrollPane);

		HmPanel HmPanel = new HmPanel(loginDto);
		hmscrollPane.setViewportView(HmPanel.getPanel_Hm());

		// 인문학 장바구니 버튼
		JButton btnNewButton3 = new JButton("장바구니 조회");
		btnNewButton3.setBounds(883, 650, 150, 43);
		hmpanel.add(btnNewButton3);
		btnNewButton3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CartPanel(loginDto);
			}
		});

		// 만화 패널
		JPanel cmpanel = new JPanel();
		cmpanel.setBackground(new Color(232, 232, 255));
		tab.addTab("만화", null, cmpanel, null);
		cmpanel.setLayout(null);

		JScrollPane cmscrollPane = new JScrollPane();
		cmscrollPane.setBounds(0, 0, 1074, 624);
		cmpanel.add(cmscrollPane);

		CmPanel CmPanel = new CmPanel(loginDto);
		cmscrollPane.setViewportView(CmPanel.getPanel_Cm());

		// 만화 장바구니 버튼
		JButton btnNewButton4 = new JButton("장바구니 조회");
		btnNewButton4.setBounds(883, 650, 150, 43);
		cmpanel.add(btnNewButton4);
		btnNewButton4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CartPanel(loginDto);
			}
		});

		// 19+ 패널
		if (loginDto.getAge() >= 20) {
			JPanel panel19 = new JPanel();
			panel19.setBackground(new Color(232, 232, 255));
			tab.addTab("19+", null, panel19, null);
			panel19.setLayout(null);

			JScrollPane adscrollPane = new JScrollPane();
			adscrollPane.setBounds(0, 0, 1074, 624);
			panel19.add(adscrollPane);

			AdPanel adPanel = new AdPanel(loginDto);
			adscrollPane.setViewportView(adPanel.getPanel_Ad());

			// 19+ 장바구니 버튼
			JButton btnNewButton5 = new JButton("장바구니 조회");
			btnNewButton5.setBounds(883, 650, 150, 43);
			panel19.add(btnNewButton5);

			btnNewButton5.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					new CartPanel(loginDto);
				}
			});
		}

		// UsedBook 판넬 가져오기
		JPanel UsedBook = new JPanel();
		UsedBook.setBackground(new Color(232, 232, 255));
		tabbedPane.addTab("UsedBook", null, UsedBook, null);
		UsedBook.setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(12, 67, 1067, 574);
		UsedBook.add(scrollPane1);

		uesdBookTableModel = new DefaultTableModel(null, usedcols);
		UsedBookTable = new JTable(uesdBookTableModel);
		UsedBookTable.setBounds(12, 67, 590, 415);
		scrollPane1.setViewportView(UsedBookTable);
		MainService.loadUsedBooks(uesdBookTableModel);

		JLabel lblNewLabel = new JLabel("도서 검색창");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("휴먼편지체", Font.BOLD, 22));
		lblNewLabel.setBounds(23, 22, 128, 30);
		UsedBook.add(lblNewLabel);

		JTextField usedtextField = new JTextField();
		usedtextField.setBounds(177, 23, 514, 30);
		UsedBook.add(usedtextField);
		usedtextField.setColumns(10);

		String[] temp = { "도서명", "카테고리" };
		JComboBox<String> comboBox = new JComboBox<>(temp);
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(751, 22, 128, 30);
		UsedBook.add(comboBox);

		// 중고책 도서검색
		JButton usedbtnSearchButton = new JButton("도서 검색");
		usedbtnSearchButton.setBounds(0, 0, 128, 30);
		usedbtnSearchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String bookname = usedtextField.getText(); // 검색 키워드 가져오기
				String searchCategory = (String) comboBox.getSelectedItem(); // 검색 카테고리 가져오기

				uesdBookTableModel.setRowCount(0);

				// 데이터베이스에서 도서 검색
				MainService.Searchusedbook(bookname, searchCategory, uesdBookTableModel);
			}
		});

		usedbtnSearchButton.setBounds(922, 22, 128, 30);
		UsedBook.add(usedbtnSearchButton);

		CartBookDto dto = new CartBookDto();

		UsedBookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});

		// 장바구니 추가 버튼
		JButton btnAddButton = new JButton("장바구니 추가");
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartBookDaoTest dao = new CartBookDaoTest();

				int r = UsedBookTable.getSelectedRow();
				dto.setUsed_book_code((String) UsedBookTable.getValueAt(r, 0));
				dto.setCust_no(loginDto.getCust_no());
				dto.setBook_name(((String) UsedBookTable.getValueAt(r, 2)));
				dto.setAuthor(((String) UsedBookTable.getValueAt(r, 4)));
				dto.setCnt(1);

				try {
					// (String)UsedBookTable.getValueAt(r, 0) 값으로 장바구니 테이블에서 이미 존재하는지 조회할 dao를 만들어서
					if (dao.selectCartBookByCode(loginDto.getCust_no(),
							(String) UsedBookTable.getValueAt(r, 0)) != null) {
						dao.updateCartCount(loginDto.getCust_no(), (String) UsedBookTable.getValueAt(r, 0));
					} else if(dao.selectCartBookByCode(loginDto.getCust_no(),
							(String) UsedBookTable.getValueAt(r, 0)) == null)
						// 해당 book코드가 존재한다면 개수만 update 하는 dao를 만들어서 실행하기 (update 는 현재 개수+1 )
						dao.ubpro(dto); // 해당 book코드가 존재하지 않을 때 실행
				} catch (SQLException se) {
					JOptionPane.showMessageDialog(frame, se.getMessage(), "실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddButton.setBounds(465, 662, 149, 47);
		UsedBook.add(btnAddButton);

		// 장바구니 조회 버튼
		JButton checkAddButton = new JButton("장바구니 조회");
		checkAddButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CartPanel(loginDto);
			}
		});
		checkAddButton.setBounds(162, 662, 149, 47);
		UsedBook.add(checkAddButton);

		// 책 판매 등록
		JButton btnsellButton = new JButton("중고책 판매");
		btnsellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new Salebook(loginDto);

			}
		});
		btnsellButton.setBounds(738, 662, 149, 47);
		UsedBook.add(btnsellButton);

		// 중고책 끝

		// ------------------------------- 구매 내역 ----------------------------------

		// 구매 내역 패널
		JPanel Buy = new JPanel();
		Buy.setForeground(new Color(0, 0, 0));
		Buy.setBackground(new Color(232, 232, 255));
		tabbedPane.addTab("  Buy ", null, Buy, null);
		Buy.setLayout(null);

		// 구매내역 조회 버튼
		JButton Buyselect = new JButton("조회하기");
		Buyselect.setBounds(500, 660, 130, 40);
		Buy.add(Buyselect);
		Buyselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyTableModel.setRowCount(0);
				MainService.loadBuyBooks(buyTableModel, loginDto.getCust_no());
			}
		});

		// 스크롤 패널
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 135, 979, 477);
		Buy.add(scrollPane);

		// 스크롤 패널안에 테이블

//	         JTable BuyTable = new JTable(new DefaultTableModel(null,buyscols));         
		buyTableModel = new DefaultTableModel(null, buyscols);
		JTable BuyTable;
		BuyTable = new JTable(buyTableModel);
		BuyTable.setBounds(12, 67, 590, 415);
		scrollPane.setViewportView(BuyTable);
		MainService.loadBuyBooks(buyTableModel, loginDto.getCust_no());

		UsedBookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});

		// 텍스트

		JLabel lblNewLabel_1 = new JLabel("구매 내역 확인하기");
		lblNewLabel_1.setBounds(215, 20, 632, 73);
		lblNewLabel_1.setForeground(new Color(64, 64, 64));
		lblNewLabel_1.setFont(new Font("휴먼편지체", Font.ITALIC, 62));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		Buy.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(loginDto.getCust_name() + "님의 구매내역 입니다.");
		lblNewLabel_2.setBounds(47, 90, 558, 33);
		lblNewLabel_2.setFont(new Font("휴먼편지체", Font.PLAIN, 17));
		Buy.add(lblNewLabel_2);

		// 패널 아래 추가구문
		JLabel lblNewLabel_3 = new JLabel("주식회사 얄라얄라얄라딘\r\n");
		lblNewLabel_3.setFont(new Font("휴먼매직체", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(0, 637, 219, 25);
		Buy.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("서울특별시 강남구 역삼동(어딘가의 13층)");
		lblNewLabel_4.setBounds(12, 663, 245, 15);
		Buy.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("사업자 등록번호: 010-4177-5428");
		lblNewLabel_5.setBounds(12, 677, 229, 25);
		Buy.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("대표이사: 지니");
		lblNewLabel_6.setBounds(12, 707, 219, 15);
		Buy.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("고객센터");
		lblNewLabel_7.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_7.setBounds(822, 660, 130, 21);
		Buy.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("서울특별시 강남구 역삼동(어딘가의 13층)");
		lblNewLabel_8.setBounds(822, 682, 245, 15);
		Buy.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Tel: 010-4177-5428");
		lblNewLabel_9.setBounds(822, 707, 203, 15);
		Buy.add(lblNewLabel_9);

//	         BuyBookDaoTest dao = new BuyBookDaoTest();
//	         try {
//				List<BuyBookDto> list = dao.selectBuyBook(loginDto.getCust_no());
//				System.out.println(list);
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}

	}

}
