package com.project.kupuvalnik.repository;

import com.project.kupuvalnik.models.entity.OfferEntity;
import com.project.kupuvalnik.models.view.OfferSummaryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    @Query("SELECT o FROM OfferEntity o ORDER BY o.id DESC")
    List<OfferEntity> findLatestOffers();
}
