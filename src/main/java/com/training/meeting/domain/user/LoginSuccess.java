package com.training.meeting.domain.user;

import com.training.meeting.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "login_success")
public class LoginSuccess extends BaseEntity {

    @ManyToOne
    private User user;

    private String sourceIp;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

}
