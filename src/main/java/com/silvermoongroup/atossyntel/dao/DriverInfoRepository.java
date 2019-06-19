package com.silvermoongroup.atossyntel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silvermoongroup.atossyntel.domain.DriverInformation;

public interface DriverInfoRepository extends JpaRepository<DriverInformation,Long> {

        List<DriverInformation> findByFirstNameAndLastName(String firstName, String lastName);
}
