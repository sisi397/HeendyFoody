package com.heendy.dto;
/**
 * @author 문석호
 *
 *	업체 회원 정보 DTO
 */
public class CompanyMemberDTO {
	private int companyId;
	private String companyName;
	private String companyPassword;
	private String companyTel;
	private String companyEmail;
	private int role_id;


	//클라이언트에서 보낸 입력 인자를 받는 생성자
	public CompanyMemberDTO(String companyName, String companyPassword, String companyTel, String companyEmail,
			int role_id) {
		super();
		this.companyName = companyName;
		this.companyPassword = companyPassword;
		this.companyTel = companyTel;
		this.companyEmail = companyEmail;
		this.role_id = role_id;
	}
	public CompanyMemberDTO() {
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPassword() {
		return companyPassword;
	}
	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

}
