package teamD.project.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import teamD.project.Dao.Test.BuyBookDaoTest;
import teamD.project.Dto.BuyBookDto;
import teamD.project.Dto.CustomerDto;
import teamD.project.Main.MainService;


public class BuyPanel {
	private String buyno;
	CustomerDto customer;
	private JFrame frame;
	private DefaultTableModel buyTableModel;
	String[] buyscols = { "주문 번호", "장바구니 번호", "총 금액", "구매날짜" };
	List<BuyBookDto> allNewBuys = new ArrayList<>();
//        private JPanel panel;
//        private JLabel lblNewLabel;
//        private JLabel lblNewLabel_1;
//        private JLabel lblNewLabel_2;
//        private JLabel lblNewLabel_3;
//        private JLabel lblNewLabel_4;
//        private JLabel lblNewLabel_5;
//        private JLabel lblNewLabel_6;
//        private JLabel lblNewLabel_7;
	BuyBookDaoTest dao = new BuyBookDaoTest();

	public BuyPanel(CustomerDto login,String buyno) {
		this.customer = login;
		this.buyno=buyno;
		initialize();
		frame.setVisible(true);
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(232, 232, 255));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(400, 100, 1100, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

//		try {
//			dto = dao.selectbuyno();
//		} catch (SQLException e) {
//			e.getMessage();
//			System.out.println("오류입니다.");
//		}
//	try {
//		allNewBuys = dao.selectBuyBook();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		List<BuyBookDto> dtos = null;
//		String bn = MainService.getbuyno();
		try {
			System.out.println(buyno);
			dtos = dao.selectbuyno(buyno);
		} catch (Exception e) {
			System.out.println("출력할 buyboodto객체 가져오는 과정에서 오류 발생"+e.getMessage());
		}
//		System.out.println(dto);
		// 제목
		JLabel lblNewLabel_8 = new JLabel("📕📕📕📕📕 결제가 완료되었습니다. 📕📕📕📕📕");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("휴먼매직체", Font.PLAIN, 32));
		lblNewLabel_8.setBounds(191, 38, 713, 58);
		frame.getContentPane().add(lblNewLabel_8);

		// 홈으로 가기
		JButton btnNewButton_1 = new JButton("홈으로 가기");
		btnNewButton_1.setBounds(478, 640, 134, 49);
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton_1);

		// 결제 완료 안내
		JLabel lblNewLabel = new JLabel("  주문 번호");
		lblNewLabel.setBounds(191, 130, 134, 58);
		lblNewLabel.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(dtos.get(0).getBuy_no());
		lblNewLabel_1.setBackground(UIManager.getColor("Button.highlight"));
		lblNewLabel_1.setBounds(326, 130, 578, 58);
		lblNewLabel_1.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("  장바구니 번호");
		lblNewLabel_2.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(191, 265, 134, 58);
		frame.getContentPane().add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(326, 265, 578, 58);
		lblNewLabel_3.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_3);
		
		String temp = "";
		for(BuyBookDto dto : dtos) {
			temp += String.valueOf(dto.getCart_no()) + ",";
		}
		lblNewLabel_3.setText(temp);
		
		JLabel lblNewLabel_5 = new JLabel("  총 금액");
		lblNewLabel_5.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(191, 399, 134, 58);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(326, 399, 578, 58);
		lblNewLabel_4.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_6 = new JLabel("  구매 날짜");
		lblNewLabel_6.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(191, 532, 134, 58);
		frame.getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel(String.valueOf(dtos.get(0).getOrder_date()));
		lblNewLabel_7.setBounds(326, 532, 578, 58);
		lblNewLabel_7.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_7);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(UIManager.getColor("Button.highlight"));
		desktopPane.setBounds(326, 130, 578, 58);
		frame.getContentPane().add(desktopPane);

		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(UIManager.getColor("Button.highlight"));
		desktopPane_1.setBounds(326, 265, 578, 58);
		frame.getContentPane().add(desktopPane_1);

		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBackground(UIManager.getColor("Button.highlight"));
		desktopPane_2.setBounds(326, 399, 578, 58);
		frame.getContentPane().add(desktopPane_2);

		JDesktopPane desktopPane_3 = new JDesktopPane();
		desktopPane_3.setBackground(UIManager.getColor("Button.highlight"));
		desktopPane_3.setBounds(326, 532, 572, 58);
		frame.getContentPane().add(desktopPane_3);

		buyTableModel = new DefaultTableModel(null, buyscols);

		// 홈으로 가는 버튼 동작 처리
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}