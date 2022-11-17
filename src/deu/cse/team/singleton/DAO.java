/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.singleton;

/**
 *
 * @author PC
 */
import deu.cse.team.login.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class DAO {
    // 2022. 11. 05 [최초작성자 정현수]
    // DB 데이터 연동 클래스

    // DB 연동 방법.
    // 1) 클래스를 로드 - 프로그램 전체에서 1번만 수행
    // Static 초기화를 이용.
    // 2) DB연결 - Connection 객체를 이용. (메서드를 만들어서 사용)
    // Connection변수 = DriverManager.getConnection(위치, 아이디, 비번)을 이용.
    // 3) DB 사용 - Statment, PreparedStatement, CallableStatement객체 (SQL문 실행)
    // ResultSet - Select 구문 결과 출력. (그외에는 정수(영향받은 행의 갯수)로 출력)
    // 4) 자원을 해제. - 위 객체들을 close();
    static { // Static 초기화
        // 여기에 작성한 내용을 클래스가 메모리에 로드될 때 딱 1번만 수행됩니다. (다시는 수행 안함)
        // 객체가 생성되기 전에 수행되기 때문에 멤버 변수 사용은 안됩니다.

        // 오라클 드라이버 로드
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("클래스 로드 실패 : " + e.getMessage());
        }
    }

    //Singleton 클래스로 만들기 위해서 생성자를 private로 선언
    private DAO() {
    }

    //하나의 객체를 주소로 저장할 변수 선언
    private static DAO uniqueInstance = new DAO();

    //선언한 static변수에 객체를 생성해주는 메서드 선언
    public static DAO getInstance() {
        return uniqueInstance;
    }

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    // 데이터베이스 연결을 수행해주는 메서드.
    private boolean connect() {
        boolean result = false;
        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl", "b20183207", "20183207");
            result = true;
        } catch (Exception e) {
            System.out.println("연결 실패 : " + e.getMessage());
        }
        return result;
    }

    // 데이터베이스 연결을 해제하는 메서드
    private void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("해제 실패 : " + e.getMessage());
        }
    }

    // 모든 테이블의 모든 데이터를 읽어서 List<Account> 타입에 저장해서 리턴하는 메서드.
    // Select문을 이용. 결과는 ResultSet에 저장됨. (위에서 선언했음)
    // 여기서 AccountDTO는 DTO클래스 AccountDTO를 의미.
    public List<AccountDTO> getAccountList() {
        // 데이터를 저장할 변수 생성.
        //(여기서 객체 생성을 안하는 이유는 접속이 되었을 때만 객체생성을 해야 의미가 있기 때문에.
        //접속 안됬는데 객체를 생성한다면 이 DB에 데이터를 넣을수 있는지 없는지 판단할 기준이 애매해짐.
        List<AccountDTO> list = null;

        // 수행할 sql문장을 생성.
        String sql = "SELECT * FROM account";

        // 데이터베이스에 연결이 되었을때만 Select문 실행.
        if (connect()) {
            // 연결에 성공했을 때 작업
            try {
                //Connection객체에 연결된(DB에 연결된) Statement 객체를 생성하여 Statement변수에 대입.
                stmt = con.createStatement();
                if (stmt != null) { //위 객체가 Null이 아니라는 것은 무언가를 받아왔다는 의미. SQL문장을 받아온 것.
                    //sql구문 실행 (Select문의 결과는 ResultSet에 저장. (위에서 선언))
                    rs = stmt.executeQuery(sql);

                    //데이터를 저장할 리스트 생성
                    list = new ArrayList<AccountDTO>();

                    //데이터를 읽어서 list에 저장
                    while (rs.next()) {
                        //DTO 클래스의 객체 생성. (모든 데이터가 DTO클래스에 들어있으므로)
                        AccountDTO account = new AccountDTO();
                        //DTO클래스의 변수에 값을 세팅하기 위해 Set메서드를 이용하고.
                        //Select의 결과를 컬럼 단위로 읽어오기 위해서 'get변수타입(컬럼명)' 메서드를 이용
                        account.setId(rs.getString("id"));
                        account.setPassword(rs.getString("password"));
                        account.setName(rs.getString("name"));
                        account.setPhonenumber(rs.getString("phonenumber"));
                        account.setWarning(rs.getInt("warning"));
                        account.setPower(rs.getString("power"));
                        account.setAllowed(rs.getString("allowed"));
                        list.add(account);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // 연결에 실패했을 때 작업
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return list;
    }

    public List<ClassTimetableDTO> getTimetableList() {
        List<ClassTimetableDTO> list = null;
        String sql = "SELECT * FROM classtimetable";
        if (connect()) {
            try {
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    list = new ArrayList<ClassTimetableDTO>();
                    while (rs.next()) {
                        ClassTimetableDTO timetable = new ClassTimetableDTO();
                        timetable.setClassnumber(rs.getString("classnumber"));
                        timetable.setTime1(rs.getString("time1"));
                        timetable.setTime2(rs.getString("time2"));
                        timetable.setTime3(rs.getString("time3"));
                        timetable.setTime4(rs.getString("time4"));
                        timetable.setTime5(rs.getString("time5"));
                        timetable.setTime6(rs.getString("time6"));
                        timetable.setTime7(rs.getString("time7"));
                        timetable.setTime8(rs.getString("time8"));

                        list.add(timetable);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return list;
    }

    public List<ReservationDTO> getReserList() {
        List<ReservationDTO> list = null;
        String sql = "SELECT * FROM reservation";
        if (connect()) {
            try {
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    list = new ArrayList<ReservationDTO>();
                    while (rs.next()) {
                        ReservationDTO reserList = new ReservationDTO();
                        reserList.setReser_number(rs.getInt("reser_number"));
                        reserList.setSeat_number(rs.getInt("seat_number"));
                        reserList.setId(rs.getString("id"));
                        reserList.setClassnumber(rs.getString("classnumber"));
                        reserList.setReser_date(rs.getString("reser_date"));
                        reserList.setReser_starttime(rs.getString("reser_starttime"));
                        reserList.setReser_endtime(rs.getString("reser_endtime"));
                        reserList.setClassadmin(rs.getString("classadmin"));
                        reserList.setOk(rs.getString("ok"));

                        list.add(reserList);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return list;
    }

    public List<ReservationDTO> getclassReserList(String classnumber) {
        List<ReservationDTO> list = null;
        String sql = "SELECT * FROM reservation where classnumber = '" + classnumber + "'";
        if (connect()) {
            try {
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    list = new ArrayList<ReservationDTO>();
                    while (rs.next()) {
                        ReservationDTO reserList = new ReservationDTO();
                        reserList.setReser_number(rs.getInt("reser_number"));
                        reserList.setSeat_number(rs.getInt("seat_number"));
                        reserList.setId(rs.getString("id"));
                        reserList.setClassnumber(rs.getString("classnumber"));
                        reserList.setReser_date(rs.getString("reser_date"));
                        reserList.setReser_starttime(rs.getString("reser_starttime"));
                        reserList.setReser_endtime(rs.getString("reser_endtime"));
                        reserList.setClassadmin(rs.getString("classadmin"));
                        reserList.setOk(rs.getString("ok"));

                        list.add(reserList);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return list;
    }

    public List<ClassInformationDTO> getClassInformation() {
        List<ClassInformationDTO> list = null;
        String sql = "SELECT * FROM classinformation";
        if (connect()) {
            try {
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    list = new ArrayList<ClassInformationDTO>();
                    while (rs.next()) {
                        ClassInformationDTO classinforList = new ClassInformationDTO();
                        classinforList.setClassnumber(rs.getString("classnumber"));
                        classinforList.setMaxseat(rs.getInt("maxseat"));
                        list.add(classinforList);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return list;
    }

    public String getClassMaxseat(String classnumber) {
        String maxseat = "0";
        String sql = "SELECT * FROM classinformation where classnumber = '" + classnumber + "'";
        if (connect()) {
            try {
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        maxseat = rs.getString("maxseat");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return maxseat;
    }

    public int getClassReserLength(String classnumber) {
        int value = 0;
        String sql = "SELECT * FROM reservation where classnumber = '" + classnumber + "'";

        if (connect()) {
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                if (stmt != null) {
                    //데이터를 읽어서 list에 저장
                    while (rs.next()) {
                        value++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // 연결에 실패했을 때 작업
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }
        return value;
    }

    public String getTokenList() {
        String value = null;
        String sql = "SELECT * FROM TOKEN";
        if (connect()) {
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                if (stmt != null) {

                    //데이터를 읽어서 list에 저장
                    while (rs.next()) {
                        value = rs.getString("token_value");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // 연결에 실패했을 때 작업
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return value;
    }

    //account 테이블에 데이터를 삽입하는 메서드
    public boolean InsertAccount(AccountDTO account) {
        boolean result = false;

        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "INSERT INTO account VALUES (?,?,?,?,?,?,?)"; //모든 컬럼에 값을 넣으므로 컬럼명 생략.
                PreparedStatement pstmt = con.prepareStatement(sql);

                //VALUES의 ?에 값을 바인딩. (바인딩 : ?에 들어갔어야 하는 원래 데이터 값을 입력.
                //바인딩 방법. set자료형(컬럼, 들어갈 데이터); 
                pstmt.setString(1, account.getId());
                pstmt.setString(2, account.getPassword());
                pstmt.setString(3, account.getName());
                pstmt.setString(4, account.getPhonenumber());
                pstmt.setInt(5, account.getWarning());
                pstmt.setString(6, account.getPower());
                pstmt.setString(7, account.getAllowed());

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean InsertReservation(ReservationDTO reservation) {
        boolean result = false;
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "INSERT INTO reservation VALUES (?,?,?,?,?,?,?,?,?)"; //모든 컬럼에 값을 넣으므로 컬럼명 생략.
                PreparedStatement pstmt = con.prepareStatement(sql);
                //VALUES의 ?에 값을 바인딩. (바인딩 : ?에 들어갔어야 하는 원래 데이터 값을 입력.
                pstmt.setInt(1, reservation.getReser_number());
                pstmt.setInt(2, reservation.getSeat_number());
                pstmt.setString(3, reservation.getId());
                pstmt.setString(4, reservation.getClassnumber());
                pstmt.setString(5, reservation.getReser_date());
                pstmt.setString(6, reservation.getReser_starttime());
                pstmt.setString(7, reservation.getReser_endtime());
                pstmt.setString(8, reservation.getClassadmin());
                pstmt.setString(9, reservation.getOk());
                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public int getReserLength() {
        int count = 0;
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "SELECT max(reser_number) as reser_number FROM reservation";
                PreparedStatement pstmt = con.prepareStatement(sql);
                stmt = con.createStatement();
                if (stmt != null) { //위 객체가 Null이 아니라는 것은 무언가를 받아왔다는 의미. SQL문장을 받아온 것.
                    //sql구문 실행 (Select문의 결과는 ResultSet에 저장. (위에서 선언))
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        count = rs.getInt("reser_number") + 1;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에     실패");
            System.exit(0);
        }

        return count;
    }

    public ArrayList<ReservationDTO> getreservationcanadd(String classnumber, String today, String endtime, String seat_number) {
        ArrayList<ReservationDTO> list = null;
        String sql = "SELECT * FROM reservation where classnumber = '" + classnumber + "' and reser_date= '" + today + "' and reser_starttime <= '" + endtime + "' and seat_number = '" + seat_number + "' and ok='1'";
        System.out.println(sql);
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                PreparedStatement pstmt = con.prepareStatement(sql);
                stmt = con.createStatement();
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    list = new ArrayList<ReservationDTO>();
                    while (rs.next()) {
                        ReservationDTO reserList = new ReservationDTO();
                        reserList.setReser_number(rs.getInt("reser_number"));
                        reserList.setSeat_number(rs.getInt("seat_number"));
                        reserList.setId(rs.getString("id"));
                        reserList.setClassnumber(rs.getString("classnumber"));
                        reserList.setReser_date(rs.getString("reser_date"));
                        reserList.setReser_starttime(rs.getString("reser_starttime"));
                        reserList.setReser_endtime(rs.getString("reser_endtime"));
                        reserList.setClassadmin(rs.getString("classadmin"));
                        reserList.setOk(rs.getString("ok"));

                        list.add(reserList);
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return list;
    }

    public ArrayList<ReservationDTO> getreservation(String classnumber, String today, String starttime, String endtime) {
        ArrayList<ReservationDTO> list = null;
        String sql = "SELECT * FROM reservation where classnumber = '" + classnumber + "' and reser_date= '" + today + "' and reser_starttime >= '" + starttime + ":00' and reser_endtime <= '" + endtime + ":00'";
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                PreparedStatement pstmt = con.prepareStatement(sql);
                stmt = con.createStatement();
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    list = new ArrayList<ReservationDTO>();
                    while (rs.next()) {
                        ReservationDTO reserList = new ReservationDTO();
                        reserList.setReser_number(rs.getInt("reser_number"));
                        reserList.setSeat_number(rs.getInt("seat_number"));
                        reserList.setId(rs.getString("id"));
                        reserList.setClassnumber(rs.getString("classnumber"));
                        reserList.setReser_date(rs.getString("reser_date"));
                        reserList.setReser_starttime(rs.getString("reser_starttime"));
                        reserList.setReser_endtime(rs.getString("reser_endtime"));
                        reserList.setClassadmin(rs.getString("classadmin"));
                        reserList.setOk(rs.getString("ok"));

                        list.add(reserList);
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return list;
    }

    public int getselecttimeReserLength(String classnumber, String today, String starttime) {
        int count = 0;
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "SELECT * FROM reservation where classnumber = '" + classnumber + "' and reser_date= '" + today + "' and reser_starttime >= '" + starttime + ":00' and ok = '1'";
                PreparedStatement pstmt = con.prepareStatement(sql);
                stmt = con.createStatement();
                if (stmt != null) { //위 객체가 Null이 아니라는 것은 무언가를 받아왔다는 의미. SQL문장을 받아온 것.
                    //sql구문 실행 (Select문의 결과는 ResultSet에 저장. (위에서 선언))
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        count++;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
        System.out.println(count);
        return count;
    }

    public boolean isuserreserd(String id, String today, String starttime) {
        boolean isreserd = false;
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "SELECT * FROM reservation where id = '" + id + "' and reser_date= '" + today + "' and reser_starttime >= '" + starttime + ":00' and ok = '1'";
                System.out.println(sql);
                PreparedStatement pstmt = con.prepareStatement(sql);
                stmt = con.createStatement();
                if (stmt != null) { //위 객체가 Null이 아니라는 것은 무언가를 받아왔다는 의미. SQL문장을 받아온 것.
                    //sql구문 실행 (Select문의 결과는 ResultSet에 저장. (위에서 선언))
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        System.out.println("what? ");
                        isreserd = true;
                        if (isreserd) {
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
        System.out.println(isreserd);
        return isreserd;
    }

    public String getClassAdmin(String classnumber, String today) {
        String id = "미정";
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "SELECT * FROM reservation where classnumber = '" + classnumber + "' and reser_date= '" + today + "' and classadmin not in ('-','조교')";
                System.out.println(sql);
                PreparedStatement pstmt = con.prepareStatement(sql);
                stmt = con.createStatement();
                if (stmt != null) { //위 객체가 Null이 아니라는 것은 무언가를 받아왔다는 의미. SQL문장을 받아온 것.
                    //sql구문 실행 (Select문의 결과는 ResultSet에 저장. (위에서 선언))
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        id = rs.getString("id");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
        return id;
    }

    public String[] getClassAdminEndtime(String classnumber, String today) {
        String[] str = new String[2];

        String reser_endtime = "미정";
        String reser_number = "미정";
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "SELECT * FROM reservation where classnumber = '" + classnumber + "' and reser_date= '" + today + "' and classadmin not in ('-','조교')";
                System.out.println(sql);
                PreparedStatement pstmt = con.prepareStatement(sql);
                stmt = con.createStatement();
                if (stmt != null) { //위 객체가 Null이 아니라는 것은 무언가를 받아왔다는 의미. SQL문장을 받아온 것.
                    //sql구문 실행 (Select문의 결과는 ResultSet에 저장. (위에서 선언))
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        reser_endtime = rs.getString("reser_endtime");
                        reser_number = rs.getString("reser_number");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
        str[0] = reser_endtime;
        str[1] = reser_number;
        return str;
    }

    /*
    public boolean InsertC(ClassTimetableDTO time) {
        boolean result = false;

        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "INSERT INTO classtimetable VALUES (?,?,?,?,?,?,?,?,?)"; //모든 컬럼에 값을 넣으므로 컬럼명 생략.
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                //VALUES의 ?에 값을 바인딩. (바인딩 : ?에 들어갔어야 하는 원래 데이터 값을 입력.
                //바인딩 방법. set자료형(컬럼, 들어갈 데이터); 
                pstmt.setString(1, time.getClassnumber());
                pstmt.setString(2, time.getTime1());
                pstmt.setString(3, time.getTime2());
                pstmt.setString(4, time.getTime3());
                pstmt.setString(5, time.getTime4());
                pstmt.setString(6, time.getTime5());
                pstmt.setString(7, time.getTime6());
                pstmt.setString(8, time.getTime7());
                pstmt.setString(9, time.getTime8());
                


                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }
   
     */
    public boolean UpdateToken(TokenDTO token, String oldToken, String newToken) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE token SET token_value=" + newToken + " WHERE token_value =" + oldToken;
                PreparedStatement pstmt = con.prepareStatement(sql);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateAccount(AccountDTO account, String id, String allowed) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE account SET allowed =(?) WHERE id =" + id;

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, allowed);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public void Updateallowed(String id) {
        boolean result = false;
        if (this.connect()) {
            try {
                String sql = "UPDATE account SET allowed ='1' WHERE id ='" + id + "'";
                PreparedStatement pstmt = con.prepareStatement(sql);
                int r = pstmt.executeUpdate();
                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
    }

    public boolean UpdateAccount(AccountDTO account, String id, String password, String name, String phonenumber) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE account SET password =(?),name =(?),phonenumber =(?) WHERE id =" + id;

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, password);
                pstmt.setString(2, name);
                pstmt.setString(3, phonenumber);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateAccount(AccountDTO account, String id, String password, String name, String phonenumber, int warning, String power, String allowed) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE account SET password =(?),name =(?),phonenumber =(?), warning =(?), power = (?), allowed = (?) WHERE id =" + id;

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, password);
                pstmt.setString(2, name);
                pstmt.setString(3, phonenumber);
                pstmt.setInt(4, warning);
                pstmt.setString(5, power);
                pstmt.setString(6, allowed);
                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateTimetable(ClassTimetableDTO classtimetable, String classnumber,
            String time1, String time2, String time3, String time4, String time5, String time6, String time7, String time8) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE classtimetable SET time1=(?),time2 =(?),time3 =(?),time4 =(?),time5 =(?),time6 =(?),time7 =(?),time8 =(?) WHERE classnumber =" + classnumber;

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, time1);
                pstmt.setString(2, time2);
                pstmt.setString(3, time3);
                pstmt.setString(4, time4);
                pstmt.setString(5, time5);
                pstmt.setString(6, time6);
                pstmt.setString(7, time7);
                pstmt.setString(8, time8);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateCancelReser(ReservationDTO reservation, String reser_number) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE reservation SET ok=2 classadmin=- WHERE reser_number =" + reser_number;

                PreparedStatement pstmt = con.prepareStatement(sql);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateReser(ReservationDTO reservation, String reser_number, String classadmin, String ok) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE reservation SET classadmin=(?), ok=(?) WHERE reser_number =" + reser_number;

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, classadmin);
                pstmt.setString(2, ok);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateReser(ReservationDTO reservation, String reser_number,
            String reser_endtime) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE reservation SET reser_endtime=(?) WHERE reser_number =" + reser_number;

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, reser_endtime);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean DeleteAccount(AccountDTO account, String id) {
        boolean result = false;
        if (this.connect()) {
            try {
                String sql = "DELETE FROM account CASCADE WHERE id=(?)";

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, id);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();

                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateClassInformation(String classnumber, String maxseat) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE classinformation SET maxseat=(?) WHERE classnumber =(?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, maxseat);
                pstmt.setString(2, classnumber);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean CancelReser(ReservationDTO reservation, String reser_number) {
        boolean result = false;
        if (this.connect()) {
            try {
                String sql = "DELETE FROM reservation CASCADE WHERE reser_number=(?)";

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, reser_number);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();

                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean DeleteReser(ReservationDTO reservation, String id) {
        boolean result = false;
        if (this.connect()) {
            try {
                String sql = "DELETE FROM reservation CASCADE WHERE id=(?)";

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, id);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();

                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public void boardInsert(BoardDTO b) {
        if (this.connect()) {
            try {
                String sql = "insert into board(no, title, content, sid, sps, type) values(no.NEXTVAL,?,?,?,?,?)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, b.getTitle());
                st.setString(2, b.getContent());
                st.setString(3, b.getSid());
                st.setString(4, b.getSps());
                st.setString(5, b.getType());

                st.executeUpdate();

                st.close();
                this.close();

            } catch (SQLException e) {
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
    }

    public void boardUpdate(BoardDTO b, int n1) {
        if (this.connect()) {
            try {
                String sql = "update board set title=?, content=?, sid=?, sps=?, type=? where no=?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, b.getTitle());
                st.setString(2, b.getContent());
                st.setString(3, b.getSid());
                st.setString(4, b.getSps());
                st.setString(5, b.getType());
                st.setInt(6, n1);

                st.executeUpdate();
                st.close();
                this.close();
            } catch (SQLException e) {
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
    }

    public void boardDelete(int n1) {
        if (this.connect()) {
            try {
                String sql = "delete from board where no=" + n1;
                System.out.println(sql);
                PreparedStatement st = con.prepareStatement(sql);
                st.executeUpdate();
                st.close();
                this.close();
            } catch (SQLException e) {
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
    }

    public ArrayList<BoardDTO> boardSelect() {
        ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

        if (connect()) {
            try {
                String sql = "select no, title, sid, wdate, type from board";
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        BoardDTO mc = new BoardDTO();
                        mc.setNo(rs.getInt(1));
                        mc.setTitle(rs.getString(2));
                        mc.setSid(rs.getString(3));
                        mc.setWdate(rs.getString(4));
                        mc.setType(rs.getString(5));
                        list.add(mc);
                    }
                    stmt.close();
                    this.close();
                }
            } catch (SQLException e) {
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return list;
    }

    public BoardDTO boardNoSelect(int index) {
        BoardDTO b = new BoardDTO();
        if (connect()) {
            try {
                stmt = con.createStatement();
                String sql = "select * from board where no=" + index;
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        b.setNo(rs.getInt(1));
                        b.setTitle(rs.getString(2));
                        b.setContent(rs.getString(3));
                        b.setSid(rs.getString(4));
                        b.setSps(rs.getString(5));
                        b.setType(rs.getString(6));
                        b.setWdate(rs.getString(7));
                    }
                    stmt.close();
                    this.close();
                }
            } catch (SQLException e) {
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }
        return b;
    }

    public void warningUpdate(int w, String sid) {
        if (this.connect()) {
            try {
                int ww = w + 1;
                String sql = "update account set warning=? where id=?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, ww);
                st.setString(2, sid);

                st.executeUpdate();
                st.close();
                this.close();
            } catch (SQLException e) {
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
    }

    public void AllowedUpdate(String sid) {
        if (this.connect()) {
            try {
                String sql = "update account set allowed='0' where id=?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, sid);

                st.executeUpdate();
                st.close();
                this.close();
            } catch (SQLException e) {
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
    }

    public String getUserAllowed(String id) {
        String allowed = null;
        if (connect()) {
            try {
                String sql = "select allowed from account where id='" + id + "'";
                System.out.println(sql);
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        allowed = rs.getString("allowed");
                    }
                    stmt.close();
                    this.close();
                }
            } catch (SQLException e) {
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }
        return allowed;
    }

    public List<NoticeDTO> getNoticeList(String id) {
        List<NoticeDTO> list = null;
        String sql = "SELECT * FROM notice";
        if (connect()) {
            try {
                stmt = con.createStatement();
                if (stmt != null) {
                    rs = stmt.executeQuery(sql);
                    list = new ArrayList<NoticeDTO>();
                    while (rs.next()) {
                        NoticeDTO noticeList = new NoticeDTO();
                        noticeList.setName(rs.getString("name"));
                        noticeList.setContent(rs.getString("content"));
                        noticeList.setId(rs.getString("id"));
                        noticeList.setSeperation(rs.getString("seperation"));
                        list.add(noticeList);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return list;
    }

    public boolean InsertNotice(NoticeDTO notice) {
        boolean result = false;
        if (this.connect()) {
            try {
                //값이 삽입되어야 하는 자리에는 물음표
                String sql = "INSERT INTO notice VALUES (?,?,?,?)"; //모든 컬럼에 값을 넣으므로 컬럼명 생략.
                PreparedStatement pstmt = con.prepareStatement(sql);
                //VALUES의 ?에 값을 바인딩. (바인딩 : ?에 들어갔어야 하는 원래 데이터 값을 입력.
                pstmt.setString(1, notice.getId());
                pstmt.setString(2, notice.getContent());
                pstmt.setString(3, notice.getSeperation());
                pstmt.setString(4, notice.getName());
                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean UpdateNotice(NoticeDTO notice, String id, String content, String seperation) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE notice SET seperation =(?), content = (?) WHERE id =" + id;

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, seperation);
                pstmt.setString(2, content);

                int r = pstmt.executeUpdate();

                if (r > 0) {
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }

        return result;
    }

    public boolean CheckSemester() {
        boolean result = false;
        String[] sql = new String[2];
        sql[0] = "select * from token where extract( month from to_date(changeday)) in ('9','10','11','12','1','2') and extract( month from to_date(sysdate)) in ('3','4','5','6','7','8')";
        sql[1] = "select * from token where extract( month from to_date(changeday)) in ('3','4','5','6','7','8') and extract( month from to_date(sysdate)) in ('9','10','11','12','1','2')";
        if (connect()) {
            try {
                stmt = con.createStatement();
                if (stmt != null) {
                    for (int i = 0; i < 1; i++) {
                        rs = stmt.executeQuery(sql[i]);
                        while (rs.next()) {   
                            System.out.println(rs.getString("changeday"));
                            result = true;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return result;
    }

    public void UpdateSemester() {

        if (this.connect()) {
            try {
                String sql[] = new String[4];
                sql[0] = "update token set token_value = TRUNC(DBMS_RANDOM.VALUE(100000,1000000)),changeday=sysdate;";
                sql[1] = "update account set allowed='0' where power='X';";
                sql[2] = "delete reservation where 1=1;";
                sql[3] = "update classtimetable set time1 = ' , , , , , , ', time2 = ' , , , , , , ', time3 = ' , , , , , , ', time4 = ' , , , , , , ', time5 = ' , , , , , , ', time6 = ' , , , , , , ', time7 = ' , , , , , , ', time8 = ' , , , , , , ';";
                PreparedStatement pstmt = null;
                for (int i = 0; i < 4; i++) {
                    pstmt = con.prepareStatement(sql[i]);
                    int r = pstmt.executeUpdate();
                    System.out.println(r);
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
    }
}
