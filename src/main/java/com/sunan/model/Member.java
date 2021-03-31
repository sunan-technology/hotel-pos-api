package com.sunan.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_table")
public class Member extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int memberId;

	@Column(name = "name")
	private String name;

	@Column(name = "card_no")
	private int cardNo;

	@Column(name = "contact_no")
	private int contactNo;

	@Column(name = "address")
	private String address;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "registeration_date")
	private Date registerationDate;

	@Column(name = "is_active")
	private String isActive;

	@OneToMany(mappedBy = "member")
	List<MemberLedger> memberLedgers;

	public Member(int memberId) {
		super();
		this.memberId = memberId;
	}

}
