package com.ktdsuniversity.edu.pms.member.vo;

public class MemberVO {

	private String memId; // 회원의 고유 ID
	private String pwd; // 회원의 암호화 된 비밀번호
	private String salt; // 단방향 암호화를 위한 SALT값
//	private String symKey; // 양방향 암호화를 위한 대칭키
	private String name; // 회원의 이름
	private String cont; // 회원의 연락처
	private String addr; // 회원의 거주지 주소
	private String email; // 회원의 이메일
	private String bday; // 회원의 생년월일
	private String jnDt; // 회원의 가입 날짜
	private String trmDt; // 회원의 해지 날짜
	private String phnPlanId; // 요금제에 대한 고유 ID
	private String payMthd; // 회원이 선택한 결제수단
	private int logTryCnt; // 로그인을 시도한 횟수, 로그인 성공 시 0으로 초기화
	private String idLockYn; // 'Y': 잠금, 'N': 풀림
	private String mgrYn; // 'Y': 관리자, 'N': 일반 회원
	private String wdrlYn; // 'Y': 탈퇴, 'N': 탈퇴 x

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

//	public String getSymKey() {
//		return symKey;
//	}
//
//	public void setSymKey(String symKey) {
//		this.symKey = symKey;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getJnDt() {
		return jnDt;
	}

	public void setJnDt(String jnDt) {
		this.jnDt = jnDt;
	}

	public String getTrmDt() {
		return trmDt;
	}

	public void setTrmDt(String trmDt) {
		this.trmDt = trmDt;
	}

	public String getPhnPlanId() {
		return phnPlanId;
	}

	public void setPhnPlanId(String phnPlanId) {
		this.phnPlanId = phnPlanId;
	}

	public String getPayMthd() {
		return payMthd;
	}

	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}

	public int getLogTryCnt() {
		return logTryCnt;
	}

	public void setLogTryCnt(int logTryCnt) {
		this.logTryCnt = logTryCnt;
	}

	public String getIdLockYn() {
		return idLockYn;
	}

	public void setIdLockYn(String idLockYn) {
		this.idLockYn = idLockYn;
	}

	public String getMgrYn() {
		return mgrYn;
	}

	public void setMgrYn(String mgrYn) {
		this.mgrYn = mgrYn;
	}

	public String getWdrlYn() {
		return wdrlYn;
	}

	public void setWdrlYn(String wdrlYn) {
		this.wdrlYn = wdrlYn;
	}

}
