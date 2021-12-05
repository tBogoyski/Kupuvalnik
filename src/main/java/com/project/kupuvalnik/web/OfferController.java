package com.project.kupuvalnik.web;

import com.project.kupuvalnik.models.binding.OfferAddBindingModel;
import com.project.kupuvalnik.models.binding.OfferUpdateBindingModel;
import com.project.kupuvalnik.models.entity.enums.OfferCategoryEnum;
import com.project.kupuvalnik.models.service.OfferUpdateServiceModel;
import com.project.kupuvalnik.repository.PictureRepository;
import com.project.kupuvalnik.service.OfferService;
import com.project.kupuvalnik.service.impl.KupuvalnikUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;

    public OfferController(OfferService offerService, ModelMapper modelMapper, PictureRepository pictureRepository) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "all-offers";
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferAddBindingModel offerAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal KupuvalnikUser user) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerAddBindingModel", offerAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel", bindingResult);

            return "redirect:/offers/add";
        }
        var picture = offerService.createPictureEntity(offerAddBindingModel.getPicture());

        offerService.addOffer(offerAddBindingModel, user.getUsername(), picture);


        return "redirect:/offers/all";
    }

    @PreAuthorize("isOwner(#id)")
    @GetMapping("/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal KupuvalnikUser currentUser) {
        var offerDetailsView = offerService.findById(id, currentUser.getUserIdentifier());

        var offerUpdateBindingModel =
                modelMapper.map(offerDetailsView, OfferUpdateBindingModel.class);

        model.addAttribute("category", OfferCategoryEnum.values());
        model.addAttribute("offerModel", offerUpdateBindingModel);

        return "edit-offer";
    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @PatchMapping("/{id}/edit")
    public String editOffer(@PathVariable Long id, @Valid OfferUpdateBindingModel offerModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/edit";
        }
        var pictures = offerService.createPictureEntity(offerModel.getPicture());
        pictureRepository.save(pictures);

        var serviceModel = modelMapper.map(offerModel, OfferUpdateServiceModel.class);
        serviceModel.setPictures(Set.of(pictures));
        serviceModel.setId(id);

        offerService.updateOffer(serviceModel);

        return "redirect:/offers/details/" + id;
    }


    @GetMapping("/add")
    public String addOffer() {
        return "add-offer";
    }

    @GetMapping("/details/{id}")
    public String offerDetails(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("offer", offerService.findById(id, principal.getName()));

        return "offer-details";
    }

    @ModelAttribute("offerAddBindingModel")
    OfferAddBindingModel offerAddBindingModel() {
        return new OfferAddBindingModel();
    }


}
