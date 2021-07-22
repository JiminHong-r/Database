import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class HospitalRecords extends JFrame implements ActionListener {
	JPanel header, content;
	JComboBox<String> tableList;
	JButton resetBtn, inputBtn, deleteBtn, modifyBtn, search1Btn, search2Btn, search3Btn;
	JScrollPane scrollPane, scrollPane2;
	JTable table;

	DefaultTableModel model = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	static Connection con;

	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public HospitalRecords() {
		super("19011043 홍지민");
		super.setLocationRelativeTo(null);
		conDB();
		layInit();

		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void layInit() {
		String[] tableNames = { "Doctors", "Nurses", "Patients", "Treatments", "Charts" };
		
		header = new JPanel();
		content = new JPanel();

		header.setLayout(new GridLayout(1, 7));
		content.setLayout(new GridLayout(1, 2));
		 
		String[] columns = { "" };
		String[][] datas = new String[0][columns.length];
		model = new DefaultTableModel(datas, columns);
		table = new JTable(model);
		table.setDefaultEditor(Object.class, null); // table 수정 불가하게
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPane2 = new JScrollPane(table);

		resetBtn = new JButton("초기화 버튼");
		inputBtn = new JButton("삽입 버튼");
		deleteBtn = new JButton("삭제 버튼");
		modifyBtn = new JButton("변경 버튼");
		search1Btn = new JButton("검색1 버튼");
		search2Btn = new JButton("검색2 버튼");
		search3Btn = new JButton("검색3 버튼");
		
		tableList = new JComboBox<String>(tableNames);

		header.add(tableList);
		header.add(resetBtn);
		header.add(inputBtn);
		header.add(deleteBtn);
		header.add(modifyBtn);
		header.add(search1Btn);
		header.add(search2Btn);
		header.add(search3Btn);

		content.add(scrollPane2);

		resetBtn.addActionListener(this);
		inputBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		modifyBtn.addActionListener(this);
		search1Btn.addActionListener(this);
		search2Btn.addActionListener(this);
		search3Btn.addActionListener(this);

		add("North", header);
		add("Center", content);
	}

	public void conDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, userid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		content.removeAll();
		content.add(scrollPane2);
		
		if (e.getSource() == resetBtn) { // 초기화 버튼 확안
			try {
				stmt = con.createStatement();
				for (int i = 0; i < DatabaseReset.queries.length; i++) {
					stmt.executeUpdate(DatabaseReset.queries[i]);
				}
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showConfirmDialog(null, "DB 초기화가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			
		} else if (e.getSource() == inputBtn) { // 삽입 버튼
			String tableName = tableList.getSelectedItem().toString();
			
			if (tableName.equals("Doctors")) {
				JTextField doc_idTf = new JTextField();
				JTextField major_treatTf = new JTextField();
				JTextField doc_nameTf = new JTextField();
				JTextField doc_genTf = new JTextField();
				JTextField doc_phoneTf = new JTextField();
				JTextField doc_emailTf = new JTextField();
				JTextField doc_positionTf = new JTextField();

				Object[] message = { "doc_id", doc_idTf, "major_treat", major_treatTf, "doc_name", doc_nameTf, "doc_gen", doc_genTf, "doc_phone", doc_phoneTf, "doc_email", doc_emailTf, "doc_position", doc_positionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Doctors 테이블 입력", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String doc_id = doc_idTf.getText();
					String major_treat = major_treatTf.getText();
					String doc_name = doc_nameTf.getText();
					String doc_gen = doc_genTf.getText();
					String doc_phone = doc_phoneTf.getText();
					String doc_email = doc_emailTf.getText();
					String doc_position = doc_positionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "INSERT INTO Doctors (doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) " + "VALUES(" + doc_id
								+ ",\"" + major_treat + "\",\"" + doc_name + "\",\"" + doc_gen + "\",\"" + doc_phone + "\",\"" + doc_email + "\",\"" + doc_position + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else if (tableName.equals("Nurses")) {
				JTextField nur_idTf = new JTextField();
				JTextField major_jobTf = new JTextField();
				JTextField nur_nameTf = new JTextField();
				JTextField nur_genTf = new JTextField();
				JTextField nur_phoneTf = new JTextField();
				JTextField nur_emailTf = new JTextField();
				JTextField nur_positionTf = new JTextField();

				Object[] message = { "nur_id", nur_idTf, "major_job", major_jobTf, "nur_name", nur_nameTf, "nur_gen", nur_genTf, "nur_phone", nur_phoneTf, "nur_email", nur_emailTf, "nur_position", nur_positionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Nurses 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);
			
				if (option == JOptionPane.OK_OPTION) {
					String nur_id = nur_idTf.getText();
					String major_job = major_jobTf.getText();
					String nur_name = nur_nameTf.getText();
					String nur_gen = nur_genTf.getText();
					String nur_phone = nur_phoneTf.getText();
					String nur_email = nur_emailTf.getText();
					String nur_position = nur_positionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "INSERT INTO Nurses (nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) " + "VALUES(" + nur_id
								+ ",\"" + major_job + "\",\"" + nur_name + "\",\"" + nur_gen + "\",\"" + nur_phone + "\",\"" + nur_email + "\",\"" + nur_position + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else if (tableName.equals("Patients")) {
				JTextField pat_idTf = new JTextField();
				JTextField nur_idTf = new JTextField();
				JTextField doc_idTf = new JTextField();
				JTextField pat_nameTf = new JTextField();
				JTextField pat_genTf = new JTextField();
				JTextField pat_juminTf = new JTextField();
				JTextField pat_addrTf = new JTextField();
				JTextField pat_phoneTf = new JTextField();
				JTextField pat_emailTf = new JTextField();
				JTextField pat_jobTf = new JTextField();

				Object[] message = { "pat_id", pat_idTf, "nur_id", nur_idTf, "doc_id", doc_idTf, "pat_name",
						pat_nameTf, "pat_gen", pat_genTf, "pat_jumin", pat_juminTf, "pat_addr", pat_addrTf,
						"pat_phone", pat_phoneTf, "pat_email", pat_emailTf, "pat_job", pat_jobTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Patients 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);
			    
				if (option == JOptionPane.OK_OPTION) {
					String pat_id = pat_idTf.getText();
					String nur_id = nur_idTf.getText();
					String doc_id = doc_idTf.getText();
					String pat_name = pat_nameTf.getText();
					String pat_gen = pat_genTf.getText();
					String pat_jumin = pat_juminTf.getText();
					String pat_addr = pat_addrTf.getText();
					String pat_phone = pat_phoneTf.getText();
					String pat_email = pat_emailTf.getText();
					String pat_job = pat_jobTf.getText();

					try {
						stmt = con.createStatement();
						String query = "INSERT INTO Patients (pat_id, nur_id, doc_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job) VALUES("
								+ pat_id + "," + nur_id + "," + doc_id + ",\"" + pat_name + "\",\"" + pat_gen + "\",\""
								+ pat_jumin + "\",\"" + pat_addr + "\",\"" + pat_phone + "\",\"" + pat_email + "\",\""
								+ pat_job + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else if (tableName.equals("Treatments")) {
				JTextField treat_idTf = new JTextField();
				JTextField pat_idTf = new JTextField();
				JTextField doc_idTf = new JTextField();
				JTextField treat_contentsTf = new JTextField();
				JTextField treat_dateTf = new JTextField();

				Object[] message = { "treat_id", treat_idTf, "pat_id", pat_idTf, "doc_id", doc_idTf, "treat_contents",
						treat_contentsTf, "treat_date", treat_dateTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Treatments 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String treat_id = treat_idTf.getText();
					String pat_id = pat_idTf.getText();
					String doc_id = doc_idTf.getText();
					String treat_contents = treat_contentsTf.getText();
					String treat_date = treat_dateTf.getText();

					try {
						stmt = con.createStatement();
						String query = "INSERT INTO Treatments (treat_id, pat_id, doc_id, treat_contents, treat_date) VALUES("
								+ treat_id + "," + pat_id + "," + doc_id + ",\"" + treat_contents + "\",\"" + treat_date + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("Charts")) {
				JTextField chart_idTf = new JTextField();
				JTextField treat_idTf = new JTextField();
				JTextField doc_idTf = new JTextField();
				JTextField pat_idTf = new JTextField();
				JTextField nur_idTf = new JTextField();
				JTextField chart_contentsTf = new JTextField();
			    
				Object[] message = { "chart_id", chart_idTf, "treat_id", treat_idTf, "doc_id", doc_idTf, "pat_id", pat_idTf, "nur_id", nur_idTf, "chart_contents", chart_contentsTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Charts 테이블 입력", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String chart_id = chart_idTf.getText();
					String treat_id = treat_idTf.getText();
					String doc_id = doc_idTf.getText();
					String pat_id = pat_idTf.getText();
					String nur_id = pat_idTf.getText();
					String chart_contents = chart_contentsTf.getText();

					try {
						stmt = con.createStatement();
						String query = "INSERT INTO Charts (chart_id, treat_id, doc_id, pat_id, nur_id, chart_contents) " + "VALUES(\"" + chart_id
								+ "\"," + treat_id + "," + doc_id + "," + pat_id + "," + nur_id + ",\"" + chart_contents + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource() == deleteBtn) { // 삭제 버튼
			String tableName = tableList.getSelectedItem().toString();
			
			if (tableName.equals("Doctors")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Doctors 테이블 삭제", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "DELETE FROM Doctors WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else if (tableName.equals("Nurses")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Nurses 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "DELETE FROM Nurses WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("Patients")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Patients 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "DELETE FROM Patients WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("Treatments")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Treatments 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "DELETE FROM Treatments WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("Charts")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Charts 테이블 삭제", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "DELETE FROM Charts WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} 
		} else if (e.getSource() == modifyBtn) { // 변경 버튼
			String tableName = tableList.getSelectedItem().toString();
			
			if (tableName.equals("Doctors")) {
				JTextField setconditionTf = new JTextField();
				JTextField whereconditionTf = new JTextField();

				Object[] message = { "set절", setconditionTf, "where절", whereconditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Doctors 테이블 변경", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String setcondition = setconditionTf.getText();
					String wherecondition = whereconditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "UPDATE Doctors SET " + setcondition + " WHERE " + wherecondition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else if (tableName.equals("Nurses")) {
				JTextField setconditionTf = new JTextField();
				JTextField whereconditionTf = new JTextField();

				Object[] message = { "set절", setconditionTf, "where절", whereconditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Nurses 테이블 변경", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String setcondition = setconditionTf.getText();
					String wherecondition = whereconditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "UPDATE Nurses SET " + setcondition + " WHERE " + wherecondition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("Patients")) {
				JTextField setconditionTf = new JTextField();
				JTextField whereconditionTf = new JTextField();

				Object[] message = { "set절", setconditionTf, "where절", whereconditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Patients 테이블 변경", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String setcondition = setconditionTf.getText();
					String wherecondition = whereconditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "UPDATE Patients SET " + setcondition + " WHERE " + wherecondition;
						stmt.executeUpdate(query);
						} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else if (tableName.equals("Treatments")) {
				JTextField setconditionTf = new JTextField();
				JTextField whereconditionTf = new JTextField();

				Object[] message = { "set절", setconditionTf, "where절", whereconditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Treatments 테이블 변경", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String setcondition = setconditionTf.getText();
					String wherecondition = whereconditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "UPDATE Treatments SET " + setcondition + " WHERE " + wherecondition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else if (tableName.equals("Charts")) {
				JTextField setconditionTf = new JTextField();
				JTextField whereconditionTf = new JTextField();

				Object[] message = { "set절", setconditionTf, "where절", whereconditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "Charts 테이블 변경", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String setcondition = setconditionTf.getText();
					String wherecondition = whereconditionTf.getText();

					try {
						stmt = con.createStatement();
						String query = "UPDATE Charts SET " + setcondition + " WHERE " + wherecondition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} 
		} else if (e.getSource() == search1Btn) { // 검색1 버튼
			model.setNumRows(0);

			String tableName = tableList.getSelectedItem().toString();
		    
			if (tableName.equals("Doctors")) {
				String[] columnNames = { "doc_id", "major_treat", "doc_name", "doc_gen", "doc_phone", "doc_email", "doc_position" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM Doctors");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2),
								rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("Nurses")) {
				String[] columnNames = { "nur_id", "major_job", "nur_name", "nur_gen", "nur_phone", "nur_email", "nur_position" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM Nurses");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("Patients")) {
				String[] columnNames = { "pat_id", "nur_id", "doc_id", "pat_name", "pat_gen", "pat_jumin",
						"pat_addr", "pat_phone", "pat_email", "pat_job" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM Patients");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)),
								Integer.toString(rs.getInt(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
				
			} else if (tableName.equals("Treatments")) {
				String[] columnNames = { "treat_id", "pat_id", "doc_id", "treat_contents", "treat_date" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM Treatments");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)),
								Integer.toString(rs.getInt(3)), rs.getString(4), rs.getString(5) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
				
			} else if (tableName.equals("Charts")) {
				String[] columnNames = { "chart_id", "treat_id", "doc_id", "pat_id", "nur_id", "chart_contents" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM Charts");

					while (rs.next()) {
						String[] row = { rs.getString(1), Integer.toString(rs.getInt(2)), Integer.toString(rs.getInt(3)),
								Integer.toString(rs.getInt(4)), Integer.toString(rs.getInt(5)), rs.getString(6) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} 
			
		} else if (e.getSource() == search2Btn) { // 검색2 버튼
			String[] columnNames = { "pat_id", "nur_id", "doc_id", "pat_name", "pat_gen", "pat_jumin",
					"pat_addr", "pat_phone", "pat_email", "pat_job" };			
			
			String[][] datas = new String[0][columnNames.length];
			model.setDataVector(datas, columnNames);
			
			try {
				stmt = con.createStatement();
				String query = "SELECT * FROM Patients WHERE pat_id in (SELECT pat_id FROM Treatments) GROUP BY pat_id";
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)),
							Integer.toString(rs.getInt(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10) };
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showConfirmDialog(null, "데이터 검색이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);

		} else { // 검색3 버튼
			String[] columnNames = { "doc_id", "major_treat", "doc_name", "doc_gen", "doc_phone", "doc_email", "doc_position" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);
			
			try {
				stmt = con.createStatement();
				String query = "SELECT * FROM Doctors WHERE doc_id in (SELECT doc_id FROM Charts WHERE pat_id in (SELECT pat_id FROM Treatments)) GROUP BY doc_id;";
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };					
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showConfirmDialog(null, "데이터 검색이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
		}
		content.revalidate();
		content.repaint();
		}
	
	
	public static void main(String[] args) {
		HospitalRecords BLS = new HospitalRecords();

		BLS.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					con.close();
				} catch (Exception e4) {
				}
				System.exit(0);
			}
		});
	}

}