package com.silvermoongroup.atossyntel.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table( name = "DRIVER_INFO", uniqueConstraints = @UniqueConstraint(name = "pr_driver_info_id", columnNames = {"id"}))
public class DriverInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DI_SEQ")
    @GenericGenerator(name = "DI_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "GM_SEQ"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")}
    )
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    @Size(min = 2, max = 100, message = "Size must be between 2 and 100")
    private String firstName;


    @Column(name = "LAST_NAME", nullable = false)
    @Size(min = 2, max = 100, message = "Size must be between 2 and 100")
    private String lastName;

    @Column(name = "CRED_SCORE", nullable = false)
    private Integer creditScore;

    @Column(name = "NR_ACC_VLTNS", nullable = false)
    private Integer nrOfAccidents;

    @Column(name = "LAST_ACC_VLTNS_DATE", nullable = false)
    private Date dateLastAccidentOrViolation;

    public DriverInformation() {
    }

    public DriverInformation(
            @Size(min = 2, max = 100, message = "Size must be between 2 and 100") String firstName,
            @Size(min = 2, max = 100, message = "Size must be between 2 and 100") String lastName,
            Integer creditScore, Integer nrOfAccidents, Date dateLastAccidentOrViolation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditScore = creditScore;
        this.nrOfAccidents = nrOfAccidents;
        this.dateLastAccidentOrViolation = dateLastAccidentOrViolation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getNrOfAccidents() {
        return nrOfAccidents;
    }

    public void setNrOfAccidents(Integer nrOfAccidents) {
        this.nrOfAccidents = nrOfAccidents;
    }

    public Date getDateLastAccidentOrViolation() {
        return dateLastAccidentOrViolation;
    }

    public void setDateLastAccidentOrViolation(Date dateLastAccidentOrViolation) {
        this.dateLastAccidentOrViolation = dateLastAccidentOrViolation;
    }
}
