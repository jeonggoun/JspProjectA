package com.member.study;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	//boardMember Table : mybatis FrameWork ▶ com.hanul.mybatis
	
	private static SqlSessionFactory sqlMapper;
	static { 
		try {
			String resource = "com/hanul/mybatis/SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SqlSessionFactory Exception!!!");
		}
	}//static(초기화블럭)
	
	//회원가입
	public int joinMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		int succ = 0;
		succ = session.insert("joinMember", dto);
		session.commit();
		session.close();
		return succ;
	}//joinMember()

	//회원여부
	public int isMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		MemberDTO isDto = null;
		isDto = session.selectOne("isMember", dto);
		session.close();
		
		int result = -1;	//실패
		if(isDto != null) {	//아이디 존재
			if (isDto.getMember_pw().equals(dto.getMember_pw())) {	//isDto → db, dto → 사용자 입력. 
				result = 1;	//비밀번호 일치
			}else {
				result = 0;	//비밀번호 불일치
			}
		}else {
			result = -1;	//아이디가 존재하지 않는다
		}
		return result;
	}//isMember()
	
	//전체회원 목록 검색
	public List<MemberDTO> getAllMember() {
		SqlSession session = sqlMapper.openSession();
		List<MemberDTO> list = null;
		list = session.selectList("getAllMember");
		session.close();
		return list;
	}//getAllMember()
	
	//회원정보 삭제
	public int deleteMember(String member_id) {
		SqlSession session = sqlMapper.openSession();
		int succ = 0;
		succ = session.delete("deleteMember", member_id);
		session.commit();
		session.close();
		return succ;
	}//deleteMember()

	//회원ID 검색(한 명의 회원정보를 검색)
	public MemberDTO getDetailMember(String member_id) {
		SqlSession session = sqlMapper.openSession();
		MemberDTO dto = null;
		dto = session.selectOne("getDetailMember", member_id);
		session.close();
		return dto;
	}//getDetailMember
	
	//회원 pw 검색
	public String getMember_pw(String id) {
		SqlSession session = sqlMapper.openSession();
		String member_pw = null;
		member_pw = session.selectOne("getMember_pw", id);
		session.close();
		return member_pw;
	}//getMember_pw()

	//회원정보 수정
	public int updateMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		int succ = 0;
		succ = session.update("updateMember", dto);
		session.commit();
		session.close();
		return succ;
	}//updateMember()
}//class