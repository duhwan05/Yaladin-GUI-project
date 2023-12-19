package teamD.project.Main;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.table.DefaultTableModel;

import teamD.project.Dao.Test.BuyBookDaoTest;
import teamD.project.Dao.Test.CartBookDaoTest;
import teamD.project.Dao.Test.NewBookDaoTest;
import teamD.project.Dao.Test.UsedBookDaoTest;
import teamD.project.Dto.BuyBookDto;
import teamD.project.Dto.CartBookDto;
import teamD.project.Dto.NewBookDto;
import teamD.project.Dto.UsedBookDto;

public class MainService {
	UsedBookDto usedbook = new UsedBookDto();

	// 카테고리별 코드화 메소드
	public String bookCode(String category) {

		Random rd = new Random();
		int bookcode = rd.nextInt(99999 - 10000) + 1;
		String usedbookcode = null;
		switch (category) {
		case "자기계발":
			do {
				bookcode = rd.nextInt(99999 - 10000) + 1;
				usedbookcode = "A" + bookcode;
				usedbook.setUsed_book_code(usedbookcode);
			} while (!usedbook.getUsed_book_code().equals(usedbookcode));
			break;
		case "소설":
			do {
				bookcode = rd.nextInt(99999 - 10000) + 1;
				usedbookcode = "B" + bookcode;
				usedbook.setUsed_book_code(usedbookcode);
			} while (!usedbook.getUsed_book_code().equals(usedbookcode));
			break;
		case "인문학":
			do {
				bookcode = rd.nextInt(99999 - 10000) + 1;
				usedbookcode = "C" + bookcode;
				usedbook.setUsed_book_code(usedbookcode);
			} while (!usedbook.getUsed_book_code().equals(usedbookcode));
			break;
		case "19+":
			do {
				bookcode = rd.nextInt(99999 - 10000) + 1;
				usedbookcode = "D" + bookcode;
				usedbook.setUsed_book_code(usedbookcode);
			} while (!usedbook.getUsed_book_code().equals(usedbookcode));
			break;
		case "만화":
			do {
				bookcode = rd.nextInt(99999 - 10000) + 1;
				usedbookcode = "E" + bookcode;
				usedbook.setUsed_book_code(usedbookcode);
			} while (!usedbook.getUsed_book_code().equals(usedbookcode));
			break;

		}
		return usedbookcode;
	}

	// 새책 리스트로 불러오기 메소드

	public static void loadNewBooks(DefaultTableModel tablemodel) {
		NewBookDaoTest newdao = new NewBookDaoTest();
		List<NewBookDto> newbooks = null;
		try {
			newbooks = newdao.selectAll();
			for (NewBookDto book : newbooks) {
				String[] rowData = { book.getNew_book_code(), book.getBook_name(), book.getCategory(), book.getAuthor(),
						book.getPublisher(), String.valueOf(book.getPrice()), String.valueOf(book.getCnt()) };
				tablemodel.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//

	// UsedBook 메소드
	// 리스트로 불러오기
	public static void loadUsedBooks(DefaultTableModel table) {
		UsedBookDaoTest dao = new UsedBookDaoTest();
		List<UsedBookDto> books = null;
		try {
			books = dao.selectAll();
			for (UsedBookDto book : books) {
				if (book.getCnt() != 0) {
					String[] rowData = { book.getUsed_book_code(), String.valueOf(book.getCust_no()),
							book.getBook_name(), book.getCategory(), book.getAuthor(), book.getPublisher(),
							String.valueOf(book.getUsed_price()), String.valueOf(book.getCnt()),
							// String.valueOf(book.getTotal()),
							book.getSel_date().toString() };
					table.addRow(rowData);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	// UsedBook 검색메소드

	public static void Searchusedbook(String bookname, String Category, DefaultTableModel table) {
		UsedBookDaoTest dao = new UsedBookDaoTest();
		try {
			if (Category.equals("도서명")) {
				List<UsedBookDto> booknames = dao.selectBybookname(bookname);
				for (UsedBookDto book : booknames) {
					if (book.getCnt() != 0) {

						String[] rowData = { book.getUsed_book_code(), String.valueOf(book.getCust_no()),
								book.getBook_name(), book.getCategory(), book.getAuthor(), book.getPublisher(),
								String.valueOf(book.getUsed_price()), String.valueOf(book.getCnt()),
								// String.valueOf(book.getTotal()),
								book.getSel_date().toString() };

						table.addRow(rowData);
					}
				}
			} else if (Category.equals("카테고리")) {
				List<UsedBookDto> category = dao.selectByCategory(bookname);
				for (UsedBookDto book : category) {
					if (book.getCnt() != 0) {
						String[] rowData = { book.getUsed_book_code(), String.valueOf(book.getCust_no()),
								book.getBook_name(), book.getCategory(), book.getAuthor(), book.getPublisher(),
								String.valueOf(book.getUsed_price()), String.valueOf(book.getCnt()),
								// String.valueOf(book.getTotal()),
								book.getSel_date().toString() };
						table.addRow(rowData);
					}
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// 장바구니 리스트 메소드
	public static void CartList(DefaultTableModel cartsDB, List<NewBookDto> allNewBooks, List<UsedBookDto> allUsedBooks,
			int custno) {
		List<CartBookDto> cartDB = null;
		CartBookDaoTest dao = new CartBookDaoTest();
		try {
			cartDB = dao.selectDB(custno);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (cartsDB != null) {
			for (CartBookDto c : cartDB) {
				if (c.getStatus().equals("n")) {

					if (c.getNew_book_code() != null) {
						for (NewBookDto nb : allNewBooks) {
							if (nb.getNew_book_code().equals(c.getNew_book_code())) {
								c.setBook_name(nb.getBook_name());
								c.setPrice(nb.getPrice());
								c.setAuthor(nb.getAuthor());

								String[] RowData = { nb.getNew_book_code(), String.valueOf(c.getCust_no()),
										nb.getBook_name(), nb.getAuthor(), String.valueOf(nb.getPrice()),
										String.valueOf(c.getCnt()) };
								cartsDB.addRow(RowData);
							} // equal if
						} // for newbook
					} // if(newbookcode)

					else if (c.getUsed_book_code() != null) {
						for (UsedBookDto ub : allUsedBooks) {
							if (ub.getUsed_book_code().equals(c.getUsed_book_code())) {
								c.setBook_name(ub.getBook_name());
								c.setPrice(ub.getUsed_price());
								String[] RowData = { ub.getUsed_book_code(), String.valueOf(c.getCust_no()),
										ub.getBook_name(), ub.getAuthor(), String.valueOf(ub.getUsed_price()),
										String.valueOf(c.getCnt()) };

								cartsDB.addRow(RowData);

							}
						} // for(usedbook)

					} // else if end
				}
			} // 전체 for end

		} // 전체 if end
	}// 메서드 끝

	public static void CartList(List<CartBookDto> cartsDB, List<NewBookDto> allNewBooks,
			List<UsedBookDto> allUsedBooks) {
		if (cartsDB != null) {
			for (CartBookDto c : cartsDB) {
				if (c.getStatus().equals("n")) {
					if (c.getNew_book_code() != null) {
						for (NewBookDto nb : allNewBooks) {
							if (nb.getNew_book_code().equals(c.getNew_book_code())) {
								c.setBook_name(nb.getBook_name());
								c.setPrice(nb.getPrice());
								c.setAuthor(nb.getAuthor());
							}
						}
					} else if (c.getUsed_book_code() != null) {
						for (UsedBookDto ub : allUsedBooks) {
							if (ub.getUsed_book_code().equals(c.getUsed_book_code())) {
								c.setBook_name(ub.getBook_name());
								c.setPrice(ub.getUsed_price());
								c.setAuthor(ub.getAuthor());
							} // if
						} // for usedbook
					} // else if
				}

			} // foreach

		} // if
	}// method

	public static void loadBuyBooks(DefaultTableModel table, int custno) {
		BuyBookDaoTest dao = new BuyBookDaoTest();
		List<BuyBookDto> books = null;
		try {
			books = dao.selectBuyBook(custno);
			for (BuyBookDto book : books) {
				String[] rowData = { book.getBuy_no(), String.valueOf(book.getCart_no()),
						String.valueOf(book.getTotal()), String.valueOf(book.getOrder_date()) };
				table.addRow(rowData);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static String getbuyno() {
		Date date = new Date();
		String now = "yyyyMMddHHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(now);
		String buyno = sdf.format(date);
		return buyno;
	}

}
