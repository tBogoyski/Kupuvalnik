package com.project.kupuvalnik.service;

import com.project.kupuvalnik.models.binding.OfferAddBindingModel;
import com.project.kupuvalnik.models.entity.PictureEntity;
import com.project.kupuvalnik.models.service.OfferAddServiceModel;
import com.project.kupuvalnik.models.service.OfferUpdateServiceModel;
import com.project.kupuvalnik.models.view.OfferDetailsView;
import com.project.kupuvalnik.models.view.OfferSummaryView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OfferService {


    List<OfferSummaryView> getAllOffers();

    OfferAddServiceModel addOffer(OfferAddBindingModel offerAddBindingModel, String ownerId, PictureEntity picture);

    boolean isOwner(String username, Long id);

    void initializeOffers() throws IOException;

    PictureEntity createPictureEntity(MultipartFile picture) throws IOException;

    OfferDetailsView findById(Long id, String currentUser);

    void updateOffer(OfferUpdateServiceModel serviceModel);

    void deleteOffer(Long id);

    List<OfferSummaryView> getLatestOffers();
}
