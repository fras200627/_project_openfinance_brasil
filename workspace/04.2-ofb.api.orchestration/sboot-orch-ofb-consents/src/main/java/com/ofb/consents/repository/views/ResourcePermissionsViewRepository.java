package com.ofb.consents.repository.views;

import com.ofb.consents.model.ResourcePermissionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourcePermissionsViewRepository extends JpaRepository<ResourcePermissionsModel, String>,
                                                    JpaSpecificationExecutor<ResourcePermissionsModel> {


    @Query("SELECT a.permission FROM ResourcePermissionsView a")
    List<String> findAllPermissionNames();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.qualifiedforpf = 'Y'")
    List<String> findAllPermissionNamesByQualifiedPF();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.resourcestatus = 'AVAILABLE'")
    List<String> findAllPermissionsNameEnabled();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.resourcestatus = 'UNAVAILABLE'")
    List<String> findAllPermissionsNameDisabled();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.resourcestatus = 'AVAILABLE'")
    List<ResourcePermissionsModel> findAllPermissionsEnabled();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.resourcestatus = 'UNAVAILABLE'")
    List<ResourcePermissionsModel> findAllPermissionsDisabled();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.resourcetype = :resourcetype")
    List<ResourcePermissionsModel> findAllPermissionsByResourceType(@Param("resourcetype")  String resourceType);

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.permission = :permission")
    ResourcePermissionsModel findPermissionByPermissionName(@Param("permission")  String permission);

    @Query("SELECT a.permissionid FROM ResourcePermissionsView a WHERE a.permission = :permission GROUP BY a.permission, a.permissionid")
    List<String> findPermissionsIdByPermissionName(@Param("permission")  String permission);

    @Query("SELECT a.control FROM ResourcePermissionsView a WHERE a.permission = :permission GROUP BY a.permission, a.control")
    String findPermissionGroupByByPermissionName(@Param("permission")  String permission);

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY'")
    List<String> findAllPermissionsNameByMandatory();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY'")
    List<ResourcePermissionsModel> findAllPermissionsByMandatory();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY' AND a.qualifiedforpf = 'Y'")
    List<String> findAllPermissionsNameByMandatoryQualifiedPF();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY' AND a.qualifiedforpf = 'Y'")
    List<ResourcePermissionsModel> findAllPermissionsByMandatoryQualifiedPF();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY' AND a.qualifiedforpj = 'Y'")
    List<String> findAllPermissionsNameByMandatoryQualifiedPJ();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY' AND a.qualifiedforpj = 'Y'")
    List<ResourcePermissionsModel> findAllPermissionsByMandatoryQualifiedPJ();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY-OPTIONAL'")
    List<String> findAllPermissionsNameByMandatoryOptional();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY-OPTIONAL'")
    List<ResourcePermissionsModel> findAllPermissionsByMandatoryOptional();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY-OPTIONAL' AND a.qualifiedforpf = 'Y'")
    List<String> findAllPermissionsNameByMandatoryOptionalPF();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY-OPTIONAL' AND a.qualifiedforpf = 'Y'")
    List<ResourcePermissionsModel> findAllPermissionsByMandatoryOptionalPF();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY-OPTIONAL' AND a.qualifiedforpj = 'Y'")
    List<String> findAllPermissionsNameByMandatoryOptionalPJ();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.control = 'MANDATORY-OPTIONAL' AND a.qualifiedforpj = 'Y'")
    List<ResourcePermissionsModel> findAllPermissionsByMandatoryOptionalPJ();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.control = 'GROUP-CONTROL'")
    List<String> findAllPermissionsNameByGroupControl();

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.control = 'GROUP-CONTROL'")
    List<ResourcePermissionsModel> findAllPermissionsByGroupControl();

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag%")
    List<String> findAllPermissionsNameByPermissionFlag(@Param("permissionflag")  String permissionFlag);

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag%")
    List<ResourcePermissionsModel> findAllPermissionsByPermissionFlag(@Param("permissionflag")  String permissionFlag);

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag% AND a.control = 'GROUP-CONTROL'")
    List<String> findAllPermissionsNameByPermissionFlagGroupControl(@Param("permissionflag")  String permissionFlag);

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag% AND a.control = 'GROUP-CONTROL'")
    List<ResourcePermissionsModel> findAllPermissionsByPermissionFlagGroupControl(@Param("permissionflag")  String permissionFlag);

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag% AND a.control = 'GROUP-ITEM'")
    List<String> findAllPermissionsNameByPermissionFlagGroupItem(@Param("permissionflag")  String permissionFlag);

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag% AND a.control = 'GROUP-ITEM'")
    List<ResourcePermissionsModel> findAllPermissionsByPermissionFlagGroupItem(@Param("permissionflag")  String permissionFlag);

    @Query("SELECT a.permission FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag% AND a.control = 'UNIQUE'")
    List<String> findAllPermissionsNameByPermissionFlagGroupUnique(@Param("permissionflag")  String permissionFlag);

    @Query("SELECT a FROM ResourcePermissionsView a WHERE a.permission LIKE :permissionflag% AND a.control = 'UNIQUE'")
    List<ResourcePermissionsModel> findAllPermissionsByPermissionFlagGroupUnique(@Param("permissionflag")  String permissionFlag);

}
