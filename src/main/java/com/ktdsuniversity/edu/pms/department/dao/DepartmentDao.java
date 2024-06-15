package com.ktdsuniversity.edu.pms.department.dao;

import java.util.List;

import com.ktdsuniversity.edu.pms.department.vo.DepartmentVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;

public interface DepartmentDao {
	
	public String NAME_SPACE = "com.ktdsuniversity.edu.pms.department.dao.DepartmentDao";
	
	/**
	 * 현재 부서의 전체 갯수
	 * @return
	 */
	public int getDepartmentCount();
	
	/**
	 * 전체 부서의 정보 
	 * @return
	 */
	public List<DepartmentVO> getAllDepartment();
	
	/**
	 * 부서 만들기 
	 * @param deparmentVO 
	 * 필수파라미터 2개 deptname(부서의 이름), deptLeadId(부서장사원번호) 
	 * 자동으로 부여되는 값 deptId(부서 고유번호), deptCrDt(부서 생성일), delYn(삭제여부) 
	 * @return
	 */
	public int createNewDepartment(DepartmentVO deparmentVO);
	
	/**
	 * 부서 삭제하기
	 * @param id 필수파라미터 1개 deptId (부서의 고유번호)
	 * @return
	 */
	public int deleteOneDepartment(String id);
	
	/**
	 * 부서 수정하기
	 * @param departmentVO 
	 * 필수 파라미터 3개 deptName(부서의 이름) , deptLeadId(팀장의 고유번호), deptId(수정할 부서의 고유번호)
	 * @return
	 */
	public int updateOneDepartment(DepartmentVO departmentVO);

	/**
	 * 한 개의 부서 정보 가져오기 
	 * @param deptId 필수 파라미터 1개 deptId(부서의 고유번호)
	 * @return
	 */
	public List<DepartmentVO> getOnlyDepartment(String deptId);

	public DepartmentVO getOneDepartment(String departmentId);
	
	public String getDepartmentNameById(String deptId);
	
	public  int getDepartment(String id);

	public String  getOnlypstnid(String pstnid);

	public List<EmployeeVO> getEmpByDeptId(String deptId);

	public String getDeptIdByName(String deptName);  

}
