public class DatabaseReset {
	static public String[] queries = {
			"DROP DATABASE IF EXISTS madang;\r\n",
			"CREATE DATABASE madang;\r\n",
			"USE madang;\r\n",
			"CREATE TABLE Doctors\r\n"
			+ "(\r\n"
			+ "	doc_id INTEGER NOT NULL,\r\n"
			+ " major_treat VARCHAR(25) NOT NULL,\r\n"
			+ "    doc_name VARCHAR(20) NOT NULL,\r\n"
			+ "    doc_gen char(1) NOT NULL,\r\n"
			+ "    doc_phone VARCHAR(15) NOT NULL,\r\n"
			+ "    doc_email VARCHAR(50) UNIQUE,\r\n"
			+ "    doc_position VARCHAR(20) NOT NULL\r\n"
			+ ");",
			"ALTER TABLE Doctors\r\n" +
			"	ADD CONSTRAINT doc_id_pk PRIMARY KEY (doc_id);\r\n",
			"CREATE TABLE Nurses\r\n"
			+ "(\r\n"
			+ "	nur_id INTEGER NOT NULL,\r\n"
			+ "    major_job VARCHAR(25) NOT NULL,\r\n"
			+ "    nur_name VARCHAR(20) NOT NULL,\r\n"
			+ "    nur_gen char(1) NOT NULL,\r\n"
			+ "    nur_phone VARCHAR(15) NOT NULL,\r\n"
			+ "    nur_email VARCHAR(50) UNIQUE,\r\n"
			+ "    nur_position VARCHAR(20) NOT NULL\r\n"
			+ ");",
			"ALTER TABLE Nurses\r\n"
			+ "	ADD CONSTRAINT nur_id_pk PRIMARY KEY (nur_id);\r\n",
			"CREATE TABLE Patients\r\n"
			+ "(\r\n"
			+ "	pat_id INTEGER NOT NULL,\r\n"
			+ "    nur_id INTEGER NOT NULL,\r\n"
			+ "    doc_id INTEGER NOT NULL,\r\n"
			+ "    pat_name VARCHAR(20) NOT NULL,\r\n"
			+ "    pat_gen char(1) NOT NULL,\r\n"
			+ "    pat_jumin VARCHAR(14) NOT NULL,\r\n"
			+ "    pat_addr VARCHAR(100) NOT NULL,\r\n"
			+ "    pat_phone VARCHAR(15) NULL,\r\n"
			+ "    pat_email VARCHAR(50) UNIQUE,\r\n"
			+ "    pat_job VARCHAR(20) NOT NULL\r\n"
			+ ");",
			"ALTER TABLE Patients\r\n"
			+ "	ADD CONSTRAINT pat_id_pk PRIMARY KEY (pat_id);\r\n",
			"ALTER TABLE Patients\r\n"
			+ "	ADD (CONSTRAINT R_2 FOREIGN KEY (doc_id) REFERENCES Doctors (doc_id));\r\n",
			"CREATE TABLE Treatments\r\n"
			+ "(\r\n"
			+ "	treat_id INTEGER NOT NULL,\r\n"
			+ "    pat_id INTEGER NOT NULL,\r\n"
			+ "    doc_id INTEGER NOT NULL,\r\n"
			+ "    treat_contents VARCHAR(1000) NOT NULL,\r\n"
			+ "    treat_date DATE NOT NULL\r\n"
			+ ");",
			"ALTER TABLE Treatments\r\n"
			+ "	ADD CONSTRAINT treat_pat_doc_id_pk PRIMARY KEY (treat_id, pat_id, doc_id);\r\n",
			"ALTER TABLE Treatments\r\n"
			+ "	ADD (CONSTRAINT R_5 FOREIGN KEY (pat_id) REFERENCES Patients (pat_id));\r\n",
			"ALTER TABLE Treatments\r\n"
			+ "	ADD (CONSTRAINT R_6 FOREIGN KEY (doc_id) REFERENCES Doctors (doc_id));\r\n",
			"CREATE TABLE Charts\r\n"
			+ "(\r\n"
			+ "	chart_id VARCHAR(20) NOT NULL,\r\n"
			+ "	treat_id INTEGER NOT NULL,\r\n"
			+ "    doc_id INTEGER NOT NULL,\r\n"
			+ "	pat_id INTEGER NOT NULL,\r\n"
			+ "    nur_id INTEGER NOT NULL,\r\n"
			+ "    chart_contents VARCHAR(1000) NOT NULL\r\n"
			+ ");",
			"ALTER TABLE Charts\r\n"
			+ "	ADD CONSTRAINT chart_treat_doc_pat_id_pk PRIMARY KEY (chart_id, treat_id, doc_id, pat_id);\r\n",
			"ALTER TABLE Charts\r\n"
			+ "	ADD (CONSTRAINT R_4 FOREIGN KEY (nur_id) REFERENCES Nurses (nur_id));\r\n",
			"ALTER TABLE Charts\r\n"
			+ "	ADD (CONSTRAINT R_7 FOREIGN KEY (treat_id, pat_id, doc_id) REFERENCES Treatments (treat_id, pat_id, doc_id));\r\n",
			"INSERT INTO Doctors VALUES(980312, '�Ҿư�', '������', 'M', '010-333-1240', 'ltj@hanbh.com', '����');\r\n",
			"INSERT INTO Doctors VALUES(000601, '����', '�ȼ���', 'M', '011-222-0987', 'ask@hanbh.com', '����');\r\n",
			"INSERT INTO Doctors VALUES(001208, '�ܰ�', '�����', 'M', '010-333-8743', 'kmj@hanbh.com', '����');\r\n",
			"INSERT INTO Doctors VALUES(020403, '�Ǻΰ�', '���¼�', 'M', '019-777-3764', 'lts@hanbh.com', '����');\r\n",
			"INSERT INTO Doctors VALUES(050900, '�Ҿư�', '�迬��', 'F', '010-555-3746', 'kya@hanbh.com', '������');\r\n",
			"INSERT INTO Doctors VALUES(050101, '����', '������', 'M', '011-222-7643', 'cth@hanbh.com', '������');\r\n",
			"INSERT INTO Doctors VALUES(062019, '�Ҿư�', '������', 'F', '010-999-1265', 'jjh@hanbh.com', '������');\r\n",
			"INSERT INTO Doctors VALUES(070576, '�Ǻΰ�', 'ȫ�浿', 'M', '016-333-7263', 'hgd@hanbh.com', '������');\r\n",
			"INSERT INTO Doctors VALUES(080543, '��缱��', '���缮', 'M', '010-222-1263', 'yjs@hanbh.com', '����');\r\n",
			"INSERT INTO Doctors VALUES(091001, '�ܰ�', '�躴��', 'M', '010-555-3542', 'kbm@hanbh.com', '������');\r\n",
			"INSERT INTO Doctors VALUES(070586, '����', '���ǻ�', 'M', '010-111-3542', 'kys@hanbh.com', '������');",
			"INSERT INTO Doctors VALUES(050111, '����', '���ǻ�', 'M', '010-222-3542', 'pys@hanbh.com', '������');",
			"INSERT INTO Doctors VALUES(091005, '�Ǻΰ�', '���ǻ�', 'F', '010-333-3542', 'cys@hanbh.com', '����');",
			"INSERT INTO Doctors VALUES(091004, '�ܰ�', '���ǻ�', 'M', '010-444-3542', 'sys@hanbh.com', '������');",
			"INSERT INTO Doctors VALUES(062017, '�ܰ�', '���ǻ�', 'M', '010-555-1231', 'yys@hanbh.com', '����');",
			"INSERT INTO Nurses VALUES(050302, '�Ҿư�', '������', 'F', '010-555-8751', 'key@hanbh.com', '����ȣ��');\r\n",
			"INSERT INTO Nurses VALUES(050021, '����', '������', 'F', '016-333-8745', 'ysa@hanbh.com', '����ȣ��');\r\n",
			"INSERT INTO Nurses VALUES(040089, '�Ǻΰ�', '������', 'M', '010-666-7646', 'sjw@hanbh.com', '����');\r\n",
			"INSERT INTO Nurses VALUES(070605, '��缱��', '����ȭ', 'F', '010-333-4588', 'yjh@hanbh.com', '����');\r\n",
			"INSERT INTO Nurses VALUES(070804, '����', '���ϳ�', 'F', '010-222-1340', 'nhn@hanbh.com', '����');\r\n",
			"INSERT INTO Nurses VALUES(071018, '�Ҿư�', '��ȭ��', 'F', '019-888-4116', 'khk@hanbh.com', '����');\r\n",
			"INSERT INTO Nurses VALUES(100356, '�Ҿư�', '�̼���', 'M', '010-777-1234', 'lsy@hanbh.com', '��ȣ��');\r\n",
			"INSERT INTO Nurses VALUES(104145, '�ܰ�', '����', 'M', '010-999-8520', 'kh@hanbh.com', '��ȣ��');\r\n",
			"INSERT INTO Nurses VALUES(120309, '�Ǻΰ�', '�ڼ���', 'M', '010-777-4996', 'psw@hanbh.com', '��ȣ��');\r\n",
			"INSERT INTO Nurses VALUES(130211, '�ܰ�', '�̼���', 'F', '010-222-3214', 'lsy2@hanbh.com', '��ȣ��');\r\n",
			"INSERT INTO Nurses VALUES(120300, '����', '�谣ȣ', 'F', '010-111-1234', 'kkh@hanbh.com', '��ȣ��');",
			"INSERT INTO Nurses VALUES(070805, '����', '�ڰ�ȣ', 'M', '010-222-1234', 'pkh@hanbh.com', '����');",
			"INSERT INTO Nurses VALUES(130213, '�Ǻΰ�', '�ְ�ȣ', 'M', '010-333-1234', 'ckh@hanbh.com', '����ȣ��');", 
			"INSERT INTO Nurses VALUES(130214, '�ܰ�', '�۰�ȣ', 'F', '010-444-1234', 'skh@hanbh.com', '��ȣ��');",
			"INSERT INTO Nurses VALUES(071017, '�ܰ�', '����ȣ', 'F', '010-555-1234', 'ykh@hanbh.com', '��ȣ��');",
			"INSERT INTO Patients VALUES(2345, 050302, 980312, '�Ȼ��', 'M', '232345', '����', '010-555-7845', 'ask@hanbh.com', 'ȸ���');\r\n",
			"INSERT INTO Patients VALUES(3545, 040089, 020403, '�輺��', 'M', '543545', '����', '010-333-7812', 'ksr@hanbh.com', '�ڿ���');\r\n",
			"INSERT INTO Patients VALUES(3424, 070605, 080543, '������', 'M', '433424', '�λ�', '019-888-4859', 'ljj@hanbh.com', 'ȸ���');\r\n",
			"INSERT INTO Patients VALUES(7675, 100356, 050900, '�ֱ���', 'M', '677675', '����', '010-222-4847', 'cks@hanbh.com', 'ȸ���');\r\n",
			"INSERT INTO Patients VALUES(4533, 070804, 000601, '���Ѱ�', 'M', '744533', '����', '010-777-9630', 'jhk@hanbh.com', '����');\r\n",
			"INSERT INTO Patients VALUES(5546, 120309, 070576, '������', 'M', '765546', '�뱸', '016-777-0214', 'ywh@hanbh.com', '�ڿ���');\r\n",
			"INSERT INTO Patients VALUES(4543, 070804, 050101, '������', 'M', '454543', '�λ�', '010-555-4187', 'cjj@hanbh.com', 'ȸ���');\r\n",
			"INSERT INTO Patients VALUES(9768, 130211, 091001, '������', 'F', '119768', '����', '010-888-3675', 'ljh@hanbh.com', '����');\r\n",
			"INSERT INTO Patients VALUES(4234, 130211, 091001, '������', 'F', '234234', '����', '010-999-6541', 'onm@hanbh.com', '�л�');\r\n",
			"INSERT INTO Patients VALUES(7643, 071018, 062019, '�ۼ���', 'M', '987643', '����', '010-222-5874', 'ssm@hanbh.com', '�л�');\r\n",
			"INSERT INTO Patients VALUES(5541, 120300, 070586, '��ȯ��', 'F', '111111', '����', '010-111-1111', 'khj@hanbh.com', 'ȸ���');",
			"INSERT INTO Patients VALUES(4547, 070805, 050111, '��ȯ��', 'F', '222222', '�λ�', '010-222-2222', 'phj@hanbh.com', '�л�');",
			"INSERT INTO Patients VALUES(9763, 130213, 091005, '��ȯ��', 'F', '333333', '����', '010-333-3333', 'chj@hanbh.com', '����');",
			"INSERT INTO Patients VALUES(4238, 130214, 091004, '��ȯ��', 'M', '444444', '����', '010-444-4444', 'shj@hanbh.com', 'ȸ���');",
			"INSERT INTO Patients VALUES(7646, 071017, 062017, '��ȯ��', 'F', '555555', '����', '010-555-5555', 'yhj@hanbh.com', '�л�');",
			"INSERT INTO Treatments VALUES(130516023, 2345, 980312, '����, ����', STR_TO_DATE('2013-05-16','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(130628100, 3545, 020403, '�Ǻ� Ʈ���� ġ��', STR_TO_DATE('2013-06-28','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(131205056, 3424, 080543, '�� ��ũ�� MRI �Կ�', STR_TO_DATE('2013-12-05','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(131218024, 7675, 050900, '���̿�',  STR_TO_DATE('2013-12-18','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(131224012, 4533, 000601, '�忰', STR_TO_DATE('2013-12-24','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(140103001, 5546, 070576, '���帧 ġ��', STR_TO_DATE('2014-01-03','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(140109026, 4543, 050101, '����', STR_TO_DATE('2014-01-09','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(140226102, 9768, 091001, 'ȭ��ġ��', STR_TO_DATE('2014-02-26','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(140303003, 4234, 091001, '������ �ܻ�ġ��', STR_TO_DATE('2014-03-03','%Y-%m-%d'));\r\n",
			"INSERT INTO Treatments VALUES(140103003, 5541, 070586, '����', STR_TO_DATE('2014-04-07','%Y-%m-%d'));",
			"INSERT INTO Treatments VALUES(140109027, 4547, 050111, '�峻����', STR_TO_DATE('2014-05-07','%Y-%m-%d'));",
			"INSERT INTO Treatments VALUES(140226109, 9763, 091005, 'ȭ�� ������ ġ��', STR_TO_DATE('2014-06-07','%Y-%m-%d'));",
			"INSERT INTO Treatments VALUES(140303006, 4238, 091004, '������', STR_TO_DATE('2014-07-07','%Y-%m-%d'));",
			"INSERT INTO Treatments VALUES(140308085, 7646, 062017, '�忰', STR_TO_DATE('2014-08-07','%Y-%m-%d'));",
			"INSERT INTO Treatments VALUES(140308087, 7643, 062019, '�忰', STR_TO_DATE('2014-03-08','%Y-%m-%d'));\r\n",
			"INSERT INTO Charts VALUES('p_130516023', 130516023, 980312, 2345, 050302,'���� �ֻ� �� �� ó��'); \r\n",
			"INSERT INTO Charts VALUES('d_130628100', 130628100, 020403, 3545, 040089, '�Ǻ� ���� ���� �ֻ�'); \r\n",
			"INSERT INTO Charts VALUES('r_131205056', 131205056, 080543, 3424, 070605, '�ֻ� ó��'); \r\n",
			"INSERT INTO Charts VALUES('p_131218024', 131218024, 050900, 7675, 100356, '�Ӽ� û�� �� �� ó��'); \r\n",
			"INSERT INTO Charts VALUES('i_131224012', 131224012, 000601, 4533, 070804, '�忰 �Կ�ġ��'); \r\n",
			"INSERT INTO Charts VALUES('d_140103001', 140103001, 070576, 5546, 120309,'���帧 ġ��� ó��'); \r\n",
			"INSERT INTO Charts VALUES('i_140109026', 140109026, 050101, 4543, 070804, '�����ð�'); \r\n",
			"INSERT INTO Charts VALUES('s_140226102', 140226102, 091001, 9768, 130211, 'ȭ�� ũ���� ó��'); \r\n",
			"INSERT INTO Charts VALUES('s_140303003', 140303003, 091001, 4234, 130211, '�Կ�ġ��');",
			"INSERT INTO Charts VALUES('p_140308087', 140308087, 062019, 7643, 071018, '�忰 �Կ�ġ��');",
			"INSERT INTO Charts VALUES('a_140103003', 140103003, 070586, 5541, 120300, '������ ó��');",
			"INSERT INTO Charts VALUES('b_140109027', 140109027, 050111, 4547, 070805, '�峻�ð�');",
			"INSERT INTO Charts VALUES('c_140226109', 140226109, 091005, 9763, 130213, 'ȭ�� ������ ġ��');", 
			"INSERT INTO Charts VALUES('d_140303006', 140303006, 091004, 4238, 130214, '�Կ�ġ��');",
			"INSERT INTO Charts VALUES('e_140308085', 140308085, 062017, 7646, 071017, '�忰 �Կ�ġ��');"
	};
}
