package com.ssg.partner.common.service;

import com.ssg.partner.common.dto.OrderRequest;
import com.ssg.partner.common.dto.OrderResponse;

/**
 * 제휴사 주문 조회 서비스 인터페이스
 * 모든 제휴사 모듈이 구현해야 하는 공통 인터페이스
 */
public interface PartnerOrderService {
    
    /**
     * 제휴사에서 주문 데이터를 조회하여 우리 회사 기준 포맷으로 변환하여 반환
     * 
     * @param request 주문 조회 요청
     * @return 우리 회사 기준으로 변환된 주문 응답
     */
    OrderResponse getOrders(OrderRequest request);
    
    /**
     * 제휴사 이름 반환
     * 
     * @return 제휴사 이름 (예: "naver", "gmarket", "11st")
     */
    String getPartnerName();
}
