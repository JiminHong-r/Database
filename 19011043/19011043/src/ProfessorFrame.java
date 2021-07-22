import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProfessorFrame extends JFrame implements ActionListener {
	JPanel header, content;
	JButton idBtn, inputBtn, lectureBtn, studentBtn, majorBtn, courseBtn, gradeBtn;
	JScrollPane scrollPane2, scrollPane3;
	JTable table, table2;

	int nRow, nColumn;
	Object cValue;

	DefaultTableModel model = null;
	DefaultTableModel model2 = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	String id = null, year = null, semester = null;
	String pattern = "^[0-9]*$"; // ���ڸ�

	MouseAdapter lectureTableEvent = null;
	MouseAdapter studentTableEvent = null;

	public ProfessorFrame(Connection con) {
		super("���� �޴�");
		super.setLocationRelativeTo(null);
		this.con = con;
		layInit();

		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

		lectureTableEvent = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.removeMouseListener(lectureTableEvent);

				table = (JTable) e.getComponent();
				model = (DefaultTableModel) table.getModel();

				nRow = table.getSelectedRow();
				cValue = model.getValueAt(nRow, 0); // ��

				String lecid = cValue.toString();

				model.setNumRows(0);

				String[] columnNames = { "student", "name", "address", "phone", "email", "major", "professor",
						"tuition", "sub_major" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(
							"select * from student where id in (select student from course where lecture = " + lecid
									+ " )");

					while (rs.next()) {
						String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), Integer.toString(rs.getInt(6)),
								Integer.toString(rs.getInt(7)), rs.getString(8), Integer.toString(rs.getInt(9)) };
						model.addRow(row);
					}
				} catch (Exception e2) {
					System.out.println("���� �б� ���� :" + e2);
				}
			}
		};

		studentTableEvent = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.removeMouseListener(studentTableEvent);

				table = (JTable) e.getComponent();
				model = (DefaultTableModel) table.getModel();

				nRow = table.getSelectedRow();
				cValue = model.getValueAt(nRow, 0); // ��

				String lecid = cValue.toString();

				model.setNumRows(0);

				String[] columnNames = { "lecture", "attendence_score", "midterm_score", "final_score", "extra_score",
						"total_score", "grade" };
				String[][] datas = new String[0][columnNames.length];

				model.setDataVector(datas, columnNames);

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(
							"select lecture, attendence_score, midterm_score, final_score, extra_score, total_score, grade from course where student="
									+ lecid);

					while (rs.next()) {
						String[] row = { Integer.toString(1), Double.toString(rs.getDouble(2)),
								Double.toString(rs.getDouble(3)), Double.toString(rs.getDouble(4)),
								Double.toString(rs.getDouble(5)), Double.toString(rs.getDouble(6)), rs.getString(7) };
						model.addRow(row);
					}
				} catch (Exception e2) {
					System.out.println("���� �б� ���� :" + e2);
				}
			}
		};
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

		String[] columns2 = { "id", "name", "address", "phone", "email" };
		String[][] datas2 = new String[0][columns.length];
		model2 = new DefaultTableModel(datas2, columns2);
		table2 = new JTable(model2);
		table2.setDefaultEditor(Object.class, null); // table ���� �Ұ��ϰ�
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPane2 = new JScrollPane(table);
		scrollPane3 = new JScrollPane(table2);

		idBtn = new JButton("���� ��ȣ �Է�");
		inputBtn = new JButton("����/�б� �Է�");
		lectureBtn = new JButton("��� ���� ��ȸ");
		studentBtn = new JButton("���� �л�");
		majorBtn = new JButton("�Ҽ� �а�");
		courseBtn = new JButton("���ǽð�ǥ");
		gradeBtn = new JButton("���� �Է�");

		header.add(idBtn);
		header.add(inputBtn);
		header.add(lectureBtn);
		header.add(studentBtn);
		header.add(majorBtn);
		header.add(courseBtn);
		header.add(gradeBtn);

		content.add(scrollPane2);

		idBtn.addActionListener(this);
		inputBtn.addActionListener(this);
		lectureBtn.addActionListener(this);
		studentBtn.addActionListener(this);
		majorBtn.addActionListener(this);
		courseBtn.addActionListener(this);
		gradeBtn.addActionListener(this);

		add("North", header);
		add("Center", content);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		content.removeAll();
		content.add(scrollPane2);

		model.setNumRows(0);

		if (table.getMouseListeners().length > 0) {
			table.removeMouseListener(lectureTableEvent);
			table.removeMouseListener(studentTableEvent);
		}

		if (e.getSource() == idBtn) { // ���� ���̵� �Է�
			JTextField sid = new JTextField();

			Object[] message = { "���� ��ȣ", sid };

			int option = JOptionPane.showConfirmDialog(null, message, "���� ��ȣ �Է�", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				id = sid.getText();

				if (Pattern.matches(pattern, id) == false) {
					JOptionPane.showMessageDialog(null, "�ùٸ� �Է°��� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT id FROM student WHERE id = " + id);
				if (rs.next() == false) {
					JOptionPane.showConfirmDialog(null, "�������� �ʴ� ���� ��ȣ�� �ԷµǾ����ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(null, "���� ��ȣ�� �ԷµǾ����ϴ�.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "�ùٸ� �Է°��� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					JOptionPane.showConfirmDialog(null, "����/�бⰡ �ԷµǾ����ϴ�.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getSource() == lectureBtn) {
			table.addMouseListener(lectureTableEvent);

			model.setNumRows(0);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "���� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (year == null || semester == null) {
				JOptionPane.showMessageDialog(null, "����/�б⸦ �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String[] columnNames = { "lecture", "day", "period", "unit", "course_time", "major" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			try {
				stmt = this.con.createStatement();
				rs = stmt.executeQuery(
						"SELECT c.lecture, l.day, l.period, l.unit, l.course_time, l.major FROM lecture l, course c WHERE c.professor = "
								+ id + " and c.lecture = l.id and c.year = " + year + " and c.semester = " + semester);

				while (rs.next()) {
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), Integer.toString(rs.getInt(3)),
							Integer.toString(rs.getInt(4)), Integer.toString(rs.getInt(5)),
							Integer.toString(rs.getInt(6)) };
					model.addRow(row);
				}

			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == studentBtn) { // ���� ������ �����ϴ� �л��� ���� ���� ���
			table.addMouseListener(studentTableEvent);

			model.setNumRows(0);

			String[] columnNames = { "student", "name", "address", "phone", "email", "major", "professor", "tuition",
					"sub_major" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			try {
				stmt = this.con.createStatement();
				rs = stmt.executeQuery(
						"SELECT * FROM student WHERE id in (SELECT student FROM relationship WHERE professor = " + id
								+ " )");

				if (id == null) {
					JOptionPane.showMessageDialog(null, "���� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					return;
				}

				while (rs.next()) {
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), Integer.toString(rs.getInt(6)), Integer.toString(rs.getInt(7)),
							rs.getString(8), Integer.toString(rs.getInt(9)) };
					model.addRow(row);

				}

				// �л� ���� �� �ϳ� Ŭ�� �� �ش� �л��� �����߰ų� �����ϰ� �ִ� ��� ���� ���� ���� ����

			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == majorBtn) { // �а��� ���� ���� �Ҽ� �а��� ��� ���� ���
			model.setRowCount(0);
			model2.setNumRows(0);

			content.add(scrollPane3);

			String[] columnNames = { "MAJORID", "NAME", "PHONE", "PLACE", "MAINPROFESSOR" };
			String[][] datas = new String[0][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "���� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(
						"SELECT * FROM major WHERE id in (SELECT major FROM professor_major WHERE professor = " + id
								+ " )");

				while (rs.next()) {
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4),
							Integer.toString(rs.getInt(5)) };
					model.addRow(row);
				}

				rs = stmt.executeQuery(
						"select * from professor where id in (select professor from major where id in (select major from professor_major where professor = "
								+ id + " ))");

				while (rs.next()) {
					String[] row = { Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5) };
					model2.addRow(row);
				}
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�.", "ERROR", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == courseBtn) { // ���� �б��� ���� �ð�ǥ ��ư
			model.setNumRows(0);

			String[] columnNames = { "����", "��", "ȭ", "��", "��", "��" };
			String[][] datas = new String[12][columnNames.length];

			model.setDataVector(datas, columnNames);

			if (id == null) {
				JOptionPane.showMessageDialog(null, "���� ��ȣ�� �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (year == null || semester == null) {
				JOptionPane.showMessageDialog(null, "����/�б⸦ �Է¹����ÿ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				stmt = con.createStatement();
				String query = "SELECT day, period, course_time, name FROM lecture WHERE id in (SELECT lecture FROM course WHERE professor = "
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

		} else { // �ش� ������ �����ϴ� ���� ���� ���� �Է� ��ư
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

			Object[] message = { "student", studentTf, "lecture", lectureTf, "professor",
					professorTf, "year", yearTf, "semester", semesterTf, "attendence_score", attendenceScoreTf,
					"midterm_score", midtermScoreTf, "final_score", finalScoreTf, "extra_score", extraScoreTf,
					"grade", gradeTf };

			int option = JOptionPane.showConfirmDialog(null, message, "���� �Է�",
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
					String query = "UPDATE course SET " + "student=" + student + ", lecture=" + lecture
							+ ", professor=" + professor + ", year=" + year + ", semester=" + semester
							+ ", attendence_score=" + attendenceScore + ", midterm_score=" + midtermScore
							+ ", final_score=" + finalScore + ", extra_score=" + extraScore + ", grade=\"" + grade
							+ "\"";
					stmt.executeUpdate(query);
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "�Է� ���� ������ �ֽ��ϴ�. ������ ���� �Է����ּ���.", "ERROR",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showConfirmDialog(null, "���� �Է��� �Ϸ�Ǿ����ϴ�.", "COMPLETE", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		revalidate();
		repaint();
	}
}