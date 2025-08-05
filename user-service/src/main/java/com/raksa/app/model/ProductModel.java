//package com.raksa.app.model;
//
//import com.raksa.app.utils.IdGenerator;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name = "products")
//@Getter
//@Setter
//public class ProductModel {
//
//    @Id
//    @Column(name = "id")
//    private String id;
//
//    @PrePersist
//    private void prePersist() {
//        if (this.id == null || this.id.isEmpty()) {
//            this.id = IdGenerator.generateUUID("PRO");
//        }
//    }
//
//    @Column(name = "product_name", nullable = false, unique = true)
//    private String productName;
//
//    @Column(name = "product_code")
//    private String productCode;
//
//}
