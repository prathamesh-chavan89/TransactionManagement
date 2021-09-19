package com.hostEvents.transactionManagement.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENT_MASTER")
public class EventMasterEntity {

	@Id
	@Column(name = "EVENT_ID")
	@SequenceGenerator(sequenceName = "EVENT_MASTER_SEQ", name = "EVENT_MASTER_SEQ")
	private BigInteger eventId;

	@Column(name = "EVENT_NAME")
	private String eventName;

	@Column(name = "EVENT_DESC")
	private String eventDesc;

	@Column(name = "EVENT_DATE")
	private Date eventDate;

	@Column(name = "EVENT_HOST_ID")
	private BigInteger eventHostId;

	@Column(name = "EVENT_CREATER_ID")
	private BigInteger eventCreaterId;

	@Column(name = "REG_START_DT")
	private Date regStartDate;

	@Column(name = "REG_END_DT")
	private Date regEndDt;

	@Column(name = "MAX_SEATS_AVAILABLE")
	private BigInteger maxSeatsAvailable;

	@Column(name = "TICKET_DTLS_ID")
	private BigDecimal ticketDetailsId;
}
