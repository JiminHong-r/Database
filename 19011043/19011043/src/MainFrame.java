import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements ActionListener {
	JButton admin, professor, student;
	static Connection con;

	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public MainFrame() {
		super("학사정보 시스템");
		super.setLocationRelativeTo(null);
		conDB();
		layInit();

		setSize(400, 200);
		GridLayout grid = new GridLayout(1, 3);
		setLayout(grid);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void layInit() {
		admin = new JButton("관리자");
		professor = new JButton("교수");
		student = new JButton("학생");

		admin.addActionListener(this);
		professor.addActionListener(this);
		student.addActionListener(this);

		add(admin);
		add(professor);
		add(student);
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
		if (e.getSource() == admin) {
			new AdminFrame(con);
		} else if (e.getSource() == professor) {
			new ProfessorFrame(con);
		} else if (e.getSource() == student) {
			new StudentFrame(con);
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}