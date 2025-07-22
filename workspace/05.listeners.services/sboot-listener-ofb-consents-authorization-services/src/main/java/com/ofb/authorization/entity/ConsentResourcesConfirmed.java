package com.ofb.authorization.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "ConsentResourcesConfirmed")
@Table(schema = "OFB", name = "CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= "id")
public class ConsentResourcesConfirmed {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SQ_OFB_SYSTEM")
    @SequenceGenerator(schema= "OFB", name= "SQ_OFB_SYSTEM", allocationSize = 1, sequenceName = "SQ_OFB_SYSTEM")
    @Column(name = "CONSENTRESOURCEID")
    private Long id;

    @Column(name = "RESOURCEID")
    private String resourceId;

    @Column(name = "RESOURCEIDSUMMARY")
    private String resourceIdSummary;

    @Column(name = "RESOURCETYPEID")
    private Long resourceTypeId;

    @Column(name = "RESOURCESTATUS")
    private Long resourceStatus;

    @Column(name = "CONSENTID")
    private String consentId;

    @Column(name = "CREATE_AT")
    private Timestamp createAt;

    @Column(name = "MODIFY_AT")
    private Timestamp modifyAt;

    @Column(name = "USER_CODE")
    private String userCode;

}
