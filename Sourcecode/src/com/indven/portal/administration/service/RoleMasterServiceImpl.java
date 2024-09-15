package com.indven.portal.administration.service;

import java.util.List;

import com.indven.framework.exception.IndvenException;
import com.indven.framework.service.BaseEntityCRUDService;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.administration.assembler.RoleMasterAssembler;
import com.indven.portal.administration.dao.RoleMasterDAOImpl;
import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.vo.RoleMasterVO;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.exception.MenuInfoException;

public class RoleMasterServiceImpl implements BaseEntityCRUDService<RoleMasterVO>{
    
    public RoleMasterVO saveRoleInfo(RoleMasterBean roleMasterBean, List<AccessControlBean> accessControlBeans) throws AdministrationException, MenuInfoException{
    	RoleMasterVO roleMasterVO = RoleMasterAssembler.convertEntityToVo(new RoleMasterDAOImpl().saveRoleInfo(roleMasterBean, accessControlBeans));
    	return roleMasterVO;
    }
    
    public List<RoleMasterVO> generateListOfAllRoles() throws AdministrationException {
    	RoleMasterDAOImpl roleDAOImpl = new RoleMasterDAOImpl();
    	List<RoleMasterVO> roleMasterVOsList = RoleMasterAssembler.convertEntitiesToVos(roleDAOImpl.findAll());
    	return roleMasterVOsList;
    }
    
    public RoleMasterVO findRoleById(Long id) throws AdministrationException {
    	RoleMasterVO roleMasterVO = new RoleMasterVO();
    	RoleMasterDAOImpl roleMasterDAOImpl = new RoleMasterDAOImpl();
    	RoleMasterBean roleMasterBean = roleMasterDAOImpl.findById(id);
		if (roleMasterBean != null) {
			roleMasterVO = RoleMasterAssembler.convertEntityToVo(roleMasterBean);
			roleMasterVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
		}
    	return roleMasterVO;
    }

	@Override
	public RoleMasterVO save(RoleMasterVO valueObject) throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleMasterVO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleMasterVO> findAll() throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleMasterVO update(RoleMasterVO valueObject) throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purge(RoleMasterVO valueObject) {
		// TODO Auto-generated method stub
		
	}
    
}
