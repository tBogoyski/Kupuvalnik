package com.project.kupuvalnik.service.impl;

import com.project.kupuvalnik.models.binding.OfferAddBindingModel;
import com.project.kupuvalnik.models.entity.OfferEntity;
import com.project.kupuvalnik.models.entity.PictureEntity;
import com.project.kupuvalnik.models.entity.UserEntity;
import com.project.kupuvalnik.models.entity.UserRoleEntity;
import com.project.kupuvalnik.models.entity.enums.OfferCategoryEnum;
import com.project.kupuvalnik.models.entity.enums.UserRoleEnum;
import com.project.kupuvalnik.models.service.OfferAddServiceModel;
import com.project.kupuvalnik.models.service.OfferUpdateServiceModel;
import com.project.kupuvalnik.models.view.OfferDetailsView;
import com.project.kupuvalnik.models.view.OfferSummaryView;
import com.project.kupuvalnik.repository.OfferRepository;
import com.project.kupuvalnik.repository.PictureRepository;
import com.project.kupuvalnik.repository.UserRepository;
import com.project.kupuvalnik.service.CloudinaryService;
import com.project.kupuvalnik.service.OfferService;
import com.project.kupuvalnik.web.exception.OfferNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, UserRepository userRepository, CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public List<OfferSummaryView> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(offer -> {

                    var offerSummaryView =
                            modelMapper.map(offer, OfferSummaryView.class);

                    if (offer.getPictures().isEmpty()) {
                        offerSummaryView.setImageUrl("http://www.dumpaday.com/wp-content/uploads/2019/12/pictures-10-2.jpg");
                    } else {
                        offerSummaryView.setImageUrl(offer.getPictures().stream().findFirst().get().getUrl());
                    }

                    return offerSummaryView;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OfferAddServiceModel addOffer(OfferAddBindingModel offerAddBindingModel, String ownerId, PictureEntity picture) {

        var userEntity = userRepository.findByUsername(ownerId).orElseThrow(null);

        pictureRepository.save(picture);

        var offerAddServiceModel = modelMapper.map(offerAddBindingModel,
                OfferAddServiceModel.class);

        var newOffer = modelMapper.map(offerAddServiceModel, OfferEntity.class);

        newOffer.setSeller(userEntity);
        newOffer.setPictures(Set.of(picture));

        var savedOffer = offerRepository.save(newOffer);

        return modelMapper.map(savedOffer, OfferAddServiceModel.class);
    }

    @Override
    public boolean isOwner(String username, Long id) {

        Optional<OfferEntity> offerOpt = offerRepository.findById(id);
        Optional<UserEntity> caller = userRepository.findByUsername(username);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            var offerEntity = offerOpt.get();
            return isAdmin(caller.get()) || offerEntity.getSeller().getUsername().equals(username);
        }
    }

    @Override
    public void initializeOffers() {
        if (offerRepository.count() == 0) {
            var picture = new PictureEntity();
            picture.setUrl("https://res.cloudinary.com/kupuvalnik-cloud/image/upload/v1638395274/sweatshirt_tmcq1h.jpg");

            var picture2 = new PictureEntity();
            picture2.setUrl("https://res.cloudinary.com/kupuvalnik-cloud/image/upload/v1638395273/phone_bmpowi.jpg");


            pictureRepository.saveAll(List.of(picture, picture2));

            var offer1 = new OfferEntity();

            offer1.setSeller(userRepository.findByUsername("dummy").orElse(null));
            offer1.setCategory(OfferCategoryEnum.CLOTHES);
            offer1.setName("Grey Sweatshirt");
            offer1.setPictures(Set.of(picture));
            offer1.setCity("Plovdiv");
            offer1.setCountry("Bulgaria");
            offer1.setDescription("Its new and has never been used. The size is M.");
            offer1.setPhone("1234567890");
            offer1.setPrice(BigDecimal.valueOf(50));

            var offer2 = new OfferEntity();

            offer2.setSeller(userRepository.findByUsername("admin").orElse(null));
            offer2.setCategory(OfferCategoryEnum.ELECTRONICS);
            offer2.setName("Smartphone");
            offer2.setPictures(Set.of(picture2));
            offer2.setCity("Sofia");
            offer2.setCountry("Bulgaria");
            offer2.setDescription("Selling the newest smartphone on the market!");
            offer2.setPhone("1234567890");
            offer2.setPrice(BigDecimal.valueOf(500.00));

            offerRepository.saveAll(List.of(offer1, offer2));


        }
    }

    @Override
    public PictureEntity createPictureEntity(MultipartFile picture) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(picture);

        var pictureEntity = new PictureEntity();

        pictureEntity.setPublicId(uploaded.getPublicId());
        pictureEntity.setUrl(uploaded.getUrl());

        return pictureEntity;
    }

    @Override
    public OfferDetailsView findById(Long id, String currentUser) {

        return offerRepository.findById(id)
                .map(offerEntity -> mapDetailsView(currentUser, offerEntity)).get();
    }

    @Override
    public void updateOffer(OfferUpdateServiceModel serviceModel) {

        var offerEntity = offerRepository.findById(serviceModel.getId()).orElseThrow(() ->
                new OfferNotFoundException(serviceModel.getId()));


        offerEntity.setName(serviceModel.getName());
        offerEntity.setCategory(serviceModel.getCategory());
        offerEntity.setPictures(serviceModel.getPictures());
        offerEntity.setPrice(serviceModel.getPrice());
        offerEntity.setDescription(serviceModel.getDescription());

        offerRepository.save(offerEntity);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<OfferSummaryView> getLatestOffers() {


        return offerRepository.findLatestOffers()
                .stream()
                .map(offer -> {

                    var offerSummaryView =
                            modelMapper.map(offer, OfferSummaryView.class);

                    if (offer.getPictures().isEmpty()) {
                        offerSummaryView.setImageUrl("http://www.dumpaday.com/wp-content/uploads/2019/12/pictures-10-2.jpg");
                    } else {
                        offerSummaryView.setImageUrl(offer.getPictures().stream().findFirst().get().getUrl());
                    }

                    return offerSummaryView;
                })
                .collect(Collectors.toList());
    }

    private boolean isAdmin(UserEntity user) {
        return user.getRoles().stream().map(UserRoleEntity::getRole).anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    private OfferDetailsView mapDetailsView(String currentUser, OfferEntity offerEntity) {
        //the problem was that model mapper could not let me map
        //the setSeller property because it's made out of 2 instances
        //if something with the mapping breaks it's probably from here
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);


        var offerDetailsView = this.modelMapper.map(offerEntity, OfferDetailsView.class);
        offerDetailsView.setCanDeleteAndUpdate(isOwner(currentUser, offerEntity.getId()));
        offerDetailsView.setPictureUrl(offerEntity.getPictures().stream().findFirst().get().getUrl());
        offerDetailsView.setSellerName(offerEntity.getSeller().getFirstName() +
                " " + offerEntity.getSeller().getLastName());

        return offerDetailsView;
    }
}
