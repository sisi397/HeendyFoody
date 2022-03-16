package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.dao.MemberDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 문석호
 * 일반 회원 로그인 기능 Action 클래스
 */
public class LoginMemberAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	
	private final UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		String member_name = request.getParameter("id");
		String member_password = request.getParameter("pwd");
		//아이디 저장버튼 체크 여부를 확인하기 위한 변수
		String saveId = request.getParameter("idSaveYn");

		MemberDTO memberVO = new MemberDTO();
		//이름과 비밀번호를 memberVO객체에 담아 정보가 맞는지 검사한다.
		memberVO.setMemberName(member_name);
		memberVO.setMemberPassword(member_password);
		
		//로그인 정보가 맞다면 1 아니라면 0을 리턴 받음
		int result = memberDAO.isExisted(memberVO);
		
		//로그인 성공시
		if(result == 1) {	
			System.out.println("로그인 성공!");
			//아이디 저장을 체크했을 경우
			if(saveId != null) {	
				Cookie c = new Cookie("saveId", member_name);
				c.setMaxAge(60*60*24*3);	//3일간 쿠키에 저장
				response.addCookie(c);	//사용자 이름(id) 정보를 쿠키에 저장한다.
			}else {	//아이디 저장을 체크하지 않은 경우
				Cookie c = new Cookie("saveId", member_name);
				c.setMaxAge(0);
                response.addCookie(c);
			}
			memberVO = memberDAO.getMember(member_name); //성공했으면 멤버를 조회해서 속성들을 가져온다

			HttpSession session = request.getSession();
		
			userService.saveUser(memberVO, session);
			url = request.getContextPath() + "/main";	//로그인 성공 시 이동할 페이지 지정
			response.sendRedirect(url);
		}else {		//로그인 실패시
			System.out.println("로그인 실패");
			url="/pages/login/loginFail.jsp";
			request.setAttribute("msg", "아이디와 비밀번호를 다시 확인하세요.");
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
