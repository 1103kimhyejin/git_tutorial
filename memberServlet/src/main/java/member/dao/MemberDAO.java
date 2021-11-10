package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import member.bean.MemberDTO;

public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";

	private static MemberDAO instance = null;

	public static MemberDAO getInstance() {
		if (instance == null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();// 생성
			}
		}

		return instance;
	}

	public MemberDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void write(MemberDTO memberDTO) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);// 생성
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());

			pstmt.executeUpdate();// 실행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public String login(String id, String pwd) {
		String name=null;
		String sql = "select * from member where id=? and pwd=?";
		getConnection();

		try {
			pstmt = conn.prepareStatement(sql);// 생성
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			rs = pstmt.executeQuery();

			if (rs.next()) name = rs.getString("name");
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return name;
	}

	public boolean isCheckedId(String id) {
		boolean exist = false;
		String sql = "select * from member where id=?";
		getConnection();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) exist =true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exist;
	}

	


}

	


/*
 * package member.dao;
 * 
 * import java.sql.Connection; import java.sql.DriverManager; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.sql.SQLException;
 * 
 * import member.bean.MemberDTO;
 * 
 * public class MemberDAO { //얘가 서블릿힘드니까 드라이버로딩, 커넥션, 프리페어드스테이트먼트 대신해준다
 * 
 * private Connection conn=null; private PreparedStatement pstmt; private
 * ResultSet rs;
 * 
 * private String driver = "oracle.jdbc.driver.OracleDriver"; private String url
 * = "jdbc:oracle:thin:@localhost:1521:xe"; private String username = "c##java";
 * private String password = "bit";
 * 
 * private static MemberDAO instance=null;//객체명 아무거나 해도되지만 인스턴스로 그냥
 * 
 * public static MemberDAO getInstance() { if(instance==null) {//null인 경우는 처음 한번
 * 뿐이니까 synchronized(MemberDAO.class) { instance = new MemberDAO();
 * 
 * } } return instance; }
 * 
 * public MemberDAO() { //이거를 빼도 돌아간다?? 자르파일이 있으면 드라이버 로딩을 안해도 된다 사실 자동으로 해준다
 * try { Class.forName(driver); } catch (ClassNotFoundException e) {
 * e.printStackTrace(); } }
 * 
 * public void getConnection() { try { conn = DriverManager.getConnection(url,
 * username, password); } catch (SQLException e) { e.printStackTrace(); } }
 * 
 * public void write(MemberDTO memberDTO) { String sql =
 * "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?, sysdate)"; //sysdate는
 * 테이블에 로그타임이라는 컬럼이 있어서 넣어준것! getConnection(); try { pstmt =
 * conn.prepareStatement(sql);//생성 pstmt.setString(1, memberDTO.getName());
 * pstmt.setString(2, memberDTO.getId()); pstmt.setString(3,
 * memberDTO.getPwd()); pstmt.setString(4, memberDTO.getGender());
 * pstmt.setString(5, memberDTO.getEmail1()); pstmt.setString(6,
 * memberDTO.getEmail2()); pstmt.setString(7, memberDTO.getTel1());
 * pstmt.setString(8, memberDTO.getTel2()); pstmt.setString(9,
 * memberDTO.getTel3()); pstmt.setString(10, memberDTO.getZipcode());
 * pstmt.setString(11, memberDTO.getAddr1()); pstmt.setString(12,
 * memberDTO.getAddr2());
 * 
 * pstmt.executeUpdate();//실행
 * 
 * } catch (SQLException e) { e.printStackTrace();
 * 
 * } finally { try {
 * 
 * if (pstmt != null) pstmt.close(); if (conn != null) conn.close(); } catch
 * (SQLException e) { e.printStackTrace(); } } } }
 */