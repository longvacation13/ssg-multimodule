package com.ssg.partner.common.service;

import com.ssg.partner.common.dto.OrderRequest;
import com.ssg.partner.common.dto.OrderResponse;

/**
 * 제휴사 주문 조회 서비스 인터페이스
 * 모든 제휴사 모듈이 구현해야 하는 공통 인터페이스
 * SSG 기준으로 표준화된 주문 데이터를 반환
 * 
 * SRP: 주문 조회만 담당
 * OCP: 새로운 제휴사 추가 시 기존 코드 수정 없이 확장 가능
 * DIP: 구체적인 구현이 아닌 인터페이스에 의존
 */
public interface PartnerOrderService {
    
    /**
     * 제휴사에서 주문 데이터를 조회하여 SSG 기준 포맷으로 변환하여 반환
     * 
     * @param request 주문 조회 요청
     * @return SSG 기준으로 변환된 주문 응답
     */
    OrderResponse getOrders(OrderRequest request);
    
    /**
     * 제휴사 이름 반환
     * 
     * @return 제휴사 이름 (예: "naver", "gmarket", "11st")
     */
    String getPartnerName();
    
    /**
     * 제휴사별 특화된 주문 데이터 변환기 반환
     * 
     * @return 주문 데이터 변환기
     */
    OrderConverter<?> getOrderConverter();
    
    /**
     * 제휴사별 특화된 주문 데이터 검증기 반환 (선택사항)
     * 
     * @return 주문 데이터 검증기 (null인 경우 기본 검증기 사용)
     */
    default OrderValidator getOrderValidator() {
        return null; // 기본 검증기 사용
    }
}
