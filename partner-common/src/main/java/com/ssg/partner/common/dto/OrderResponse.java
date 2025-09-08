package com.ssg.partner.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 제휴사 주문 조회 응답 DTO
 * 모든 제휴사가 공통으로 반환하는 응답 포맷
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    
    /**
     * 주문 목록
     */
    private List<Order> orders;
    
    /**
     * 전체 주문 수
     */
    private Long totalCount;
    
    /**
     * 현재 페이지
     */
    private Integer currentPage;
    
    /**
     * 전체 페이지 수
     */
    private Integer totalPages;
    
    /**
     * 개별 주문 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Order {
        
        /**
         * 제휴사 주문번호
         */
        private String partnerOrderId;
        
        /**
         * 우리 회사 주문번호 (제휴사에서 제공하는 경우)
         */
        private String ssgOrderId;
        
        /**
         * 주문일시
         */
        private LocalDateTime orderDate;
        
        /**
         * 주문 상태
         */
        private String orderStatus;
        
        /**
         * 주문자 정보
         */
        private Customer customer;
        
        /**
         * 주문 상품 목록
         */
        private List<OrderItem> orderItems;
        
        /**
         * 주문 금액 정보
         */
        private OrderAmount orderAmount;
        
        /**
         * 배송 정보
         */
        private Delivery delivery;
    }
    
    /**
     * 주문자 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Customer {
        private String name;
        private String phone;
        private String email;
    }
    
    /**
     * 주문 상품 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        private String productId;
        private String productName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal totalPrice;
    }
    
    /**
     * 주문 금액 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderAmount {
        private BigDecimal productAmount;    // 상품금액
        private BigDecimal discountAmount;   // 할인금액
        private BigDecimal deliveryFee;      // 배송비
        private BigDecimal totalAmount;      // 총 주문금액
    }
    
    /**
     * 배송 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delivery {
        private String recipientName;
        private String recipientPhone;
        private String address;
        private String deliveryStatus;
        private String trackingNumber;
    }
}
