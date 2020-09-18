package com.training.meeting.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event extends BaseEntity{

}
