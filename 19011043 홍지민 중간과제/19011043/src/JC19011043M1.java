import java.awt.event.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JC19011043M1 extends JFrame implements ActionListener {
	JButton btnOk, btnReset, btnOr, btnCu, btn4, btn5, btn6, btni;
	JTextArea txtResult, txtStatus;
	JPanel pn1;

	static Connection con;
	Statement stmt;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public JC19011043M1() {
		super("19011043 홍지민");
		layInit();
		conDB(); // JDBC
		setVisible(true);
		setBounds(200, 200, 800, 800); // 가로위치,세로위치,가로길이,세로길이
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void layInit() {
		btnOk = new JButton("Button 1");
		btnOr = new JButton("Button 2");
		btnCu = new JButton("Button 3");
		btn4 = new JButton("Button 4");
		btn5 = new JButton("Button 5");
		btn6 = new JButton("Button 6");
		btni = new JButton("Insert");
		btnReset = new JButton("Reset");

		txtResult = new JTextArea();
		txtStatus = new JTextArea(5, 30);

		pn1 = new JPanel();
		pn1.add(btni);
		pn1.add(btnOk);
		pn1.add(btnOr);
		pn1.add(btnCu);
		pn1.add(btn4);
		pn1.add(btn5);
		pn1.add(btn6);
		pn1.add(btnReset);

		txtResult.setEditable(false);
		txtStatus.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResult);
		JScrollPane scrollPane2 = new JScrollPane(txtStatus);

		add("North", pn1);
		add("Center", scrollPane);
		add("South", scrollPane2);

		btni.addActionListener(this);
		btnOk.addActionListener(this);
		btnOr.addActionListener(this);
		btnCu.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		btnReset.addActionListener(this);
	}

	public void conDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("드라이버 로드 성공");
			txtStatus.append("드라이버 로드 성공...\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try { /* 데이터베이스를 연결하는 과정 */
			// System.out.println("데이터베이스 연결 준비...");
			txtStatus.append("데이터베이스 연결 준비...\n");
			con = DriverManager.getConnection(url, userid, pwd);
			// System.out.println("데이터베이스 연결 성공");
			txtStatus.append("데이터베이스 연결 성공...\n");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Insert")) {
			Scanner scan = new Scanner(System.in);
			PreparedStatement pstmt = null;
			try {
				stmt = con.createStatement();
				String query = "INSERT INTO Orders VALUES(?, ?, ?, ?, ?) ";
				pstmt = con.prepareStatement(query);

				System.out.printf("orderid: ");
				int orderid = scan.nextInt();
				System.out.printf("custid: ");
				int custid = scan.nextInt();
				System.out.printf("bookid: ");
				int bookid = scan.nextInt();
				System.out.printf("saleprice: ");
				int saleprice = scan.nextInt();
				System.out.printf("orderdate: ");
				String orderdate = scan.next();

				pstmt.setInt(1, orderid);
				pstmt.setInt(2, custid);
				pstmt.setInt(3, bookid);
				pstmt.setInt(4, saleprice);
				pstmt.setString(5, orderdate);
				pstmt.executeUpdate();

			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
				/*
				 * } finally { try { if (rs != null) rs.close(); if (stmt != null) stmt.close();
				 * if (con != null) con.close(); } catch (Exception e3) { // TODO: handle
				 * exception }
				 */
			} finally {
				try {
					pstmt.close();

				} catch (Exception e2) {

				}

			}

		}

		else if (e.getActionCommand().equals("Button 1"))

		{
			try {
				stmt = con.createStatement();
				String query = "SELECT * FROM Book ";
				if (e.getSource() == btnOk) {
					txtResult.setText("");
					txtResult.setText("BOOKNO          BOOKNAME       PUBLISHER      PRICE\n");
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getInt(4) + "\n";
						txtResult.append(str);
					}
				} else if (e.getSource() == btnReset) {
					txtResult.setText("");
				}
			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
				/*
				 * } finally { try { if (rs != null) rs.close(); if (stmt != null) stmt.close();
				 * if (con != null) con.close(); } catch (Exception e3) { // TODO: handle
				 * exception }
				 */
			}

		} else if (e.getActionCommand().equals("Button 2")) {

			try {
				stmt = con.createStatement();
				String query = "SELECT * FROM Orders ";
				if (e.getSource() == btnOr) {
					txtResult.setText("");
					txtResult.setText("ORDERID        CUSTID       BOOKID      SALEPRICE     ORDERDATE\n");
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
								+ "\t" + rs.getString(5) + "\n";
						txtResult.append(str);
					}
				} else if (e.getSource() == btnReset) {
					txtResult.setText("");
				}
			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
				/*
				 * } finally { try { if (rs != null) rs.close(); if (stmt != null) stmt.close();
				 * if (con != null) con.close(); } catch (Exception e3) { // TODO: handle
				 * exception }
				 */
			}

		}

		else if (e.getActionCommand().equals("Button 3")) {

			try {
				stmt = con.createStatement();
				String query = "SELECT * FROM Customer ";
				if (e.getSource() == btnCu) {
					txtResult.setText("");
					txtResult.setText("CUSTID        NAME          ADDRESS           PHONE\n");
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getString(4) + "\n";
						txtResult.append(str);
					}
				} else if (e.getSource() == btnReset) {
					txtResult.setText("");
				}
			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
				/*
				 * } finally { try { if (rs != null) rs.close(); if (stmt != null) stmt.close();
				 * if (con != null) con.close(); } catch (Exception e3) { // TODO: handle
				 * exception }
				 */
			}

		} else if (e.getActionCommand().equals("Button 4")) {

			try {
				stmt = con.createStatement();
				String query = " SELECT bookname FROM book t1 WHERE not exists (select bookname from customer t1, orders t2 where t1.custid = t2.custid and t2. bookid = t1. bookid and t1.name = '박지성')";
				if (e.getSource() == btn4) {
					txtResult.setText("");
					txtResult.setText("BOOKNAME\n");
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						String str = rs.getString(1) + "\n";
						txtResult.append(str);
					}
				} else if (e.getSource() == btnReset) {
					txtResult.setText("");
				}
			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
				/*
				 * } finally { try { if (rs != null) rs.close(); if (stmt != null) stmt.close();
				 * if (con != null) con.close(); } catch (Exception e3) { // TODO: handle
				 * exception }
				 */
			}

		}

		else if (e.getActionCommand().equals("Button 5")) {

			try {
				stmt = con.createStatement();
				String query = "select count(distinct publisher) as \"출판사 수\" from customer t1, orders t2, book t3 where t1.custid = t2.custid\r\n and t2.bookid = t3.bookid and t1.name = '박지성' ";
				if (e.getSource() == btn5) {
					txtResult.setText("");
					txtResult.setText("출판사수\n");
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						String str = rs.getInt(1) + "\n";
						txtResult.append(str);
					}
				} else if (e.getSource() == btnReset) {
					txtResult.setText("");
				}
			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
				/*
				 * } finally { try { if (rs != null) rs.close(); if (stmt != null) stmt.close();
				 * if (con != null) con.close(); } catch (Exception e3) { // TODO: handle
				 * exception }
				 */
			}

		} else {

			try {
				stmt = con.createStatement();
				String query = "select count(bookid) from orders where custid = (select custid from customer where name = '박지성')";
				if (e.getSource() == btn6) {
					txtResult.setText("");
					txtResult.setText("COUNT(BOOKID)\n");
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						String str = rs.getInt(1) + "\n";
						txtResult.append(str);
					}
				} else if (e.getSource() == btnReset) {
					txtResult.setText("");
				}
			} catch (Exception e2) {
				System.out.println("쿼리 읽기 실패 :" + e2);
				/*
				 * } finally { try { if (rs != null) rs.close(); if (stmt != null) stmt.close();
				 * if (con != null) con.close(); } catch (Exception e3) { // TODO: handle
				 * exception }
				 */
			}

		}
	}

	public static void main(String[] args) {
		JC19011043M1 BLS = new JC19011043M1();

		// BLS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// BLS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BLS.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					con.close();
				} catch (Exception e4) {
				}
				System.out.println("프로그램 완전 종료!");
				System.exit(0);
			}
		});
	}
}
