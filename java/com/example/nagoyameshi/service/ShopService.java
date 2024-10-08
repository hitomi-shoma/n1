package com.example.nagoyameshi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.form.ShopEditForm;
import com.example.nagoyameshi.form.ShopRegisterForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.ShopRepository;

//@Service
//public class ShopService {
//	private final ShopRepository shopRepository;
//	
//	public ShopService(ShopRepository shopRepository) {
//		this.shopRepository = shopRepository;
//	}
//	
//	@Transactional
//	public void create(ShopRegisterForm shopRegisterForm) {
//		Shop shop = new Shop();
//		MultipartFile imageFile = shopRegisterForm.getImageFile();
//		
//		if (!imageFile.isEmpty()) {
//			String imageName = imageFile.getOriginalFilename(); 
//			String hashedImageName = generateNewFileName(imageName);
//			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
//			copyImageFile(imageFile, filePath);
//			shop.setImageName(hashedImageName);
//		}
//		
//		shop.setName(shopRegisterForm.getName());
//		shop.setCategoryNumber(Integer.parseInt(shopRegisterForm.getCategory())); // 文字列を整数に
//		shop.setDescription(shopRegisterForm.getDescription());
//		shop.setLowerPrice(shopRegisterForm.getLowerPrice());
//		shop.setUpperPrice(shopRegisterForm.getUpperPrice());
//		shop.setCapacity(shopRegisterForm.getCapacity());
//		shop.setPostCode(shopRegisterForm.getPostCode());
//		shop.setAddress(shopRegisterForm.getAddress());
//		shop.setPhoneNumber(shopRegisterForm.getPhoneNumber());
//		
//		shopRepository.save(shop);
//	}
//	
//	@Transactional
//	public void update(ShopEditForm shopEditForm) {
//		Shop shop = shopRepository.getReferenceById(shopEditForm.getId());
//		MultipartFile imageFile = shopEditForm.getImageFile();
//		
//		if (!imageFile.isEmpty()) {
//			String imageName = imageFile.getOriginalFilename();
//			String hashedImageName = generateNewFileName(imageName);
//			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
//			copyImageFile(imageFile, filePath);
//			shop.setImageName(hashedImageName);
//		}
//		
//		shop.setName(shopEditForm.getName());
//		shop.setCategoryNumber(Integer.parseInt(shopEditForm.getCategory()));// カテゴリー番号を整数型に変換
//		shop.setDescription(shopEditForm.getDescription());
//		shop.setLowerPrice(shopEditForm.getLowerPrice());
//		shop.setUpperPrice(shopEditForm.getUpperPrice());
//		shop.setCapacity(shopEditForm.getCapacity());
//		shop.setPostCode(shopEditForm.getPostCode());
//		shop.setAddress(shopEditForm.getAddress());
//		shop.setPhoneNumber(shopEditForm.getPhoneNumber());
//		
//		shopRepository.save(shop);
//	}
//	
//	// UUIDを使って生成したファイル名を返す
//	public String generateNewFileName(String fileName) {
//		String[] fileNames = fileName.split("\\.");
//		for (int i = 0; i < fileNames.length - 1; i++) {
//			fileNames[i] = UUID.randomUUID().toString();
//		}
//		String hashedFileName = String.join(".", fileNames);
//		return hashedFileName;
//	}
//	
//	// 画像ファイルを指定したファイルにコピーする
//	public void copyImageFile(MultipartFile imageFile, Path filePath) {
//		try {
//			Files.copy(imageFile.getInputStream(), filePath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}

////訂正版
@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;

    public ShopService(ShopRepository shopRepository, CategoryRepository categoryRepository) {
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void create(ShopRegisterForm shopRegisterForm) {
        Shop shop = new Shop();
        MultipartFile imageFile = shopRegisterForm.getImageFile();

        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            shop.setImageName(hashedImageName);
        }

        shop.setName(shopRegisterForm.getName());
        Category category = categoryRepository.findById(shopRegisterForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        shop.setCategory(category);  // Categoryオブジェクトをセット
//        shop.setCategory(shopRegisterForm.getCategory()); // Categoryオブジェクトをそのまま設定
        shop.setDescription(shopRegisterForm.getDescription());
        shop.setLowerPrice(shopRegisterForm.getLowerPrice());
        shop.setUpperPrice(shopRegisterForm.getUpperPrice());
        shop.setCapacity(shopRegisterForm.getCapacity());
        shop.setPostCode(shopRegisterForm.getPostCode());
        shop.setAddress(shopRegisterForm.getAddress());
        shop.setPhoneNumber(shopRegisterForm.getPhoneNumber());

        shopRepository.save(shop);
    }

    @Transactional
    public void update(ShopEditForm shopEditForm) {
        Shop shop = shopRepository.findById(shopEditForm.getId())
                .orElseThrow(() -> new IllegalStateException("Shop not found"));
        MultipartFile imageFile = shopEditForm.getImageFile();

        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            shop.setImageName(hashedImageName);
        }

        shop.setName(shopEditForm.getName());
        Category category = categoryRepository.findById(shopEditForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        shop.setCategory(category);  // Categoryオブジェクトをセット
        shop.setDescription(shopEditForm.getDescription());
        shop.setLowerPrice(shopEditForm.getLowerPrice());
        shop.setUpperPrice(shopEditForm.getUpperPrice());
        shop.setCapacity(shopEditForm.getCapacity());
        shop.setPostCode(shopEditForm.getPostCode());
        shop.setAddress(shopEditForm.getAddress());
        shop.setPhoneNumber(shopEditForm.getPhoneNumber());

        shopRepository.save(shop);
    }

    // UUIDを使って生成したファイル名を返す
    public String generateNewFileName(String fileName) {
        String[] fileNames = fileName.split("\\.");
        for (int i = 0; i < fileNames.length - 1; i++) {
            fileNames[i] = UUID.randomUUID().toString();
        }
        return String.join(".", fileNames);
    }

    // 画像ファイルを指定したファイルにコピーする
    public void copyImageFile(MultipartFile imageFile, Path filePath) {
        try { 
            Files.copy(imageFile.getInputStream(), filePath); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }
}