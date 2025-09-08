package com.ssg.partner.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 제휴사 주문 조회 응답 DTO
 * 모든 제휴사가 공통으로 반환하는 SSG 기준 응답 포맷
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    
    /**
     * 주문 목록
     */
    private List<PartnerOrder> orders;
    
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
     * 제휴사 주문 정보 (기존 ExtnlOrder 대체)
     * SSG 기준으로 표준화된 주문 데이터
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartnerOrder {
        
        /**
         * 제휴사 주문번호 (venOrdNo)
         */
        private String partnerOrderId;
        
        /**
         * 주문일시 (ordDt)
         */
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime orderDate;
        
        /**
         * 제휴사 ID (almktId)
         */
        private String partnerId;
        
        /**
         * 주문자 정보
         */
        private OrderCustomer customer;
        
        /**
         * 주문 상품 목록
         */
        private List<PartnerOrderItem> orderItems;
        
        /**
         * 배송 정보
         */
        private DeliveryInfo delivery;
        
        /**
         * 장보기 특화 정보 (필요한 경우만)
         */
        private MartOrderInfo martOrderInfo;
        
        /**
         * 기타 메타데이터
         */
        private OrderMetadata metadata;
    }
    
    /**
     * 주문자 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderCustomer {
        private String name;           // ordpeNm
        private String phone;          // ordpeHpno
        private String landline;       // ordpeTelno
        private String email;          // ordpeEmail
        private String zipCode;        // ordpeZipcd
        private String baseAddress;    // ordpeLotnoBascAddr
        private String detailAddress;  // ordpeLotnoDtlAddr
        private String roadBaseAddress; // ordpeRoadNmBascAddr
        private String roadDetailAddress; // ordpeRoadNmDtlAddr
    }
    
    /**
     * 제휴사 주문 상품 정보 (기존 ExtnlOrderItem 대체)
     * SSG 기준으로 표준화된 주문 상품 데이터
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartnerOrderItem {
        
        /**
         * 제휴사 주문상품순번 (almktOrdItemNo)
         */
        private String partnerOrderItemNo;
        
        /**
         * 상품 정보
         */
        private ProductInfo product;
        
        /**
         * 주문 수량 (ordQty)
         */
        private Long quantity;
        
        /**
         * 가격 정보
         */
        private PriceInfo price;
        
        /**
         * 배송 정보
         */
        private ItemDeliveryInfo delivery;
        
        /**
         * 기타 메타데이터
         */
        private ItemMetadata metadata;
    }
    
    /**
     * 상품 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductInfo {
        private String partnerItemId;      // venItemId
        private String partnerUnitId;      // venUitemId
        private String ssgItemId;          // ssgItemId
        private String ssgUnitId;          // ssgUitemId
        private String itemName;           // itemName
        private String unitName;           // uitemName
        private String storeNo;            // strNo
        private String partnerGiftId;      // ptnrGiftId
    }
    
    /**
     * 가격 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PriceInfo {
        private BigDecimal deliveryCost;       // dlvCst
        private BigDecimal partnerSupplyPrice; // venSplprc
        private BigDecimal partnerSellPrice;   // venSellPrc
        private BigDecimal optionPrice;        // optionPrc
        private BigDecimal ssgDiscountAmount;  // venDcAmt
        private BigDecimal partnerDiscountAmount; // venBdnDcAmt
        private BigDecimal realOrderAmount;    // venRlordAmt
        private BigDecimal ssgMarketingDiscount; // ssgBdnDcOfferAmt
        private BigDecimal ssgDiscount;        // ssgbdnDcAmt
    }
    
    /**
     * 상품별 배송 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemDeliveryInfo {
        private String recipientName;      // rcvrNm
        private String recipientPhone;     // rcvrPhoneNum
        private String recipientLandline;  // rcvrTelNum
        private String zipCode;            // rcvrZipcd
        private String baseAddress;        // rcvrBaseAddr
        private String detailAddress;      // rcvrDtlsAddr
        private Boolean isRoadNameAddress; // isRoadNmAddr
        private String prepaymentCode;     // prpayCodDivCd
        private String deliveryMemo;       // deliveryMemo
        private Integer deliveryLocationNo; // shpplocNo
        private Boolean isBundleDelivery;  // isBndlDlv
        private String deliveryNo;         // dlvNo
        private String deliveryReserveType; // shppRsvtTypeCd
    }
    
    /**
     * 주문 배송 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryInfo {
        private String deliveryType;       // strDlvClfCd
        private String deliveryReserveDate; // shppRsvtDt
        private String deliveryStartTime;  // shppStrtHm
        private String deliveryEndTime;    // shppEndHm
        private List<String> memoContents; // memoCntts
        private Boolean isShippingCostByRealPrice; // isShippingCostChargeByRealPrice
    }
    
    /**
     * 장보기 주문 특화 정보
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MartOrderInfo {
        private BigDecimal depositPrice;   // depositPrc
        private String orderDegree;        // ordDgr
        private String memberId;           // memberId
        private String tomorrowApplyYn;    // stmrwAplYn
        private String storeNo;            // strNo
    }
    
    /**
     * 주문 메타데이터
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderMetadata {
        private Boolean isExternal;        // isExtnl
        private Boolean isManual;          // isMnual
        private Boolean isBundleDeliveryCostOverlapped; // isBndlDlvCstOverlapped
        private Boolean needCapacityCheck; // needCapaChk
        private Boolean isGiftOrder;       // isGiftOrder
        private Boolean isAlertOff;        // isAlertOff
        private String mallType;           // mallType
        private List<String> skuNos;       // skuNos
        private String orderMemoDivCode;   // ordMemoDivCd
        private String partnerInflowTypeCode; // allnInfloTypeCd
    }
    
    /**
     * 상품 메타데이터
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemMetadata {
        private String buildingManageNo;   // bldgMngNo
        private Long nPlusStandardQty;     // nPlusStandQty
    }
}
