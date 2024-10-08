package com.example.nagoyameshi.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

//@Entity
//@Table(name = "shops")
//@Data
//public class Shop {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Integer id;
//	
//	@Column(name = "name")
//	private String name;
//	
//	@Column(name = "image_name")
//	private String imageName;
//	
//	@ManyToOne
//	@JoinColumn(name = "category_number")
//	private  Integer categoryNumber;
//	
//	@Column(name = "description")
//	private String description;
//	
//	@Column(name = "lower_price")
//	private Integer lowerPrice;
//	
//	@Column(name = "upper_price")
//	private Integer upperPrice;
//	
//	@Column(name = "capacity")
//	private Integer capacity;
//	
//	@Column(name = "post_code")
//	private String postCode;
//	
//	@Column(name = "address")
//	private String address;
//	
//	@Column(name = "phone_number")
//	private String phoneNumber;
//	
//	@Column(name = "created_at", insertable = false, updatable = false)
//	private Timestamp createdAt;
//	
//	@Column(name = "updated_at", insertable = false, updatable = false)
//	private Timestamp updatedAt;
//}

@Entity
@Table(name = "shops")
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_name")
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "category_id") 
    private Category category;
    
    @Column(name = "description")
    private String description;

    @Column(name = "lower_price")
    private Integer lowerPrice;

    @Column(name = "upper_price")
    private Integer upperPrice;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}