/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.singleton;

/**
 *
 * @author PC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import javax.naming.NamingException;

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
                        account.setStu_id(rs.getString("stu_id"));
                        account.setPassword(rs.getString("password"));
                        account.setName(rs.getString("name"));
                        account.setPhonenumber(rs.getString("phonenumber"));
                        account.setWarning(rs.getInt("warning"));
                        account.setPower(rs.getString("power"));
                        account.setAllowed(rs.getBoolean("allowed"));

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
                pstmt.setString(1, account.getStu_id());
                pstmt.setString(2, account.getPassword());
                pstmt.setString(3, account.getName());
                pstmt.setString(4, account.getPhonenumber());
                pstmt.setInt(5, account.getWarning());
                pstmt.setString(6, account.getPower());
                pstmt.setBoolean(7, account.isAllowed());

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
                String sql = "INSERT INTO reservation VALUES (?,?,?,?,?,?)"; //모든 컬럼에 값을 넣으므로 컬럼명 생략.
                PreparedStatement pstmt = con.prepareStatement(sql);
                //VALUES의 ?에 값을 바인딩. (바인딩 : ?에 들어갔어야 하는 원래 데이터 값을 입력.
                pstmt.setInt(1, reservation.getReser_number());
                pstmt.setInt(2, reservation.getSeat_number());
                pstmt.setString(3, reservation.getId());
                pstmt.setString(4, reservation.getClassnumber());
                pstmt.setString(5, reservation.getReser_starttime());
                pstmt.setString(6, reservation.getReser_endtime());

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
                String sql = "SELECT * FROM reservation";
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

        return count;
    }
    
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
   
    
    public boolean UpdateToken(TokenDTO token, String oldToken, String newToken) {
        boolean result = false;

        if (this.connect()) {
            try {
                String sql = "UPDATE token SET token_value="+newToken+" WHERE token_value =" + oldToken; 
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
    
    public boolean UpdateTimetable(ClassTimetableDTO classtimetable, String classnumber,
            String time1, String time2,String time3,String time4,String time5,String time6,String time7,String time8) {
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
    
        public void insert(String s1, String s2, String s3){
             if (this.connect()) {
		String sql="insert into board(title,content,type) values(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, s1);
			st.setString(2, s2);
			st.setString(3, s3);
			st.executeUpdate();
		} catch (SQLException e) {
		} 
                } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
	}
	
	public void boardUpdate(String s1, String s2, String s3, int s4){
             if (this.connect()) {
        
		String sql="update board set title=? ,content=? ,type=? where no=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, s1);
			st.setString(2, s2);
			st.setString(3, s3);
                        st.setInt(4, s4);
			st.executeUpdate();
		} catch (SQLException e) {
		} 
                } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
	}

	public void boardDelete(String s1) {
             if (this.connect()) {
        
		String sql="delete from board where no=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, s1);
			st.executeUpdate();
		} catch (SQLException e) {
		} 
                } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
	}

	public ArrayList<BoardDTO> boardSelect() throws SQLException ,NamingException {
            ArrayList<BoardDTO> a = new ArrayList<>();
             if (this.connect()) {
        
		
		String sql="select * from board";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				BoardDTO b = new BoardDTO();
				b.setBNUM(rs.getInt(1));
				b.setSID(rs.getString(2));
                                b.setTITLE(rs.getString(3));
                                b.setCONTENT(rs.getString(4));
                                b.setDATE(rs.getString(5));
                                b.setBPS(rs.getString(6));
			}
		} catch (SQLException e) {
		} 
                } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
		return a;
	}
        
        public StudentDTO AccountSelect(String s1){
            StudentDTO b = new StudentDTO();
             if (this.connect()) {
		
		String sql="select * from where sid"+s1;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
                            b.setSID(rs.getString(1));
                            b.setPASS(rs.getString(2));
			}
		} catch (SQLException e) {
		} 
		
                } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
             return b;
	}
        
        public void stopUpdate(boolean s1){
             if (this.connect()) {
        
		String sql="update board set stop=true where sid=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setBoolean(1, s1);
			st.executeUpdate();
		} catch (SQLException e) {
		} 
                } else {
            System.out.println("데이터베이스 연결에 실패");
            System.exit(0);
        }
	}
}
