package com.heendy.dto;

import java.sql.Date;
/**
 * @author 문석호
 * 일반 회원 정보 DTO
 */
public class MemberDTO {
	private int memberId;
	private String memberName;
	private String memberPassword;
	private String memberEmail;
	private String address;
	private int point;
	private Date memberRegDate;
	private int roleId;
	private String birthDate;

	public MemberDTO() {
	}
	
	//클라이언트에서 보낸 입력 인자를 받는 생성자
	public MemberDTO(String memberName, String memberPassword, String memberEmail, String address, int roleId, String birthDate) {
		super();
		this.memberName = memberName;
		this.memberPassword = memberPassword;
		this.memberEmail = memberEmail;
		this.address = address;
		this.roleId = roleId;
		this.birthDate = birthDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(Date memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
}
