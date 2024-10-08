package com.example.nagoyameshi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.PasswordChangeForm;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.PasswordService;

@Controller
@RequestMapping("/password")
public class PasswordController {
    private final PasswordService passwordService;
    private final UserRepository userRepository;

    public PasswordController (PasswordService passwordService, UserRepository userRepository) {
        this.passwordService = passwordService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        return "user/password";
    }

    //新しいパスワードが確認用と一致しない場合はエラーを表示、一致する場合はパスワードを更新する。パスワード変更完了後に /password/updated にリダイレクトする    
    @PostMapping("/update")
    public String changePassword(@ModelAttribute @Validated PasswordChangeForm passwordChangeForm,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (bindingResult.hasErrors()) {
            return "user/password";
        }

        User user = userRepository.getReferenceById(userDetails.getId());

        if (!passwordService.isSamePassword(passwordChangeForm.getPassword(), passwordChangeForm.getPasswordConfirmation())) {
            bindingResult.rejectValue("passwordConfirmation", "error.passwordConfirmation", "パスワードが一致しません");
            return "user/password";
        }

        passwordService.updatePassword(user, passwordChangeForm.getPassword());
        redirectAttributes.addFlashAttribute("message", "パスワードを変更しました");

//        return "redirect:/";
        return "redirect:/password/updated";
    }
    @GetMapping("/updated")
    public String passwordUpdated() {
    	return "user/password-updated";
    }

}