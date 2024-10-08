//package com.example.nagoyameshi.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.example.nagoyameshi.entity.Role;
//import com.example.nagoyameshi.entity.User;
//
//public interface UserRepository extends JpaRepository<User, Integer> {
//    // メールアドレスに一致するuserを検索
//    User findByEmail(String email);
//
//    // 氏名またはフリガナで会員を検索
//    Page<User> findByNameLikeOrKanaLikeOrEmailLike(String nameKeyword, String kanaKeyword, String emailKeyword, Pageable pageable);
//
//    // ロール名（roleName）と同じユーザーを検索する
//    List<User> findByRoleName(String roleName);
//
//    @Query("SELECT u FROM User u JOIN u.role r WHERE r.name = :roleName")
//    List<User> findAllByRoleName(@Param("roleName") String roleName);
//    
//    // ロールオブジェクトと同じユーザーを検索する
//    List<User> findByRole(Role role);
//
//    // 指定されたユーザーID（id）に該当するユーザーを検索する
//    Optional<User> findById(Integer id);
//
//	Optional<User> findById(Long id);
//}


package com.example.nagoyameshi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	//メールアドレスに一致するuserを検索
	public User findByEmail(String email);
	//氏名またはフリガナで会員を検索
	public Page<User> findByNameLikeOrKanaLikeOrEmailLike(String nameKeyword, String kanaKeyword, String emailKeyword, Pageable pageable);
	//ロール名（roleName）と同じユーザーを検索する
	public List<User> findByRoleName(String roleName);
	//指定されたユーザーID（id）に該当するユーザーを検索する
	Optional<User> findById(Long id);
	// Roleに基づいてユーザーを取得する
	List<User> findByRole(Role role);
}