package com.example.nagoyameshi.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.SignupForm;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.repository.RoleRepository;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	//	フォームから送信された会員情報をデータベースに登録する
	@Transactional
	public User create(SignupForm signupForm) {
		User user = new User();
		Role role = roleRepository.findByName("ROLE_GENERAL");
		
		user.setName(signupForm.getName());
		user.setKana(signupForm.getKana());
		user.setPostCode(signupForm.getPostCode());
		user.setAddress(signupForm.getAddress());
		user.setPhoneNumber(signupForm.getPhoneNumber());
		user.setEmail(signupForm.getEmail());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.setRole(role);
		user.setEnabled(false);
		
		return userRepository.save(user);
	}
	
	@Transactional
	public void update(UserEditForm userEditForm) {
		User user = userRepository.getReferenceById(userEditForm.getId());
		
		user.setName(userEditForm.getName());
		user.setKana(userEditForm.getKana());
		user.setPostCode(userEditForm.getPostCode());
		user.setAddress(userEditForm.getAddress());
		user.setPhoneNumber(userEditForm.getPhoneNumber());
		user.setEmail(userEditForm.getEmail());
		
		userRepository.save(user);
	}
	
	//メールアドレスが登録済みかどうかをチェックする
	public boolean isEmailRegistered(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}
	
	// パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
	public boolean isSamePassword(String password, String passwordConfirmation) {
		
		return password.equals(passwordConfirmation);
	}
	
	
	// ユーザーを有効にする
	@Transactional
	public void enableUser(User user) {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	// メールアドレスが変更されたかどうかをチェックする
	public boolean isEmailChanged(UserEditForm userEditForm) {
		User currentUser = userRepository.getReferenceById(userEditForm.getId());
		return !userEditForm.getEmail().equals(currentUser.getEmail());
	}
	
	@Transactional
	public void updateRole(Map<String, String> paymentIntentObject) {
	    String userId = paymentIntentObject.get("userId");
	    Optional<User> userOptional = userRepository.findById(Long.valueOf(userId).intValue());
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        String roleName = paymentIntentObject.get("roleName");
	        Role role = roleRepository.findByName(roleName);
	        user.setRole(role);
	        userRepository.save(user);
	        // ロールの変更後、再ログインさせる。
	        refreshAuthenticationByRole(roleName);
	    } else {
	        throw new RuntimeException("指定されたユーザーが見つかりません。");
	    }
	}

	
	// ユーザーのロールを更新・保存する
//	@Transactional
//	public void updateRole(Map<String, String> paymentIntentObject) {
//		String userId = paymentIntentObject.get("userId");
//		User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new RuntimeException("指定されたユーザーが見つかりません。"));
//		String roleName = paymentIntentObject.get("roleName");
//		Role role = roleRepository.findByName(roleName);
//		user.setRole(role);
//		
//		userRepository.save(user);
//		// ロールの変更後、再ログインさせる。
//		refreshAuthenticationByRole(roleName);
//    }
	
	public void refreshAuthenticationByRole(String newRole) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(newRole));
		Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}
	
	
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

   
    public List<User> getUsersByRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        return userRepository.findByRole(role);
    }
	
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
//	@PostMapping("/create")
//	public String create(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
//	    User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
//	    UserService.updateRole(user, "ROLE_VIP");
//	    UserService.refreshAuthenticationByRole("ROLE_VIP");
//
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    if (authentication != null) {
//	        new SecurityContextLogoutHandler().logout(request, response, authentication);
//	    }
//	    request.getSession().invalidate();
//
//	    return "redirect:/login?reserved";
//	}
//	
//	@PostMapping("/delete")
//	public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes) {
//	    User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
//	    UserService.updateRole(user, "ROLE_GENERAL");
//	    UserService.refreshAuthenticationByRole("ROLE_GENERAL");
//	    redirectAttributes.addFlashAttribute("successMessage", "有料プランを解約しました。");
//	    return "redirect:/";
//	}


}