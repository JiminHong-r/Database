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

import java.util.regex.Pattern;

public class StudentFrame extends JFrame implements ActionListener {
	JPanel header, content;
	JButton idBtn, inputBtn, timeTableBtn, courseBtn, addCourseBtn, clubBtn, gradeBtn;
	JScrollPane scrollPane2, scrollPane3;
	JTable table, table2;

	DefaultTableModel model = null;
	DefaultTableModel model2 = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs;

	String id = null, year = null, semester = null;
	String pattern = "^[0-9]*$"; // 숫자만

	public StudentFrame(Connection con) {
		super("학생 메뉴");
		super.setLocationRelativeTo(null);
		this.con = con;
		layInit();

		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void layInit() {
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

		String[] columns2 = { "id", "name", "address", "phone", "email", "major", "professor", "tuition", "sub_major" };
		String[][] datas2 = new String[0][columns.length];
		model2 = new DefaultTableModel(datas2, columns2);
		table2 = new JTable(model2);
		table2.setDefaultEditor(Object.class, null); // table 수정 불가하게
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPane2 = new JScrollPane(table);
		scrollPane3 = new JScrollPane(table2);

		idBtn = new JButton("학생 번호 입력");
		inputBtn = new JButton("연도/학기 입력");
		timeTableBtn = new JButton("시간표");
		courseBtn = new JButton("수강 과목");
		addCourseBtn = new JButton("수강 내역 신규 삽입");
		clubBtn = new JButton("동아리");
		gradeBtn = new JButton("성적");

		header.add(idBtn);
		header.add(inputBtn);
		header.add(timeTableBtn);
		header.add(courseBtn);
		header.add(addCourseBtn);
		header.add(clubBtn);
		header.add(gradeBtn);

		content.add(scrollPane2);

		idBtn.addActionListener(this);
		inputBtn.addActionListener(this);
		timeTableBtn.addActionListener(this);
		courseBtn.addActionListener(this);
		addCourseBtn.addActionListener(this);
		clubBtn.addActionListener(this);
		gradeBtn.addActionListener(this);

		add("North", header);
		add("Center", content);
	}

	public double gradeToGPA(String grade) {
		double result = 0.0;
		switch (grade) {
			case "A+":
				result = 4.5;
				break;
			case "A":
				result = 4.0;
				break;
			case "B+":
				result = 3.5;
				break;
			case "B":
				result = 3.0;
				break;
			case "C+":
				result = 2.5;
				break;
			case "C":
				result = 2.0;
				break;
			case "D+":
				result = 1.5;
				break;
			case "D":
				result = 1.0;
				break;
			case "F":
				result = 0.0;
				break;
			default:
				result = -1.0;
		}
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		content.removeAll();
		content.add(scrollPane2);

		if (e.getSource() == idBtn) { // 학생 아이디 입력

			JTextField sid = new JTextField();

			Object[] message = { "학생 번호", sid };

			int option = JOptionPane.showConfirmDialog(null, message, "학생 번호 입력", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				id = sid.getText();

				if (Pattern.matches(pattern, id) == false) {
					JOptionPane.showMessageDialog(null, "학생 번호는 숫자로 입력해주세요.", "ERROR_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					JOptionPane.showConfirmDialog(null, "학생 번호가 입력되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT id FROM student WHERE id = " + id);
				if (rs.next() == false) {
					JOptionPane.showConfirmDialog(null, "존재하지 않는 학생 번호가 입력되었습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
			}
		} else if (e.getSource() == inputBtn) {
			JTextField iyear = new JTextField();
			JTextField isemester = new JTextField();

			Object[] message = { "연도", iyear, "학기", isemester, };

			int option = JOptionPane.showConfirmDialog(null, message, "연도/학기 입력", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				year = iyear.getText();
				semester = isemester.getText();

				if (Pattern.matches(pattern, year) == false || Pattern.matches(pattern, semester) == false) {
					JOptionPane.showMessageDialog(null, "연도/학기는 숫자로 입력해주세요.", "ERROR_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					JOptionPane.showConfirmDialog(null, "연도/학기가 입력되었습니다.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT year FROM course WHERE year = " + year);
				if (rs.next() == false) {
					JOptionPane.showConfirmDialog(null, "잘못된 연도가 입력되었습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT semester FROM course WHERE semester = " + semester);
				if (rs.next() == false) {
					JOptionPane.showConfirmDialog(null, "잘못된 학기가 입력되었습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
			}

		} else if (e.getSource() == courseBtn) {
			model.setRowCount(0);

			String[] columnNames = { "lecture", "professor", "attendence_score", "midterm_score", "final_score",
					"extra_score", "total_score", "grade" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "학생 번호를 입력받으시오.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (year == null || semester == null) {
				JOptionPane.showMessageDialog(null, "연도/학기를 입력받으시오.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				stmt = con.createStatement();
				String query = "SELECT l.name, p.name, c.attendence_score, c.midterm_score, c.final_score, c.extra_score, c.total_score, c.grade FROM lecture l, professor p, course c WHERE l.id = c.lecture and p.id = c.professor and c.student = "
						+ id + " and c.year = " + year + " and c.semester = " + semester;
				rs = stmt.executeQuery(query);

				while (rs.next()) {
					String[] row = { rs.getString(1), rs.getString(2), Double.toString(rs.getDouble(3)),
							Double.toString(rs.getDouble(4)), Double.toString(rs.getDouble(5)),
							Double.toString(rs.getDouble(6)), Double.toString(rs.getDouble(7)), rs.getString(8) };
					model.addRow(row);
				}
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == addCourseBtn) { // 수강내역 신규 삽입 -> 신청 학점 10학점 / 등록금 오류
			JTextField lectureTf = new JTextField();

			Object[] message = { "lecture", lectureTf };

			int option = JOptionPane.showConfirmDialog(null, message, "수강내역 신규 삽입", JOptionPane.OK_CANCEL_OPTION);

			int sum = 0;

			if (option == JOptionPane.OK_OPTION) {
				String lecture = lectureTf.getText();

				if (sum <= 10) {
					try { // 수강신청 신규 삽입
						stmt = this.con.createStatement();
						String query = "INSERT INTO course (student, lecture, professor, year, semester) VALUES(" + id
								+ "," + lecture + "," + "(select professor from lecture where id = " + lecture + " )"
								+ "," + year + "," + semester + ")";
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

			int totaltuition = 0, payedtuition = 0;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT total_tuition FROM tuition WHERE student = " + id + " and year = " + year
						+ " and semester = " + semester);
				while (rs.next()) {
					int total = rs.getInt(1);
					totaltuition = total;
				}
			} catch (Exception e1) {
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT payed_tuition FROM tuition WHERE student = " + id + " and year = " + year
						+ " and semester = " + semester);
				while (rs.next()) {
					int payed = rs.getInt(1);
					payedtuition = payed;
				}

			} catch (Exception e1) {

				return;
			}
			if (totaltuition > payedtuition) {
				JOptionPane.showConfirmDialog(null, "등록금이 미납되었습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(
						"select sum(unit) from lecture where id in (select lecture from course where student = " + id
								+ " and year = " + year + " and semester = " + semester + " )");

				while (rs.next()) {
					int c = rs.getInt(1);
					sum += c;
				}
				if (sum > 10) {
					JOptionPane.showConfirmDialog(null, "이번 학기에 신청 학점이 10학점을 초과했습니다.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다. 적절한 값을 입력해주세요.", "ERROR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}

		} else if (e.getSource() == timeTableBtn) { // 현재 학기에 해당 학생이 수강하는 모든 과목을 시간표 형태로 출력
			model.setNumRows(0);

			String[] columnNames = { "교시", "월", "화", "수", "목", "금" };
			String[][] datas = new String[12][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "학생 번호를 입력받으시오.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (year == null || semester == null) {
				JOptionPane.showMessageDialog(null, "연도/학기를 입력받으시오.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				stmt = con.createStatement();
				String query = "SELECT day, period, course_time, name FROM lecture WHERE id in (SELECT lecture FROM course WHERE student = "
						+ id + " and year = " + year + " and semester = " + semester + " )";
				rs = stmt.executeQuery(query);

				while (rs.next()) {
					String day = rs.getString(1);
					int period = rs.getInt(2);
					int courseTime = rs.getInt(3);
					String lectureName = rs.getString(4);

					int dayNum = 0;

					if (day.equals("월")) {
						dayNum = 1;
					} else if (day.equals("화")) {
						dayNum = 2;
					} else if (day.equals("수")) {
						dayNum = 3;
					} else if (day.equals("목")) {
						dayNum = 4;
					} else if (day.equals("금")) {
						dayNum = 5;
					}

					for (int i = 0; i < 12; i++) {
						datas[i][0] = Integer.toString(i + 1);
					}

					for (int i = 0; i < courseTime; i++) {
						datas[period + i][dayNum] = lectureName;
					}
				}

				model.setDataVector(datas, columnNames);
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == clubBtn) { // 해당 학생이 소속된 동아리 정보 출력, 해당 학생이 동아리 회장인 경우, 동아리에 속한 모든 학생들의 정보 출력
			model.setRowCount(0);
			model2.setNumRows(0);

			String[] columnNames = { "id", "name", "student", "professor", "place", "count" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "학생 번호를 입력받으시오.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				stmt = con.createStatement();
				String query = "SELECT * FROM club WHERE id in (SELECT club FROM club_history WHERE student = " + id
						+ " )";
				rs = stmt.executeQuery(query);

				boolean isEmpty = true;
				while (rs.next()) {
					isEmpty = false;
					String member = "SELECT COUNT(*) FROM club_history WHERE club = " + Integer.toString(rs.getInt(1));
					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery(member);
					rs2.next();
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), Integer.toString(rs.getInt(3)),
							Integer.toString(rs.getInt(4)), rs.getString(5), Integer.toString(rs2.getInt(1)) };
					model.addRow(row);
				}

				if (isEmpty) {
					JOptionPane.showMessageDialog(null, "가입된 동아리가 없습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String st = "SELECT * FROM student WHERE id in (SELECT student FROM club_history WHERE club in (SELECT id FROM club WHERE student = "
						+ id + " ))";

				rs = stmt.executeQuery(st);

				isEmpty = true;
				while (rs.next()) {
					isEmpty = false;
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), Integer.toString(rs.getInt(6)), Integer.toString(rs.getInt(7)),
							rs.getString(8), Integer.toString(rs.getInt(9)) };
					model2.addRow(row);
				}

				if (isEmpty) {
					JOptionPane.showMessageDialog(null, "동아리 회장만 학생 정보를 조회할 수 있습니다.", "ERROR_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
				} else {
					content.add(scrollPane3);
				}
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else { // 성적표 버튼 과목 번호, 과목명, 취득 학점, 평점, GPA 출력
			model.setRowCount(0);

			String[] columnNames = { "lecture", "name", "unit", "grade", "GPA" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "학생 번호를 입력받으시오.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				stmt = con.createStatement();
				String query = "SELECT l.id, l.name, l.unit, c.grade FROM lecture l, course c WHERE c.student = " + id
						+ " and c.lecture = l.id";
				rs = stmt.executeQuery(query);

				int cnt = 0;
				double sum = 0.0;

				while (rs.next()) {
					cnt++;

					String grade;
					if (rs.getString(4) == null) {
						grade = "-";
					} else {
						grade = rs.getString(4);
					}
					
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), Integer.toString(rs.getInt(3)), grade
							 };
					model.addRow(row);

					double gpa = gradeToGPA(grade);
					if (gpa == -1.0) {
						cnt--;
					} else {
						sum += gpa;
					}
				}

				String gpa = Double.toString(sum / cnt);

				String[] gpaRow = { "N/A", "N/A", "N/A", "N/A", gpa };
				model.addRow(gpaRow);
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "입력 값에 문제가 있습니다.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}

		content.revalidate();
		content.repaint();
	}
}