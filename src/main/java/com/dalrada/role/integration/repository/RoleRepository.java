package com.dalrada.role.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query("UPDATE role SET role_name =:roleName WHERE role_id=:roleId")
    void UpdateRole(
           @Param("roleName") String roleName,
           @Param("roleId") Long roleId
    );

    @Query("UPDATE role SET status =:status WHERE role_id=:roleId")
    void ChangeStatus(
            @Param("status") Integer status,
            @Param("roleId") Long roleId
    );
	
	
}
