package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.FavoriteRepository;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;        
    
    public FavoriteService(FavoriteRepository favoriteRepository) {        
        this.favoriteRepository = favoriteRepository;        
    }     
    
    @Transactional
    public void create(Shop shop, User user) {
        Favorite favorite = new Favorite();        
        
        favorite.setShop(shop);                
        favorite.setUser(user);
                    
        favoriteRepository.save(favorite);
    }
    
    public boolean isFavorite(Shop shop, User user) {
        return favoriteRepository.findByShopAndUser(shop, user) != null;
    }    
}




//@Service
//public class FavoriteService {
//    private final FavoriteRepository favoriteRepository;
//    private final ShopRepository shopRepository;
//
//    public FavoriteService(FavoriteRepository favoriteRepository, ShopRepository shopRepository) {
//        this.favoriteRepository = favoriteRepository;
//        this.shopRepository = shopRepository;
//    }
//    //ユーザーの詳細情報を取得してきて、そのユーザーに紐づくお気に入りを作成日時の昇順でページングして取得する
//    public Page<Favorite> getFavoritesByUser(UserDetailsImpl userDetailsImpl, Pageable pageable) {
//        return favoriteRepository.findByUserOrderByCreatedAt(userDetailsImpl.getUser(), pageable);
//    }
//    //ユーザーの詳細情報とお店のIDを受け取り、そのユーザーとお店の組み合わせに対応するお気に入りを削除する
//    public void deleteFavoriteByUserAndShop(UserDetailsImpl userDetailsImpl, Integer shopId) {
//        Shop shop = shopRepository.getReferenceById(shopId);
//        favoriteRepository.deleteByUserAndShop(userDetailsImpl.getUser(), shop);
//    }
//}

