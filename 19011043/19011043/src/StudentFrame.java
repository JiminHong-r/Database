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
	String pattern = "^[0-9]*$"; // ���ڸ�

	public StudentFrame(Connection con) {
		super("�л� �޴�");
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
		table.setDefaultEditor(Object.class, null); // table ���� �Ұ��ϰ�
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		String[] columns2 = { "id", "name", "address", "phone", "email", "major", "professor", "tuition", "sub_major" };
		String[][] datas2 = new String[0][columns.length];
		model2 = new DefaultTableModel(datas2, columns2);
		table2 = new JTable(model2);
		table2.setDefaultEditor(Object.class, null); // table ���� �Ұ��ϰ�
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPane2 = new JScrollPane(table);
		scrollPane3 = new JScrollPane(table2);

		idBtn = new JButton("�л� ��ȣ �Է�");
		inputBtn = new JButton("����/�б� �Է�");
		timeTableBtn = new JButton("�ð�ǥ");
		courseBtn = new JButton("���� ����");
		addCourseBtn = new JButton("���� ���� �ű� ����");
		clubBtn = new JButton("���Ƹ�");
		gradeBtn = new JButton("����");

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

		if (e.getSource() == idBtn) { // �л� ���̵� �Է�

			JTextField sid = new JTextField();

			Object[] message = { "�л� ��ȣ", sid };

			int option = JOptionPane.showConfirmDialog(null, message, "�л� ��ȣ �Է�", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				id = sid.getText();

				if (Pattern.matches(pattern, id) == false) {
					JOptionPane.showMessageDialog(null, "�л� ��ȣ�� ���ڷ� �Է����ּ���.", "ERROR_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					JOptionPane.showConfirmDialog(null, "�л� ��ȣ�� �ԷµǾ����ϴ�.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT id FROM student WHERE id = " + id);
				if (rs.next() == false) {
					JOptionPane.showConfirmDialog(null, "�������� �ʴ� �л� ��ȣ�� �ԷµǾ����ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
			}
		} else if (e.getSource() == inputBtn) {
			JTextField iyear = new JTextField();
			JTextField isemester = new JTextField();

			Object[] message = { "����", iyear, "�б�", isemester, };

			int option = JOptionPane.showConfirmDialog(null, message, "����/�б� �Է�", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				year = iyear.getText();
				semester = isemester.getText();

				if (Pattern.matches(pattern, year) == false || Pattern.matches(pattern, semester) == false) {
					JOptionPane.showMessageDialog(null, "����/�б�� ���ڷ� �Է����ּ���.", "ERROR_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					JOptionPane.showConfirmDialog(null, "����/�бⰡ �ԷµǾ����ϴ�.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT year FROM course WHERE year = " + year);
				if (rs.next() == false) {
					JOptionPane.showConfirmDialog(null, "�߸��� ������ �ԷµǾ����ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT semester FROM course WHERE semester = " + semester);
				if (rs.next() == false) {
					JOptionPane.showConfirmDialog(null, "�߸��� �бⰡ �ԷµǾ����ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
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
				JOptionPane.showMessageDialog(null, "�л� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (year == null || semester == null) {
				JOptionPane.showMessageDialog(null, "����/�б⸦ �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == addCourseBtn) { // �������� �ű� ���� -> ��û ���� 10���� / ��ϱ� ����
			JTextField lectureTf = new JTextField();

			Object[] message = { "lecture", lectureTf };

			int option = JOptionPane.showConfirmDialog(null, message, "�������� �ű� ����", JOptionPane.OK_CANCEL_OPTION);

			int sum = 0;

			if (option == JOptionPane.OK_OPTION) {
				String lecture = lectureTf.getText();

				if (sum <= 10) {
					try { // ������û �ű� ����
						stmt = this.con.createStatement();
						String query = "INSERT INTO course (student, lecture, professor, year, semester) VALUES(" + id
								+ "," + lecture + "," + "(select professor from lecture where id = " + lecture + " )"
								+ "," + year + "," + semester + ")";
						stmt.executeUpdate(query);

					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�. ������ ���� �Է����ּ���.", "ERROR",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showConfirmDialog(null, "������ �Է��� �Ϸ�Ǿ����ϴ�.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
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
				JOptionPane.showConfirmDialog(null, "��ϱ��� �̳��Ǿ����ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
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
					JOptionPane.showConfirmDialog(null, "�̹� �б⿡ ��û ������ 10������ �ʰ��߽��ϴ�.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e1) {
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�. ������ ���� �Է����ּ���.", "ERROR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}

		} else if (e.getSource() == timeTableBtn) { // ���� �б⿡ �ش� �л��� �����ϴ� ��� ������ �ð�ǥ ���·� ���
			model.setNumRows(0);

			String[] columnNames = { "����", "��", "ȭ", "��", "��", "��" };
			String[][] datas = new String[12][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "�л� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (year == null || semester == null) {
				JOptionPane.showMessageDialog(null, "����/�б⸦ �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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

					if (day.equals("��")) {
						dayNum = 1;
					} else if (day.equals("ȭ")) {
						dayNum = 2;
					} else if (day.equals("��")) {
						dayNum = 3;
					} else if (day.equals("��")) {
						dayNum = 4;
					} else if (day.equals("��")) {
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
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == clubBtn) { // �ش� �л��� �Ҽӵ� ���Ƹ� ���� ���, �ش� �л��� ���Ƹ� ȸ���� ���, ���Ƹ��� ���� ��� �л����� ���� ���
			model.setRowCount(0);
			model2.setNumRows(0);

			String[] columnNames = { "id", "name", "student", "professor", "place", "count" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "�л� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "���Ե� ���Ƹ��� �����ϴ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "���Ƹ� ȸ�常 �л� ������ ��ȸ�� �� �ֽ��ϴ�.", "ERROR_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
				} else {
					content.add(scrollPane3);
				}
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else { // ����ǥ ��ư ���� ��ȣ, �����, ��� ����, ����, GPA ���
			model.setRowCount(0);

			String[] columnNames = { "lecture", "name", "unit", "grade", "GPA" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "�л� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}

		content.revalidate();
		content.repaint();
	}
}