public class DatabaseInit {
    static public String[] queries = {
        "DROP DATABASE IF EXISTS madang;",
        "CREATE DATABASE madang;", 
        "USE madang;",
        "CREATE TABLE professor (" + 
                "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(40)," +
                "address VARCHAR(100)," +
                "phone VARCHAR(20)," +
                "email VARCHAR(100)" +
        ");",
        "CREATE TABLE major (" + 
                "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100)," +
                "phone VARCHAR(20)," +
                "place VARCHAR(100)," +
                "professor INTEGER NOT NULL," +
                "FOREIGN KEY (professor) REFERENCES professor(id)" +
        ");",
        "CREATE TABLE professor_major (" + 
                "professor INTEGER," +
                "major INTEGER," +
                "FOREIGN KEY (professor) REFERENCES professor(id)," +
                "FOREIGN KEY (major) REFERENCES major(id)" +
        ");",
        "CREATE TABlE lecture (" + 
                "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "class_no INTEGER," +
                "professor INTEGER," +
                "name VARCHAR(100)," +
                "day VARCHAR(20)," +
                "period INTEGER," +
                "unit INTEGER," +
                "course_time INTEGER," +
                "major INTEGER," +
                "place VARCHAR(100)," +
                "FOREIGN KEY (professor) REFERENCES professor(id)," +
                "FOREIGN KEY (major) REFERENCES major(id)" +
        ");",
        "CREATE TABLE student (" + 
                "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(40)," +
                "address VARCHAR(100)," +
                "phone VARCHAR(20)," +
                "email VARCHAR(100)," +
                "major INTEGER," +
                "professor INTEGER," +
                "tuition_address VARCHAR(20)," +
                "sub_major INTEGER," +
                "FOREIGN KEY (major) REFERENCES major(id)," +
                "FOREIGN KEY (sub_major) REFERENCES major(id)," +
                "FOREIGN KEY (professor) REFERENCES professor(id)" +
        ");",
        "CREATE TABLE tuition (" + 
                "student INTEGER," +
                "year INTEGER," +
                "semester INTEGER," +
                "total_tuition INTEGER," +
                "payed_tuition INTEGER," +
                "payed_date DATE," +
                "FOREIGN KEY (student) REFERENCES student(id)" +
        ");",
        "CREATE TABLE club (" + 
                "id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50)," +
                "student INTEGER," +
                "professor INTEGER," +
                "place VARCHAR(50)," +
                "FOREIGN KEY (student) REFERENCES student(id)," +
                "FOREIGN KEY (professor) REFERENCES professor(id)" +
        ");",
        "CREATE TABLE club_history (" + 
                "student INTEGER," +
                "club INTEGER," +
                "FOREIGN KEY (student) REFERENCES student(id)," +
                "FOREIGN KEY (club) REFERENCES club(id)" +
        ");",
        "CREATE TABLE relationship (" + 
                "student INTEGER PRIMARY KEY," +
                "professor INTEGER," +
                "year INTEGER," +
                "semester INTEGER," +
                "FOREIGN KEY (student) REFERENCES student(id)," +
                "FOREIGN KEY (professor) REFERENCES professor(id)" +
        ");",
        "CREATE TABLE course (" +
                "student INTEGER," +
                "lecture INTEGER," +
                "professor INTEGER," +
                "year INTEGER," +
                "semester INTEGER," +
                "attendence_score DOUBLE," +
                "midterm_score DOUBLE," +
                "final_score DOUBLE," +
                "extra_score DOUBLE," +
                "total_score DOUBLE," +
                "grade VARCHAR(10), " +
                "FOREIGN KEY (student) REFERENCES student(id)," +
                "FOREIGN KEY (lecture) REFERENCES lecture(id)," +
                "FOREIGN KEY (professor) REFERENCES professor(id)" +
        ");",
        "INSERT INTO professor VALUES(1, 'professora', '서울시 마포구', '010-0000-0000', 'aa@gmail.com');",
        "INSERT INTO professor VALUES(2, 'professorb', '서울시 강남구', '010-1111-1111', 'bb@gmail.com');",
        "INSERT INTO professor VALUES(3, 'professorc', '서울시 강동구', '010-2222-2222', 'cc@gmail.com');",
        "INSERT INTO professor VALUES(4, 'professord', '서울시 강서구', '010-3333-3333', 'dd@gmail.com');",
        "INSERT INTO professor VALUES(5, 'professore', '서울시 관악구', '010-4444-4444', 'ee@gmail.com');",
        "INSERT INTO professor VALUES(6, 'professorf', '서울시 광진구', '010-5555-5555', 'ff@gmail.com');",
        "INSERT INTO professor VALUES(7, 'professorg', '서울시 구로구', '010-6666-6666', 'gg@gmail.com');",
        "INSERT INTO professor VALUES(8, 'professorh', '서울시 금천구', '010-7777-7777', 'hhe@gmail.com');",
        "INSERT INTO professor VALUES(9, 'professori', '서울시 관악구', '010-8888-8888', 'ii@gmail.com');",
        "INSERT INTO professor VALUES(10, 'professorj', '서울시 노원구', '010-9999-9999', 'jj@gmail.com');",
        "INSERT INTO professor VALUES(11, 'professork', '서울시 도봉구', '010-1010-1010', 'kk@gmail.com');",
        "INSERT INTO professor VALUES(12, 'professorl', '서울시 동대문구', '010-1212-1212', 'll@gmail.com');",
        "INSERT INTO professor VALUES(13, 'professorm', '서울시 동작구', '010-1313-1313', 'mm@gmail.com');",
        "INSERT INTO professor VALUES(14, 'professorn', '서울시 서대문구', '010-1414-1414', 'nn@gmail.com');",
        "INSERT INTO professor VALUES(15, 'professoro', '서울시 서초구', '010-1515-1515', 'oo@gmail.com');",
        "INSERT INTO professor VALUES(16, 'professorp', '서울시 성동구', '010-1616-1616', 'pp@gmail.com');",
        "INSERT INTO professor VALUES(17, 'professorq', '서울시 성북구', '010-1717-1717', 'qq@gmail.com');",
        "INSERT INTO professor VALUES(18, 'professorr', '서울시 송파구', '010-1818-1818', 'rr@gmail.com');",
        "INSERT INTO professor VALUES(19, 'professors', '서울시 양천구', '010-1919-1919', 'ss@gmail.com');",
        "INSERT INTO professor VALUES(20, 'professort', '서울시 영등포구', '010-2020-2020', 'tt@gmail.com');",
        "INSERT INTO professor VALUES(21, 'professoru', '서울시 용산구', '010-2121-2121', 'uu@gmail.com');",
        "INSERT INTO professor VALUES(22, 'professorv', '서울시 은평구', '010-2323-2323', 'vv@gmail.com');",
        "INSERT INTO professor VALUES(23, 'professorw', '서울시 종로구', '010-2424-2424', 'ww@gmail.com');",
        "INSERT INTO professor VALUES(24, 'professorx', '서울시 중랑구', '010-2525-2525', 'xx@gmail.com');",
        "INSERT INTO professor VALUES(25, 'professory', '서울시 중구', '010-2626-2626', 'yy@gmail.com');",
        "INSERT INTO major VALUES(1, 'majora', '010-0000-0000', 'aa', 1);",
        "INSERT INTO major VALUES(2, 'majorb', '010-1111-1111', 'bb', 2);",
        "INSERT INTO major VALUES(3, 'majorc', '010-2222-2222', 'cc', 3);",
        "INSERT INTO major VALUES(4, 'majord', '010-3333-3333', 'dd', 4);",
        "INSERT INTO major VALUES(5, 'majore', '010-4444-4444', 'ee', 5);",
        "INSERT INTO major VALUES(6, 'majorf', '010-5555-5555', 'ff', 6);",
        "INSERT INTO major VALUES(7, 'majorg', '010-6666-6666', 'gg', 7);",
        "INSERT INTO major VALUES(8, 'majorh', '010-7777-7777', 'hh', 8);",
        "INSERT INTO major VALUES(9, 'majori', '010-8888-8888', 'ii', 9);",
        "INSERT INTO major VALUES(10, 'majorj', '010-9999-9999', 'jj', 10);",
        "INSERT INTO major VALUES(11, 'majork', '010-1010-1010', 'kk', 11);",
        "INSERT INTO major VALUES(12, 'majorl', '010-1212-1212', 'll', 12);",
        "INSERT INTO major VALUES(13, 'majorm', '010-1313-1313', 'mm', 13);",
        "INSERT INTO major VALUES(14, 'majorn', '010-1414-1414', 'nn', 14);",
        "INSERT INTO major VALUES(15, 'majoro', '010-1515-1515', 'oo', 15);",
        "INSERT INTO major VALUES(16, 'majorp', '010-1616-1616', 'pp', 16);",
        "INSERT INTO major VALUES(17, 'majorq', '010-1717-1717', 'qq', 17);",
        "INSERT INTO major VALUES(18, 'majorr', '010-1818-1818', 'rr', 18);",
        "INSERT INTO major VALUES(19, 'majors', '010-1919-1919', 'ss', 19);",
        "INSERT INTO major VALUES(20, 'majort', '010-2020-2020', 'tt', 20);",
        "INSERT INTO major VALUES(21, 'majoru', '010-2121-2121', 'uu', 21);",
        "INSERT INTO major VALUES(22, 'majorv', '010-2323-2323', 'vv', 22);",
        "INSERT INTO major VALUES(23, 'majorw', '010-2424-2424', 'ww', 23);",
        "INSERT INTO major VALUES(24, 'majorx', '010-2525-2525', 'xx', 24);",
        "INSERT INTO major VALUES(25, 'majory', '010-2626-2626', 'yy', 25);",
        "INSERT INTO professor_major VALUES(1, 1);",
        "INSERT INTO professor_major VALUES(2, 2);",
        "INSERT INTO professor_major VALUES(3, 3);",
        "INSERT INTO professor_major VALUES(4, 4);",
        "INSERT INTO professor_major VALUES(5, 5);",
        "INSERT INTO professor_major VALUES(6, 6);",
        "INSERT INTO professor_major VALUES(7, 7);",
        "INSERT INTO professor_major VALUES(8, 8);",
        "INSERT INTO professor_major VALUES(9, 9);",
        "INSERT INTO professor_major VALUES(10, 10);",
        "INSERT INTO professor_major VALUES(11, 11);",
        "INSERT INTO professor_major VALUES(12, 12);",
        "INSERT INTO professor_major VALUES(13, 13);",
        "INSERT INTO professor_major VALUES(14, 14);",
        "INSERT INTO professor_major VALUES(15, 15);",
        "INSERT INTO professor_major VALUES(16, 16);",
        "INSERT INTO professor_major VALUES(17, 17);",
        "INSERT INTO professor_major VALUES(18, 18);",
        "INSERT INTO professor_major VALUES(19, 19);",
        "INSERT INTO professor_major VALUES(20, 20);",
        "INSERT INTO professor_major VALUES(21, 21);",
        "INSERT INTO professor_major VALUES(22, 22);",
        "INSERT INTO professor_major VALUES(23, 23);",
        "INSERT INTO professor_major VALUES(24, 24);",
        "INSERT INTO professor_major VALUES(25, 25);",
        "INSERT INTO lecture VALUES(1, 1, 1, 'lecturea', '월', 1, 1, 3, 1, 'aa');",
        "INSERT INTO lecture VALUES(2, 2, 2, 'lecturea', '화', 2, 1, 3, 2, 'bb');",
        "INSERT INTO lecture VALUES(3, 3, 3, 'lecturea', '수', 3, 1, 3, 3, 'cc');",
        "INSERT INTO lecture VALUES(4, 4, 4, 'lecturea', '목', 4, 1, 3, 4, 'dd');",
        "INSERT INTO lecture VALUES(5, 5, 5, 'lecturea', '금', 5, 1, 3, 5, 'ee');",
        "INSERT INTO lecture VALUES(6, 1, 6, 'lectureb', '월', 6, 2, 1, 6, 'ff');",
        "INSERT INTO lecture VALUES(7, 2, 7, 'lectureb', '화', 1, 2, 1, 7, 'gg');",
        "INSERT INTO lecture VALUES(8, 3, 8, 'lectureb', '수', 2, 2, 1, 8, 'hh');",
        "INSERT INTO lecture VALUES(9, 4, 9,  'lectureb', '목', 3, 2, 1, 9, 'ii');",
        "INSERT INTO lecture VALUES(10, 5, 10, 'lectureb', '금', 4, 2, 1, 10, 'jj');",
        "INSERT INTO lecture VALUES(11, 1, 11, 'lecturec', '월', 5, 3, 4, 11, 'kk');",
        "INSERT INTO lecture VALUES(12, 2, 12, 'lecturec', '화', 6, 3, 4, 12, 'll');",
        "INSERT INTO lecture VALUES(13, 3, 13, 'lecturec', '수', 1, 3, 4, 13, 'mm');",
        "INSERT INTO lecture VALUES(14, 4, 14, 'lecturec', '목', 2, 3, 4, 14, 'nn');",
        "INSERT INTO lecture VALUES(15, 5, 15, 'lecturec', '금', 3, 3, 4, 15, 'oo');",
        "INSERT INTO lecture VALUES(16, 1, 16, 'lectured', '월', 4, 4, 2, 16, 'pp');",
        "INSERT INTO lecture VALUES(17, 2, 17, 'lectured', '화', 5, 4, 2, 17, 'qq');",
        "INSERT INTO lecture VALUES(18, 3, 18, 'lectured', '수', 6, 4, 2, 18, 'rr');",
        "INSERT INTO lecture VALUES(19, 4, 19, 'lectured', '목', 1, 4, 2, 19, 'ss');",
        "INSERT INTO lecture VALUES(20, 5, 20, 'lectured', '금', 2, 4, 2, 20, 'tt');",
        "INSERT INTO lecture VALUES(21, 1, 21, 'lecturee', '월', 3, 2, 6, 21, 'uu');",
        "INSERT INTO lecture VALUES(22, 2, 22, 'lecturee', '화', 4, 2, 6, 22, 'vv');",
        "INSERT INTO lecture VALUES(23, 3, 23,'lecturee', '수', 5, 2, 6, 23, 'ww');",
        "INSERT INTO lecture VALUES(24, 4, 24, 'lecturee', '목', 6, 2, 6, 24, 'xx');",
        "INSERT INTO lecture VALUES(25, 5, 25, 'lecturee', '금', 1, 2, 6, 25, 'yy');",
        "INSERT INTO student VALUES(1, 'studenta', '서울시 마포구', '010-0000-0000', 'aa@gmail.com', 1, 1,'11111111', 24);",
        "INSERT INTO student VALUES(2, 'studentb', '서울시 강남구', '010-1111-1111', 'bb@gmail.com', 1, 2,'22222222', 14);",
        "INSERT INTO student VALUES(3, 'studentc', '서울시 강동구', '010-2222-2222', 'cc@gmail.com', 2, 3, '33333333', null);",
        "INSERT INTO student VALUES(4, 'studentd', '서울시 강서구', '010-3333-3333', 'dd@gmail.com', 3, 4, '44444444', 23);",
        "INSERT INTO student VALUES(5, 'studente', '서울시 관악구', '010-4444-4444', 'ee@gmail.com', 5, 5, '55555555', null);",
        "INSERT INTO student VALUES(6, 'studentf', '서울시 광진구', '010-5555-5555', 'ff@gmail.com', 6, 6, '66666666', 21);",
        "INSERT INTO student VALUES(7, 'studentg', '서울시 구로구', '010-6666-6666', 'gg@gmail.com', 7, 7, '77777777', 19);",
        "INSERT INTO student VALUES(8, 'studenth', '서울시 금천구', '010-7777-7777', 'hh@gmail.com', 7, 8, '88888888', 18);",
        "INSERT INTO student VALUES(9, 'studenti', '서울시 관악구', '010-8888-8888', 'ii@gmail.com', 11, 9, '99999999', 17);",
        "INSERT INTO student VALUES(10, 'studentj', '서울시 노원구', '010-9999-9999', 'jj@gmail.com', 12, 10, '10101010', 16);",
        "INSERT INTO student VALUES(11, 'studentk', '서울시 도봉구', '010-1010-1010', 'kk@gmail.com', 13, 11, '121212122', 15);",
        "INSERT INTO student VALUES(12, 'studentl', '서울시 동대문구', '010-1212-1212', 'll@gmail.com', 14, 12, '13131313', 15);",
        "INSERT INTO student VALUES(13, 'studentm', '서울시 동작구', '010-1313-1313', 'mm@gmail.com', 15, 13, '14141414', 14);",
        "INSERT INTO student VALUES(14, 'studentn', '서울시 서대문구', '010-1414-1414', 'nn@gmail.com', 17, 14, '15151515', 13);",
        "INSERT INTO student VALUES(15, 'studento', '서울시 서초구', '010-1515-1515', 'oo@gmail.com', 17, 15, '16161616', 11);",
        "INSERT INTO student VALUES(16, 'studentp', '서울시 성동구', '010-1616-1616', 'pp@gmail.com', 17, 16, '17171717', 9);",
        "INSERT INTO student VALUES(17, 'studentq', '서울시 성북구', '010-1717-1717', 'qq@gmail.com', 18, 17, '18181818', 8);",
        "INSERT INTO student VALUES(18, 'studentr', '서울시 송파구', '010-1818-1818', 'rr@gmail.com', 19, 18, '19191919', 7);",
        "INSERT INTO student VALUES(19, 'students', '서울시 양천구', '010-1919-1919', 'ss@gmail.com', 20, 19, '20202020', 6);",
        "INSERT INTO student VALUES(20, 'studentt', '서울시 영등포구', '010-2020-2020', 'tt@gmail.com', 21, 20, '21212121', null);",
        "INSERT INTO student VALUES(21, 'studentu', '서울시 용산구', '010-2121-2121', 'uu@gmail.com', 21, 21, '23232323', 5);",
        "INSERT INTO student VALUES(22, 'studentv', '서울시 은평구', '010-2323-2323', 'vv@gmail.com', 23, 22,'24242424', 5);",
        "INSERT INTO student VALUES(23, 'studentw', '서울시 종로구', '010-2424-2424', 'ww@gmail.com', 24, 23,'25252525', 3);",
        "INSERT INTO student VALUES(24, 'studentx', '서울시 중랑구', '010-2525-2525', 'xx@gmail.com', 24, 24, '26262626', null);",
        "INSERT INTO student VALUES(25, 'studenty', '서울시 중구', '010-2626-2626', 'yy@gmail.com', 25, 25, '27272727', 1);",
        "INSERT INTO tuition VALUES(1, 2020, 1, 4500000, 4500000, STR_TO_DATE('2020-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(2, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(3, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(4, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(5, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(6, 2021, 1, 4500000, 4000000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(7, 2020, 1, 4500000, 4500000, STR_TO_DATE('2020-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(8, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(9, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(10, 2021, 1, 4500000, 4000000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(11, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(12, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(13, 2020, 1, 4500000, 4500000, STR_TO_DATE('2020-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(14, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(15, 2020, 2, 4500000, 4000000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(16, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(17, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(18, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(19, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(20, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(21, 2020, 2, 4500000, 4000000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(22, 2021, 1, 4500000, 4500000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(23, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(24, 2021, 1, 4500000, 4000000, STR_TO_DATE('2021-03-05','%Y-%m-%d'));",
        "INSERT INTO tuition VALUES(25, 2020, 2, 4500000, 4500000, STR_TO_DATE('2020-09-05','%Y-%m-%d'));",
        "INSERT INTO club VALUES(1, 'cluba', 1, 1, 'aa');",
        "INSERT INTO club VALUES(2, 'clubb', 2, 2, 'bb');",
        "INSERT INTO club VALUES(3, 'clubc', 3, 3, 'cc');",
        "INSERT INTO club VALUES(4, 'clubd', 4, 4, 'dd');",
        "INSERT INTO club VALUES(5, 'clube', 5, 5, 'ee');",
        "INSERT INTO club VALUES(6, 'clubf', 6, 6, 'ff');",
        "INSERT INTO club VALUES(7, 'clubg', 7, 7, 'gg');",
        "INSERT INTO club VALUES(8, 'clubh', 8, 8, 'hh');",
        "INSERT INTO club VALUES(9, 'clubi', 9, 9, 'ii');",
        "INSERT INTO club VALUES(10, 'clubj', 10, 10, 'jj');",
        "INSERT INTO club VALUES(11, 'clubk', 11, 11, 'kk');",
        "INSERT INTO club VALUES(12, 'clubl', 12, 12, 'll');",
        "INSERT INTO club VALUES(13, 'clubm', 13, 13, 'mm');",
        "INSERT INTO club VALUES(14, 'clubn', 14, 14, 'nn');",
        "INSERT INTO club VALUES(15, 'clubo', 15, 15, 'oo');",
        "INSERT INTO club VALUES(16, 'clubp', 16, 16, 'pp');",
        "INSERT INTO club VALUES(17, 'clubq', 17, 17, 'qq');",
        "INSERT INTO club VALUES(18, 'clubr', 18, 18, 'rr');",
        "INSERT INTO club VALUES(19, 'clubs', 19, 19, 'ss');",
        "INSERT INTO club VALUES(20, 'clubt', 20, 20, 'tt');",
        "INSERT INTO club VALUES(21, 'clubu', 21, 21, 'uu');",
        "INSERT INTO club VALUES(22, 'clubv', 22, 22, 'vv');",
        "INSERT INTO club VALUES(23, 'clubw', 23, 23, 'ww');",
        "INSERT INTO club VALUES(24, 'clubx', 24, 24, 'xx');",
        "INSERT INTO club VALUES(25, 'cluby', 25, 25, 'yy');",
        "INSERT INTO club_history VALUES(1, 1);",
        "INSERT INTO club_history VALUES(1, 20);",
        "INSERT INTO club_history VALUES(2, 1);",
        "INSERT INTO club_history VALUES(3, 3);",
        "INSERT INTO club_history VALUES(4, 4);",
        "INSERT INTO club_history VALUES(5, 5);",
        "INSERT INTO club_history VALUES(6, 6);",
        "INSERT INTO club_history VALUES(7, 7);",
        "INSERT INTO club_history VALUES(8, 8);",
        "INSERT INTO club_history VALUES(9, 9);",
        "INSERT INTO club_history VALUES(10, 10);",
        "INSERT INTO club_history VALUES(11, 11);",
        "INSERT INTO club_history VALUES(12, 12);",
        "INSERT INTO club_history VALUES(13, 13);",
        "INSERT INTO club_history VALUES(14, 14);",
        "INSERT INTO club_history VALUES(15, 15);",
        "INSERT INTO club_history VALUES(16, 16);",
        "INSERT INTO club_history VALUES(17, 17);",
        "INSERT INTO club_history VALUES(18, 18);",
        "INSERT INTO club_history VALUES(19, 19);",
        "INSERT INTO club_history VALUES(20, 2);",
        "INSERT INTO club_history VALUES(21, 21);",
        "INSERT INTO club_history VALUES(22, 2);",
        "INSERT INTO club_history VALUES(23, 23);",
        "INSERT INTO club_history VALUES(24, 24);",
        "INSERT INTO club_history VALUES(25, 25);",
        "INSERT INTO relationship VALUES(1, 1, 2021, 1);",
        "INSERT INTO relationship VALUES(2, 2, 2020, 1);",
        "INSERT INTO relationship VALUES(3, 3, 2019, 1);",
        "INSERT INTO relationship VALUES(4, 4, 2019, 2);",
        "INSERT INTO relationship VALUES(5, 5, 2021, 1);",
        "INSERT INTO relationship  VALUES(6, 6, 2020, 1);",
        "INSERT INTO relationship VALUES(7, 7, 2019, 2);",
        "INSERT INTO relationship VALUES(8, 8, 2019, 1);",
        "INSERT INTO relationship VALUES(9, 9, 2021, 1);",
        "INSERT INTO relationship VALUES(10, 10, 2020, 1);",
        "INSERT INTO relationship VALUES(11, 11, 2019, 1);",
        "INSERT INTO relationship VALUES(12, 12, 2019, 2);",
        "INSERT INTO relationship VALUES(13, 13, 2021, 1);",
        "INSERT INTO relationship VALUES(14, 14, 2020, 1);",
        "INSERT INTO relationship VALUES(15, 15, 2019, 2);",
        "INSERT INTO relationship VALUES(16, 16, 2019, 1);",
        "INSERT INTO relationship VALUES(17, 17, 2021, 1);",
        "INSERT INTO relationship VALUES(18, 18, 2020, 1);",
        "INSERT INTO relationship VALUES(19, 19, 2019, 1);",
        "INSERT INTO relationship VALUES(20, 20, 2019, 2);",
        "INSERT INTO relationship VALUES(21, 21, 2021, 1);",
        "INSERT INTO relationship VALUES(22, 22, 2020, 1);",
        "INSERT INTO relationship VALUES(23, 23, 2019, 2);",
        "INSERT INTO relationship VALUES(24, 24, 2019, 1);",
        "INSERT INTO relationship VALUES(25, 25, 2021, 1);",
        "INSERT INTO course VALUES(1, 1, 1, 2020, 2, 10, 30, 28, 30, 98, 'A+');",
        "INSERT INTO course VALUES(2, 2, 2, 2020, 2, 9, 25, 19, 28, 81, 'B+');",
        "INSERT INTO course VALUES(3, 3, 3, 2021, 1, 8, 27, 30, 27, 92, 'A');",
        "INSERT INTO course VALUES(4, 4, 4, 2021, 1, 7, 26, 13, 17, 63, 'C');",
        "INSERT INTO course VALUES(5, 5, 5, 2020, 2, 6, 29, 16, 29, 80, 'B');",
        "INSERT INTO course VALUES(6, 6, 6, 2020, 2, 10, 28, 18, 16, 72, 'C');",
        "INSERT INTO course VALUES(7, 7, 7, 2021, 1, 9, 19, 25, 27, 80, 'B');",
        "INSERT INTO course VALUES(8, 8, 8, 2021, 1, 8, 30, 27, 14, 79, 'C+');",
        "INSERT INTO course VALUES(9, 9, 9, 2020, 2, 7, 0, 7, 9, 23, 'F');",
        "INSERT INTO course VALUES(10, 10, 10, 2020, 2, 6, 14, 24, 26, 70, 'C');",
        "INSERT INTO course VALUES(11, 11, 11, 2021, 1, 10, 30, 26, 24, 90, 'A');",
        "INSERT INTO course VALUES(12, 12, 12, 2021, 1, 9, 20, 27, 13, 69, 'C');",
        "INSERT INTO course VALUES(13, 13, 13, 2020, 2, 8, 18, 17, 21, 64, 'C');",
        "INSERT INTO course VALUES(14, 14, 14, 2020, 2, 7, 16, 18, 14, 55, 'D+');",
        "INSERT INTO course VALUES(15, 15, 15, 2021, 1, 6, 17, 28, 18, 69, 'C');",
        "INSERT INTO course VALUES(16, 16, 16, 2021, 1, 10, 13, 26, 28, 77, 'C+');",
        "INSERT INTO course VALUES(17, 17, 17, 2020, 2, 9, 29, 18, 15, 71, 'C');",
        "INSERT INTO course VALUES(18, 18, 18, 2020, 2, 8, 30, 27, 13, 78, 'C+');",
        "INSERT INTO course VALUES(19, 19, 19, 2021, 1, 7, 19, 17, 29, 72, 'C');",
        "INSERT INTO course VALUES(20, 20, 20, 2021, 1, 10, 18, 19, 17, 64, 'C');",
        "INSERT INTO course VALUES(21, 21, 21, 2020, 2, 9, 27, 30, 23, 89, 'B+');",
        "INSERT INTO course VALUES(22, 22, 22, 2020, 2, 8, 20, 27, 30, 85, 'B');",
        "INSERT INTO course VALUES(23, 23, 23, 2021, 1, 7, 24, 18, 17, 66, 'C');",
        "INSERT INTO course VALUES(24, 24, 24, 2021, 1, 6, 17, 10, 9, 42, 'F');",
        "INSERT INTO course VALUES(25, 25, 25, 2021, 1, 6, 24, 20, 11, 61, 'C');",
    };
}