package com.ktdsuniversity.edu.pms.memo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.pms.memo.dao.MemoDao;
import com.ktdsuniversity.edu.pms.memo.vo.MemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.MemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;

@Service
public class MemoServiceImpl implements MemoService{
	
	@Autowired
	private MemoDao memoDao;

	@Override
	public MemoListVO getSentMemoAllsearch(SearchMemoVO searchMemoVO) {
		
		int sentMemoCount = this.memoDao.getSentMemoAllCount(searchMemoVO);
		searchMemoVO.setPageCount(sentMemoCount);
		
		List<MemoVO> memoList = this.memoDao.getAllSentMemo(searchMemoVO);
		
		MemoListVO memoListVO = new MemoListVO();
		memoListVO.setMemoCnt(sentMemoCount);
		memoListVO.setMemoList(memoList);
		
		return memoListVO;
	}

	@Override
	public MemoListVO getStorageMemoAllsearch(SearchMemoVO searchMemoVO) {
		int storageMemoCount = this.memoDao.getStorageMemoAllCount(searchMemoVO);
		searchMemoVO.setPageCount(storageMemoCount);
		List<MemoVO> memoList = this.memoDao.getAllStorageMemo(searchMemoVO);
		
		MemoListVO memoListVO = new MemoListVO();
		memoListVO.setMemoCnt(storageMemoCount);
		memoListVO.setMemoList(memoList);
		
		return memoListVO;
	}

	@Override
	public MemoListVO getReceiveMemoAllsearch(SearchMemoVO searchMemoVO) {
		int receiveMemoCount = this.memoDao.getReceiveMemoAllCount(searchMemoVO);
		searchMemoVO.setPageCount(receiveMemoCount);
		List<MemoVO> memoList = this.memoDao.getAllReceiveMemo(searchMemoVO);
		
		MemoListVO memoListVO = new MemoListVO();
		memoListVO.setMemoCnt(receiveMemoCount);
		memoListVO.setMemoList(memoList);
		
		return memoListVO;
	}
	
	
	@Transactional
	@Override
	public boolean writeNewMemo(MemoVO memoVO) {
	
		List<String> rcvIdList = Arrays.asList(memoVO.getRcvId().split(","));

		int insertedCount = 0;
        for (String id : rcvIdList) {
            // 각 이메일 주소에 쪽지를 보냅니다.
        	memoVO.setRcvId(id);
        	insertedCount += this.memoDao.writeNewMemo(memoVO);
        }
        // 모든 쪽지를 성공적으로 보냈을 경우에만 커밋합니다.
        return rcvIdList.size() == insertedCount;
	}

	// 조회한 결과가 없다면? 설정해주기
	@Transactional
	@Override
	public MemoVO getOneMemo(String memoId, String empId) {
		MemoVO memoVO = this.memoDao.selectOneMemo(memoId);
		
		// 조회하면 읽음으로 바뀜
		if(memoVO.getReadYn().equals("N") && memoVO.getRcvId().equals(empId)) {
			this.memoDao.changeViewStatus(memoId);
		}
		
		return memoVO;
	}

	@Transactional
	@Override
	public boolean deleteOneMemo(String id) {
		
		int deletedCount = this.memoDao.deleteOneMemo(id);
		
		return deletedCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteManyMemo(List<String> memoIds) {
		
		int deletedCount = this.memoDao.deleteManyMemo(memoIds);
		
		return deletedCount > 0;
	}

	@Transactional
	@Override
	public boolean saveOneMemo(MemoVO memoVO) {
		int savedCount = this.memoDao.saveOneMemo(memoVO);
		return savedCount > 0;
	}

	@Override
	public MemoListVO getReceiveMemoReadYsearch(SearchMemoVO searchMemoVO) {
		int receiveMemoCount = this.memoDao.getReceiveMemoReadYCount(searchMemoVO);
		searchMemoVO.setPageCount(receiveMemoCount);
		List<MemoVO> memoList = this.memoDao.getReadYReceiveMemo(searchMemoVO);
		
		MemoListVO memoListVO = new MemoListVO();
		memoListVO.setMemoCnt(receiveMemoCount);
		memoListVO.setMemoList(memoList);
		
		return memoListVO;
	}

	@Override
	public MemoVO findMemoById(String memoId) {
		MemoVO memoVO = this.memoDao.findMemo(memoId);
		return memoVO;
	}
	
	
}
