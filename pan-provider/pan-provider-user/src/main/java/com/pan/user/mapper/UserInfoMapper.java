package com.pan.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.pan.mybatis.BcMapper;
import com.pan.user.model.domain.UserInfo;

import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper extends BcMapper<UserInfo> {
	@Select("SELECT id,user_name userName,user_role_ids userRoleIds from aw_user_info  " + 
	    		"WHERE user_role_ids LIKE  CONCAT(CONCAT('%',#{userInfo.userRoleIds}),'%')")
	List<UserInfo> selectUserInfoByRoleIds(@Param("userInfo")UserInfo userInfo);
   

	
	/**
	 * 根据userInfoId 添加角色信息
	 * @Title: updateUserInfoRoleIds  
	 * @Description: 
	 * @CreateTime 2018年7月27日下午2:35:27
	 * @Author ttsf-pzg
	 * @param userInfoId
	 * @param roleIds
	 * @return
	 */
	@Update("update aw_user_info set user_role_ids=CONCAT( user_role_ids,#{roleIds})  where id = #{userInfoId}")
	public void updateAddUserInfoRoleIds(@Param("roleIds")String roleIds,@Param("userInfoId") int userInfoId);
	
	/**
	 * 根据userInfoId 删除角色信息
	 * @Title: updataDelUserInfoRoleIds  
	 * @Description: 
	 * @CreateTime 2018年7月27日下午2:46:23
	 * @Author ttsf-pzg
	 * @param userInfoId
	 * @param roleIds
	 * @return
	 */
	@Update("update aw_user_info set user_role_ids=REPLACE(user_role_ids,#{roleId},'')  where id = #{userInfoId}")
	public void updataDelUserInfoRoleIds(@Param("roleId")String roleId,@Param("userInfoId") int userInfoId);

    /**
     * 查询列表数据
     * @Title: selectByList  
     * @Description: 
     * @CreateTime 2018年8月8日上午10:07:40
     * @Author ttsf-lfs
     * @return
     */
	@Select("SELECT id,user_name userName,user_role_ids userRoleIds, user_nike_name UserNikeName, user_sex userSex, user_phone userPhone,user_status userStatus,head_img headImg from aw_user_info ")
	List<UserInfo> selectByList();
	
	
}