package com.dalrada.role.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dalrada.role.integration.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	/*@Query(value="UPDATE  role SET role_name = :roleName , "
			+ " status =:status , created_by =:createdBy"
			+ " WHERE  role_id =:roleId")
	public void updateUser(@Param(value = "roleName") String roleName ,
	@Param(value = "status") Integer status ,
	@Param(value = "createdBy") String createdBy ,
	@Param(value="roleId")Long roleId
	);*/

/*    @Modifying(clearAutomatically = true)
	@Query("UPDATE dalrada.role r SET r.role_name =?1 WHERE r.role_id=?2")
    Long UpdateRole(String roleName,Long roleId);*/

/*    @Modifying(clearAutomatically = true)
    @Query("UPDATE role r SET r.status =:status WHERE r.role_id=:roleId")
    Long ChangeStatus(
            @Param("status") Integer status,
            @Param("roleId") Long roleId
    );*/

}
