package com.project.kupuvalnik.web;

import com.project.kupuvalnik.models.binding.UserRegisterBindingModel;
import com.project.kupuvalnik.models.service.UserRegisterServiceModel;
import com.project.kupuvalnik.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    public RegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/users/register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/users/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel serviceModel =
                modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerAndLoginUser(serviceModel);

        return "redirect:/";

    }
}
