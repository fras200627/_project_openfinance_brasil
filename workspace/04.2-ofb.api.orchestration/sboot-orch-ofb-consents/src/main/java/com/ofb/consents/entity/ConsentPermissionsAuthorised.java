package com.ofb.consents.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "ConsentPermissionsAuthorised")
@Table(schema = "OFB", name = "CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED")
@Data @Builder
@AllArgsConstructor @RequiredArgsConstructor
@EqualsAndHashCode(of= "id")
public class ConsentPermissionsAuthorised {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SQ_OFB_SYSTEM")
    @SequenceGenerator(schema= "OFB", name= "SQ_OFB_SYSTEM", allocationSize = 1, sequenceName = "SQ_OFB_SYSTEM")
    @Column(name = "CONSENTPERMISSIONAUTHORISEDID")
    private Long id;

    @Column(name = "CONSENTID")
    private String consentId;

    @Column(name = "PERMISSIONID")
    private Long permissionId;

    @Column(name = "CREATE_AT")
    private Timestamp createAt;

    @Column(name = "MODIFY_AT")
    private Timestamp modifyAt;

    @Column(name = "USER_CODE")
    private String userCode;

}
