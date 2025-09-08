package com.ssg.partner.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 제휴사 주문 조회 응답 DTO
 * 모든 제휴사가 공통으로 반환하는 SSG 기준 응답 포맷
 * Java 21 Record 사용
 */
public record OrderResponse(
    /**
     * 주문 목록
     */
    List<PartnerOrder> orders,
    
    /**
     * 전체 주문 수
     */
    Long totalCount,
    
    /**
     * 현재 페이지
     */
    Integer currentPage,
    
    /**
     * 전체 페이지 수
     */
    Integer totalPages
) {
    
    /**
     * 제휴사 주문 정보 (기존 ExtnlOrder 대체)
     * SSG 기준으로 표준화된 주문 데이터
     */
    public record PartnerOrder(
        /**
         * 제휴사 주문번호 (venOrdNo)
         */
        String partnerOrderId,
        
        /**
         * 주문일시 (ordDt)
         */
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime orderDate,
        
        /**
         * 제휴사 ID (almktId)
         */
        String partnerId,
        
        /**
         * 주문자 정보
         */
        OrderCustomer customer,
        
        /**
         * 주문 상품 목록
         */
        List<PartnerOrderItem> orderItems,
        
        /**
         * 배송 정보
         */
        DeliveryInfo delivery,
        
        /**
         * 장보기 특화 정보 (필요한 경우만)
         */
        MartOrderInfo martOrderInfo,
        
        /**
         * 기타 메타데이터
         */
        OrderMetadata metadata
    ) {}
    
    /**
     * 주문자 정보
     */
    public record OrderCustomer(
        String name,           // ordpeNm
        String phone,          // ordpeHpno
        String landline,       // ordpeTelno
        String email,          // ordpeEmail
        String zipCode,        // ordpeZipcd
        String baseAddress,    // ordpeLotnoBascAddr
        String detailAddress,  // ordpeLotnoDtlAddr
        String roadBaseAddress, // ordpeRoadNmBascAddr
        String roadDetailAddress // ordpeRoadNmDtlAddr
    ) {}
    
    /**
     * 제휴사 주문 상품 정보 (기존 ExtnlOrderItem 대체)
     * SSG 기준으로 표준화된 주문 상품 데이터
     */
    public record PartnerOrderItem(
        /**
         * 제휴사 주문상품순번 (almktOrdItemNo)
         */
        String partnerOrderItemNo,
        
        /**
         * 상품 정보
         */
        ProductInfo product,
        
        /**
         * 주문 수량 (ordQty)
         */
        Long quantity,
        
        /**
         * 가격 정보
         */
        PriceInfo price,
        
        /**
         * 배송 정보
         */
        ItemDeliveryInfo delivery,
        
        /**
         * 기타 메타데이터
         */
        ItemMetadata metadata
    ) {}
    
    /**
     * 상품 정보
     */
    public record ProductInfo(
        String partnerItemId,      // venItemId
        String partnerUnitId,      // venUitemId
        String ssgItemId,          // ssgItemId
        String ssgUnitId,          // ssgUitemId
        String itemName,           // itemName
        String unitName,           // uitemName
        String storeNo,            // strNo
        String partnerGiftId       // ptnrGiftId
    ) {}
    
    /**
     * 가격 정보
     */
    public record PriceInfo(
        BigDecimal deliveryCost,       // dlvCst
        BigDecimal partnerSupplyPrice, // venSplprc
        BigDecimal partnerSellPrice,   // venSellPrc
        BigDecimal optionPrice,        // optionPrc
        BigDecimal ssgDiscountAmount,  // venDcAmt
        BigDecimal partnerDiscountAmount, // venBdnDcAmt
        BigDecimal realOrderAmount,    // venRlordAmt
        BigDecimal ssgMarketingDiscount, // ssgBdnDcOfferAmt
        BigDecimal ssgDiscount         // ssgbdnDcAmt
    ) {}
    
    /**
     * 상품별 배송 정보
     */
    public record ItemDeliveryInfo(
        String recipientName,      // rcvrNm
        String recipientPhone,     // rcvrPhoneNum
        String recipientLandline,  // rcvrTelNum
        String zipCode,            // rcvrZipcd
        String baseAddress,        // rcvrBaseAddr
        String detailAddress,      // rcvrDtlsAddr
        Boolean isRoadNameAddress, // isRoadNmAddr
        String prepaymentCode,     // prpayCodDivCd
        String deliveryMemo,       // deliveryMemo
        Integer deliveryLocationNo, // shpplocNo
        Boolean isBundleDelivery,  // isBndlDlv
        String deliveryNo,         // dlvNo
        String deliveryReserveType // shppRsvtTypeCd
    ) {}
    
    /**
     * 주문 배송 정보
     */
    public record DeliveryInfo(
        String deliveryType,       // strDlvClfCd
        String deliveryReserveDate, // shppRsvtDt
        String deliveryStartTime,  // shppStrtHm
        String deliveryEndTime,    // shppEndHm
        List<String> memoContents, // memoCntts
        Boolean isShippingCostByRealPrice // isShippingCostChargeByRealPrice
    ) {}
    
    /**
     * 장보기 주문 특화 정보
     */
    public record MartOrderInfo(
        BigDecimal depositPrice,   // depositPrc
        String orderDegree,        // ordDgr
        String memberId,           // memberId
        String tomorrowApplyYn,    // stmrwAplYn
        String storeNo             // strNo
    ) {}
    
    /**
     * 주문 메타데이터
     */
    public record OrderMetadata(
        Boolean isExternal,        // isExtnl
        Boolean isManual,          // isMnual
        Boolean isBundleDeliveryCostOverlapped, // isBndlDlvCstOverlapped
        Boolean needCapacityCheck, // needCapaChk
        Boolean isGiftOrder,       // isGiftOrder
        Boolean isAlertOff,        // isAlertOff
        String mallType,           // mallType
        List<String> skuNos,       // skuNos
        String orderMemoDivCode,   // ordMemoDivCd
        String partnerInflowTypeCode // allnInfloTypeCd
    ) {}
    
    /**
     * 상품 메타데이터
     */
    public record ItemMetadata(
        String buildingManageNo,   // bldgMngNo
        Long nPlusStandardQty      // nPlusStandQty
    ) {}
}