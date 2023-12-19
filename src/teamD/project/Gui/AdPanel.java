package teamD.project.Gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import teamD.project.Dao.Test.CartBookDaoTest;
import teamD.project.Dao.Test.NewBookDaoTest;
import teamD.project.Dto.CartBookDto;
import teamD.project.Dto.CustomerDto;
import teamD.project.Dto.NewBookDto;

public class AdPanel {

	CustomerDto loginDto;
	private JPanel panel_Ad = new JPanel();

	public JPanel getPanel_Ad() {
		return panel_Ad;
	}

	public AdPanel(CustomerDto login) {
		this.loginDto = login;
		panel_Ad.setPreferredSize(new Dimension(490, 800));
		initialize();
	}

	private void initialize() {

		// dao 실행-> 책 리스트
		NewBookDaoTest dao = new NewBookDaoTest();
		List<NewBookDto> books = new ArrayList<>();
//    	CartBookDaoTest daoCart = new CartBookDaoTest();

//    	try {
//			List<CartBookDto> cartDB = daoCart.selectDB(loginDto.getCust_no());
//		} catch (SQLException e) {
//			System.out.println("장바구니 리스트 가져오기 실패 "+e.getMessage());
//		}// 장바구니 리스트 긁어오기

		try {
			books = dao.selectByCategory("19+");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		panel_Ad.setLayout(null);

		int x = 12;
		int y = 10;

		for (NewBookDto nbt : books) {
			JPanel subPanel = new JPanel();
			subPanel.setBounds(x, y, 600, 170);
			subPanel.setLayout(null);
			panel_Ad.add(subPanel);

//            NewBookDto book = new NewBookDto();
			JLabel ipv = new JLabel();
			ImageIcon icon = new ImageIcon("./image/19+/" + nbt.getNew_book_code() + ".jpg");
			Image image = icon.getImage().getScaledInstance(95, 100, Image.SCALE_SMOOTH);
			ipv.setIcon(new ImageIcon(image));
			ipv.setBounds(5, 5, 115, 140);
			subPanel.add(ipv);

			JLabel lblNewLabel_1 = new JLabel("<html> 책 제목 : " + nbt.getBook_name() + "<html><br> 저자 :"
					+ nbt.getAuthor() + "<html><br> 출판사 : " + nbt.getPublisher());
			lblNewLabel_1.setBounds(160, 10, 300, 140);
			subPanel.add(lblNewLabel_1);

			JButton btnNewButton = new JButton("장바구니");
			btnNewButton.setBounds(510, 55, 90, 23);
			subPanel.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CartBookDaoTest dao = new CartBookDaoTest();
					CartBookDto dto = new CartBookDto();
					dto = CartBookDto.builder().cust_no(loginDto.getCust_no()).new_book_code(nbt.getNew_book_code())
							.cnt(1).build();
					int count = 0;
					count++;
					dto.setCount(count);
					try {
						if (dao.selectCartBookByCode(loginDto.getCust_no(), dto.getNew_book_code()) != null) {
							dao.updateCartCount(loginDto.getCust_no(), dto.getNew_book_code());
						} else if(dao.selectCartBookByCode(loginDto.getCust_no(), dto.getNew_book_code()) == null) {
							dao.nbpro(dto);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			});

			y += 150;

		}
	}
}
