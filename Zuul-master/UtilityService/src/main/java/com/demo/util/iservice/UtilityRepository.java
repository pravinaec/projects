package com.demo.util.iservice;

import com.demo.util.model.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtilityRepository extends JpaRepository<Utility, Integer> {

    Utility findByUtilityName( String utilityName );
}
