package com.ktdsuniversity.edu.pms.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.beans.SHA;
import com.ktdsuniversity.edu.pms.member.dao.MemberDao;
import com.ktdsuniversity.edu.pms.member.dao.PhonePlanDao;
import com.ktdsuniversity.edu.pms.member.vo.MemberVO;
import com.ktdsuniversity.edu.pms.member.vo.PhonePlanVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PhonePlanDao phonePlanDao;

//	@Autowired
//	private AccountInformationDao accountInformationDao;
//
//	@Autowired
//	private CardInformationDao cardInformationDao;

	@Autowired
	private SHA sha;

	@Transactional
	@Override
	public boolean createMember(MemberVO memberVO) {

		String salt = this.sha.generateSalt();
		String pwd = this.sha.getEncrypt(memberVO.getPwd(), salt);
		memberVO.setPwd(pwd);
		memberVO.setSalt(salt);

		int createCardInfoSuccess = 0;
		int createAccountInfoSuccess = 0;

//		if (memberVO.getPayMthd().equals("카드")) {
//			CardInformationVO cardInformationVO = new CardInformationVO();
//			createCardInfoSuccess = cardInformationDao.createCardInfo(cardInformationVO);
//		} else if (memberVO.getPayMthd().equals("계좌이체")) {
//			AccountInformationVO accountInformationVO = new AccountInformationVO();
//			createAccountInfoSuccess = accountInformationDao.createAccountInfo(accountInformationVO);
//		}
		memberVO.setPayMthd("카드");
		memberVO.setPhnPlanId("1");

		int createMemberSuccess = memberDao.createMember(memberVO);
		System.out.println("createMemberSuccess: " + createMemberSuccess);

//		boolean allCreateSuccess = false;
//
//		if ((createCardInfoSuccess > 0 || createAccountInfoSuccess > 0) && createMemberSuccess > 0) {
//			allCreateSuccess = true;
//		}

		return createMemberSuccess > 1;
	}

	@Override
	public List<PhonePlanVO> getAllPhonePlans() {
		return this.phonePlanDao.selectAllPhonePlans();
	}

}
