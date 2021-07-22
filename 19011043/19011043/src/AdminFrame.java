import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class AdminFrame extends JFrame implements ActionListener {
	JPanel header, content;
	JComboBox<String> tableList;
	JButton showBtn, inputBtn, modifyBtn, deleteBtn, resetBtn;
	JScrollPane scrollPane, scrollPane2;
	JTable table;

	DefaultTableModel model = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public AdminFrame(Connection con) {
		super("관리자 메뉴");
		super.setLocationRelativeTo(null);
		this.con = con;
		layInit();

		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void layInit() {
		String[] tableNames = { "club", "club_history", "course", "lecture", "major", "professor", "professor_major",
				"student", "tuition", "relationship" };

		header = new JPanel();
		content = new JPanel();

		header.setLayout(new GridLayout(1, 6));
		content.setLayout(new GridLayout(1, 1));

		String[][] datas = new String[0][1];
		String[] columns = { "" };
		model = new DefaultTableModel(datas, columns);
		table = new JTable(model);
		table.setDefaultEditor(Object.class, null); // table 수정 불가하게
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPane2 = new JScrollPane(table);

		showBtn = new JButton("테이블 조회");
		inputBtn = new JButton("테이블 입력");
		modifyBtn = new JButton("테이블 변경");
		deleteBtn = new JButton("테이블 삭제");
		resetBtn = new JButton("DB 초기화");

		tableList = new JComboBox<String>(tableNames);

		showBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		inputBtn.addActionListener(this);
		modifyBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		header.add(tableList);
		header.add(showBtn);
		header.add(inputBtn);
		header.add(modifyBtn);
		header.add(deleteBtn);
		header.add(resetBtn);

		content.add(scrollPane2);

		add("North", header);
		add("Center", content);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resetBtn) {
			try {
				stmt = this.con.createStatement();
				for (int i = 0; i < DatabaseInit.queries.length; i++) {
					stmt.executeUpdate(DatabaseInit.queries[i]);
				}
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showConfirmDialog(null, "DB 초기화가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == showBtn) {
			model.setNumRows(0);

			String tableName = tableList.getSelectedItem().toString();

			if (tableName.equals("club")) {
				String[] columnNames = { "id", "name", "student", "professor", "place" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM club");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2),
								Integer.toString(rs.getInt(3)), Integer.toString(rs.getInt(4)), rs.getString(5) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("club_history")) {
				String[] columnNames = { "student", "club" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM club_history");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("course")) {
				String[] columnNames = { "student", "lecture", "professor", "year", "semester", "attendence_score",
						"midterm_score", "final_score", "extra_score", "total_score", "grade" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM course");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)),
								Integer.toString(rs.getInt(3)), Integer.toString(rs.getInt(4)),
								Integer.toString(rs.getInt(5)), Double.toString(rs.getDouble(6)),
								Double.toString(rs.getDouble(7)), Double.toString(rs.getDouble(8)),
								Double.toString(rs.getDouble(9)), Double.toString(rs.getDouble(10)), rs.getString(11) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("lecture")) {
				String[] columnNames = { "id", "class_no", "professor", "name", "day", "period", "unit", "course_time",
						"major", "place" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM lecture");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)),
								Integer.toString(rs.getInt(1)), rs.getString(4), rs.getString(5),
								Integer.toString(rs.getInt(6)), Integer.toString(rs.getInt(7)),
								Integer.toString(rs.getInt(8)), Integer.toString(rs.getInt(9)), rs.getString(10) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("major")) {
				String[] columnNames = { "id", "name", "phone", "place", "professor" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM major");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3),
								rs.getString(4), Integer.toString(rs.getInt(5)) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("professor")) {
				String[] columnNames = { "id", "name", "address", "phone", "email" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM professor");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("professor_major")) {
				String[] columnNames = { "professor", "major" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM professor_major");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("student")) {
				String[] columnNames = { "id", "name", "address", "phone", "email", "major", "professor",
						"tuition_address", "sub_major" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM student");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), Integer.toString(rs.getInt(6)),
								Integer.toString(rs.getInt(7)), rs.getString(8), Integer.toString(rs.getInt(9)) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("tuition")) {
				String[] columnNames = { "student", "year", "semester", "total_tuition", "payed_tuition",
						"payed_date" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM tuition");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)),
								Integer.toString(rs.getInt(3)), Integer.toString(rs.getInt(4)),
								Integer.toString(rs.getInt(5)), rs.getString(6) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else if (tableName.equals("relationship")) {
				String[] columnNames = { "student", "club", "year", "semester" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = this.con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM relationship");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)),
								Integer.toString(rs.getInt(3)), Integer.toString(rs.getInt(4)) };
						model.addRow(row);
					}
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "문제가 발생하였습니다. DB 초기화를 진행해주세요.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getSource() == inputBtn) {
			String tableName = tableList.getSelectedItem().toString();
			if (tableName.equals("club")) {
				JTextField nameTf = new JTextField();
				JTextField studentTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField placeTf = new JTextField();

				Object[] message = { "name", nameTf, "student", studentTf, "professor", professorTf, "place", placeTf };

				int option = JOptionPane.showConfirmDialog(null, message, "club 테이블 입력", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String name = nameTf.getText();
					String student = studentTf.getText();
					String professor = professorTf.getText();
					String place = placeTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO club (name, student, professor, place) " + "VALUES(\"" + name
								+ "\"," + student + "," + professor + ",\"" + place + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("club_history")) {
				JTextField studentTf = new JTextField();
				JTextField clubTf = new JTextField();

				Object[] message = { "student", studentTf, "club", clubTf };

				int option = JOptionPane.showConfirmDialog(null, message, "club_history 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String student = studentTf.getText();
					String club = clubTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO club_history (student, club) " + "VALUES(" + student + "," + club
								+ ")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("course")) {
				JTextField studentTf = new JTextField();
				JTextField lectureTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField yearTf = new JTextField();
				JTextField semesterTf = new JTextField();
				JTextField attendenceScoreTf = new JTextField();
				JTextField midtermScoreTf = new JTextField();
				JTextField finalScoreTf = new JTextField();
				JTextField extraScoreTf = new JTextField();
				JTextField gradeTf = new JTextField();

				Object[] message = { "student", studentTf, "lecture", lectureTf, "professor", professorTf, "year",
						yearTf, "semester", semesterTf, "atten", attendenceScoreTf, "midterm_score", midtermScoreTf,
						"final_score", finalScoreTf, "extra_score", extraScoreTf, "grade", gradeTf };

				int option = JOptionPane.showConfirmDialog(null, message, "course 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String student = studentTf.getText();
					String lecture = lectureTf.getText();
					String professor = professorTf.getText();
					String year = yearTf.getText();
					String semester = semesterTf.getText();
					String attendenceScore = attendenceScoreTf.getText();
					String midtermScore = midtermScoreTf.getText();
					String finalScore = finalScoreTf.getText();
					String extraScore = extraScoreTf.getText();
					String grade = gradeTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO course (student, lecture, professor, year, semester, attendence_score, midterm_score, final_score, extra_score, grade) VALUES("
								+ student + "," + lecture + "," + professor + "," + year + "," + semester + ","
								+ attendenceScore + "," + midtermScore + "," + finalScore + "," + extraScore + ",\""
								+ grade + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("lecture")) {
				JTextField classNoTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField nameTf = new JTextField();
				JTextField dayTf = new JTextField();
				JTextField periodTf = new JTextField();
				JTextField unitTf = new JTextField();
				JTextField courseTimeTf = new JTextField();
				JTextField majorTf = new JTextField();
				JTextField placeTf = new JTextField();

				Object[] message = { "class_no", classNoTf, "professor", professorTf, "name", nameTf, "day", dayTf,
						"period", periodTf, "unit", unitTf, "course_time", courseTimeTf, "major", majorTf, "place",
						placeTf };

				int option = JOptionPane.showConfirmDialog(null, message, "lecture 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String classNo = classNoTf.getText();
					String professor = professorTf.getText();
					String name = nameTf.getText();
					String day = dayTf.getText();
					String period = periodTf.getText();
					String unit = unitTf.getText();
					String courseTime = courseTimeTf.getText();
					String major = majorTf.getText();
					String place = placeTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO lecture (class_no, professor, name, day, period, unit, course_time, major, place) VALUES("
								+ classNo + "," + professor + ",\"" + name + "\",\"" + day + "\"," + period + "," + unit
								+ "," + courseTime + "," + major + ",\"" + place + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("major")) {
				JTextField nameTf = new JTextField();
				JTextField phoneTf = new JTextField();
				JTextField placeTf = new JTextField();
				JTextField professorTf = new JTextField();

				Object[] message = { "name", nameTf, "phone", phoneTf, "place", placeTf, "professor", professorTf };

				int option = JOptionPane.showConfirmDialog(null, message, "major 테이블 입력", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String name = nameTf.getText();
					String phone = phoneTf.getText();
					String place = placeTf.getText();
					String professor = professorTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO major (name, phone, place, professor) " + "VALUES(\"" + name
								+ "\",\"" + phone + "\",\"" + place + "\"," + professor + ")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("professor")) {
				JTextField nameTf = new JTextField();
				JTextField addressTf = new JTextField();
				JTextField phoneTf = new JTextField();
				JTextField emailTf = new JTextField();

				Object[] message = { "name", nameTf, "address", addressTf, "phone", phoneTf, "email", emailTf };

				int option = JOptionPane.showConfirmDialog(null, message, "professor 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String name = nameTf.getText();
					String address = addressTf.getText();
					String phone = phoneTf.getText();
					String email = emailTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO professor (name, address, phone, email) " + "VALUES(\"" + name
								+ "\",\"" + address + "\",\"" + phone + "\",\"" + email + "\")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("professor_major")) {
				JTextField professorTf = new JTextField();
				JTextField majorTf = new JTextField();

				Object[] message = { "professor", professorTf, "major", majorTf };

				int option = JOptionPane.showConfirmDialog(null, message, "professor_major 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String professor = professorTf.getText();
					String major = majorTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO professor_major (professor, major) " + "VALUES(" + professor + ","
								+ major + ")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("student")) {
				JTextField nameTf = new JTextField();
				JTextField addressTf = new JTextField();
				JTextField phoneTf = new JTextField();
				JTextField emailTf = new JTextField();
				JTextField majorTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField tuitionAddressTf = new JTextField();
				JTextField subMajorTf = new JTextField();

				Object[] message = { "name", nameTf, "address", addressTf, "phone", phoneTf, "email", emailTf, "major",
						majorTf, "professor", professorTf, "tuition_address", tuitionAddressTf, "sub_major",
						subMajorTf };

				int option = JOptionPane.showConfirmDialog(null, message, "student 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String name = nameTf.getText();
					String address = addressTf.getText();
					String phone = phoneTf.getText();
					String email = emailTf.getText();
					String major = majorTf.getText();
					String professor = professorTf.getText();
					String tuitionAddress = tuitionAddressTf.getText();
					String subMajor = subMajorTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO student (name, address, phone, email, major, professor, tuition_address, sub_major) VALUES(\""
								+ name + "\",\"" + address + "\",\"" + phone + "\",\"" + email + "\"," + major + ","
								+ professor + ",\"" + tuitionAddress + "\"," + subMajor + ")";
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("tuition")) {
				JTextField studentTf = new JTextField();
				JTextField yearTf = new JTextField();
				JTextField semesterTf = new JTextField();
				JTextField totalTuitionTf = new JTextField();
				JTextField payedTuitionTf = new JTextField();
				JTextField payedDateTf = new JTextField();

				Object[] message = { "student", studentTf, "year", yearTf, "semester", semesterTf, "total_tuition",
						totalTuitionTf, "payed_tuition", payedTuitionTf, "payed_date ex: 2021-06-01", payedDateTf };

				int option = JOptionPane.showConfirmDialog(null, message, "tuition 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String student = studentTf.getText();
					String year = yearTf.getText();
					String semester = semesterTf.getText();
					String totalTuition = totalTuitionTf.getText();
					String payTuition = payedTuitionTf.getText();
					String payDate = payedDateTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO tuition (student, year, semester, total_tuition, payed_tuition, payed_date) VALUES("
								+ student + "," + year + "," + semester + "," + totalTuition + "," + payTuition
								+ ", STR_TO_DATE('" + payDate + "','%Y-%m-%d'));";
						System.out.println(query);
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 입력이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("relationship")) {
				JTextField studentTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField yearTf = new JTextField();
				JTextField semesterTf = new JTextField();

				Object[] message = { "student", studentTf, "professor", professorTf, "year", yearTf, "semester",
						semesterTf };

				int option = JOptionPane.showConfirmDialog(null, message, "relationship 테이블 입력",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String student = studentTf.getText();
					String professor = professorTf.getText();
					String year = yearTf.getText();
					String semester = semesterTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "INSERT INTO relationship (student, professor, year, semester) VALUES(" + student
								+ "," + professor + "," + year + "," + semester + ")";
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
		} else if (e.getSource() == modifyBtn) {
			String tableName = tableList.getSelectedItem().toString();
			if (tableName.equals("club")) {
				JTextField conditionTf = new JTextField();
				JTextField nameTf = new JTextField();
				JTextField studentTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField placeTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "name", nameTf, "student", studentTf, "professor", professorTf,
						"place", placeTf };

				int option = JOptionPane.showConfirmDialog(null, message, "club 테이블 변경", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String name = nameTf.getText();
					String student = studentTf.getText();
					String professor = professorTf.getText();
					String place = placeTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE club SET " + "name=\"" + name + "\", student=" + student + ", professor="
								+ professor + ", place=\"" + place + "\"" + " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("club_history")) {
				JTextField conditionTf = new JTextField();
				JTextField studentTf = new JTextField();
				JTextField clubTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "student", studentTf, "club", clubTf };

				int option = JOptionPane.showConfirmDialog(null, message, "club_history 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String student = studentTf.getText();
					String club = clubTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE club_history SET " + "student=" + student + ", club=" + club + " WHERE "
								+ condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("course")) {
				JTextField conditionTf = new JTextField();
				JTextField studentTf = new JTextField();
				JTextField lectureTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField yearTf = new JTextField();
				JTextField semesterTf = new JTextField();
				JTextField attendenceScoreTf = new JTextField();
				JTextField midtermScoreTf = new JTextField();
				JTextField finalScoreTf = new JTextField();
				JTextField extraScoreTf = new JTextField();
				JTextField gradeTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "student", studentTf, "lecture", lectureTf, "professor",
						professorTf, "year", yearTf, "semester", semesterTf, "attendence_score", attendenceScoreTf,
						"midterm_score", midtermScoreTf, "final_score", finalScoreTf, "extra_score", extraScoreTf,
						"grade", gradeTf };

				int option = JOptionPane.showConfirmDialog(null, message, "course 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String student = studentTf.getText();
					String lecture = lectureTf.getText();
					String professor = professorTf.getText();
					String year = yearTf.getText();
					String semester = semesterTf.getText();
					String attendenceScore = attendenceScoreTf.getText();
					String midtermScore = midtermScoreTf.getText();
					String finalScore = finalScoreTf.getText();
					String extraScore = extraScoreTf.getText();
					String grade = gradeTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE course SET " + "student=" + student + ", lecture=" + lecture
								+ ", professor=" + professor + ", year=" + year + ", semester=" + semester
								+ ", attendence_score=" + attendenceScore + ", midterm_score=" + midtermScore
								+ ", final_score=" + finalScore + ", extra_score=" + extraScore + ", grade=\"" + grade
								+ "\"" + " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("lecture")) {
				JTextField conditionTf = new JTextField();
				JTextField classNoTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField nameTf = new JTextField();
				JTextField dayTf = new JTextField();
				JTextField periodTf = new JTextField();
				JTextField unitTf = new JTextField();
				JTextField courseTimeTf = new JTextField();
				JTextField majorTf = new JTextField();
				JTextField placeTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "class_no", classNoTf, "professor", professorTf, "name",
						nameTf, "day", dayTf, "period", periodTf, "unit", unitTf, "course_time", courseTimeTf, "major",
						majorTf, "place", placeTf };

				int option = JOptionPane.showConfirmDialog(null, message, "lecture 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String classNo = classNoTf.getText();
					String professor = professorTf.getText();
					String name = nameTf.getText();
					String day = dayTf.getText();
					String period = periodTf.getText();
					String unit = unitTf.getText();
					String courseTime = courseTimeTf.getText();
					String major = majorTf.getText();
					String place = placeTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE lecture SET " + "class_no=" + classNo + ", professor=" + professor
								+ ", name=\"" + name + "\", day=\"" + day + "\", period=" + period + ", unit=" + unit
								+ ", course_time=" + courseTime + ", major=" + major + ", place=\"" + place + "\""
								+ " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("major")) {
				JTextField conditionTf = new JTextField();
				JTextField nameTf = new JTextField();
				JTextField phoneTf = new JTextField();
				JTextField placeTf = new JTextField();
				JTextField professorTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "name", nameTf, "phone", phoneTf, "place", placeTf,
						"professor", professorTf };

				int option = JOptionPane.showConfirmDialog(null, message, "major 테이블 변경", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String name = nameTf.getText();
					String phone = phoneTf.getText();
					String place = placeTf.getText();
					String professor = professorTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE major SET " + "name=\"" + name + "\", phone=\"" + phone + "\", place=\""
								+ place + "\", professor=" + professor + " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("professor")) {
				JTextField conditionTf = new JTextField();
				JTextField nameTf = new JTextField();
				JTextField addressTf = new JTextField();
				JTextField phoneTf = new JTextField();
				JTextField emailTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "name", nameTf, "address", addressTf, "phone", phoneTf,
						"email", emailTf };

				int option = JOptionPane.showConfirmDialog(null, message, "professor 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String name = nameTf.getText();
					String address = addressTf.getText();
					String phone = phoneTf.getText();
					String email = emailTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE professor SET " + "name=\"" + name + "\", address=\"" + address
								+ "\", phone=\"" + phone + "\", email=\"" + email + "\"" + " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("professor_major")) {
				JTextField conditionTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField majorTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "professor", professorTf, "major", majorTf };

				int option = JOptionPane.showConfirmDialog(null, message, "professor_major 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String professor = professorTf.getText();
					String major = majorTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE professor_major SET " + "professor=" + professor + ", major=" + major
								+ " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("student")) {
				JTextField conditionTf = new JTextField();
				JTextField nameTf = new JTextField();
				JTextField addressTf = new JTextField();
				JTextField phoneTf = new JTextField();
				JTextField emailTf = new JTextField();
				JTextField majorTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField tuitionTf = new JTextField();
				JTextField subMajorTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "name", nameTf, "address", addressTf, "phone", phoneTf,
						"email", emailTf, "major", majorTf, "professor", professorTf, "tuition_address", tuitionTf,
						"sub_major", subMajorTf };

				int option = JOptionPane.showConfirmDialog(null, message, "student 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String name = nameTf.getText();
					String address = addressTf.getText();
					String phone = phoneTf.getText();
					String email = emailTf.getText();
					String major = majorTf.getText();
					String professor = professorTf.getText();
					String tuitionAddress = tuitionTf.getText();
					String subMajor = subMajorTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE student SET " + "name=\"" + name + "\", address=\"" + address
								+ "\", phone=\"" + phone + "\", email=\"" + email + "\", major=" + major
								+ ", professor=" + professor + ", tuition_address=\"" + tuitionAddress
								+ "\", sub_major=" + subMajor + " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("tuition")) {
				JTextField conditionTf = new JTextField();
				JTextField studentTf = new JTextField();
				JTextField yearTf = new JTextField();
				JTextField semesterTf = new JTextField();
				JTextField totalTuitionTf = new JTextField();
				JTextField payedTuitionTf = new JTextField();
				JTextField payedDateTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "student", studentTf, "year", yearTf, "semester", semesterTf,
						"total_tuition", totalTuitionTf, "payed_tuition", payedTuitionTf, "payed_date ex: 2021-06-01",
						payedDateTf };

				int option = JOptionPane.showConfirmDialog(null, message, "tuition 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String student = studentTf.getText();
					String year = yearTf.getText();
					String semester = semesterTf.getText();
					String totalTuition = totalTuitionTf.getText();
					String payTuition = payedTuitionTf.getText();
					String payDate = payedDateTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE tuition SET " + "student=" + student + ", year=" + year + ", semester="
								+ semester + ", total_tuition=" + totalTuition + ", payed_tuition=" + payTuition
								+ ", payed_date=STR_TO_DATE('" + payDate + "','%Y-%m-%d')" + " WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 변경이 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("relationship")) {
				JTextField conditionTf = new JTextField();
				JTextField studentTf = new JTextField();
				JTextField professorTf = new JTextField();
				JTextField yearTf = new JTextField();
				JTextField semesterTf = new JTextField();

				Object[] message = { "조건식", conditionTf, "student", studentTf, "professor", professorTf, "year", yearTf,
						"semester", semesterTf };

				int option = JOptionPane.showConfirmDialog(null, message, "relationship 테이블 변경",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();
					String student = studentTf.getText();
					String professor = professorTf.getText();
					String year = yearTf.getText();
					String semester = semesterTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "UPDATE relationship SET " + "student=" + student + ", professor=" + professor
								+ ", year=" + year + ", semester=" + semester + " WHERE " + condition;
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
		} else if (e.getSource() == deleteBtn) {
			String tableName = tableList.getSelectedItem().toString();
			if (tableName.equals("club")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "club 테이블 삭제", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM club WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("club_history")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "club_history 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM club_history WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("course")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "course 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM course WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("lecture")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "lecture 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM lecture WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("major")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "major 테이블 삭제", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM major WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("professor")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "professor 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM professor WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("professor_major")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "professor_major 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM professor_major WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("student")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "student 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM student WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("tuition")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "tuition 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM tuition WHERE " + condition;
						stmt.executeUpdate(query);
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "데이터 삭제가 완료되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (tableName.equals("relationship")) {
				JTextField conditionTf = new JTextField();

				Object[] message = { "조건식", conditionTf };

				int option = JOptionPane.showConfirmDialog(null, message, "relationship 테이블 삭제",
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					String condition = conditionTf.getText();

					try {
						stmt = this.con.createStatement();
						String query = "DELETE FROM relationship WHERE " + condition;
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
		}

		content.revalidate();
		content.repaint();
	}
}