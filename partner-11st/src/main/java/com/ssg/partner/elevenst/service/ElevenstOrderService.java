package com.ssg.partner.elevenst.service;

import com.ssg.partner.common.dto.OrderRequest;
import com.ssg.partner.common.dto.OrderResponse;
import com.ssg.partner.common.service.OrderConverter;
import com.ssg.partner.common.service.PartnerOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 11번가 제휴사 주문 조회 서비스
 * 외주 개발 영역해야 하는 부분
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ElevenstOrderService implements PartnerOrderService {
    
    // TODO: 외주 개발 영역
    // - 11번가 API 클라이언트
    // - 11번가 응답 데이터를 SSG 기준 DTO로 변환하는 로직
    
    @Override
    public OrderResponse getOrders(OrderRequest request) {
        log.info("11번가 주문 조회 요청: {}", request);
        
        // TODO: 외주 개발 영역
        // 1. 11번가 API 호출
        // 2. 11번가 응답 데이터를 SSG 기준 OrderResponse로 변환
        // 3. 변환된 데이터 반환
        
        throw new UnsupportedOperationException("외주 개발 영역해야 합니다.");
    }
    
    @Override
    public String getPartnerName() {
        return "11st";
    }
    
    @Override
    public OrderConverter<?> getOrderConverter() {
        // TODO: 외주 개발 영역
        // 11번가 API 응답을 SSG 기준 DTO로 변환하는 컨버터 반환
        throw new UnsupportedOperationException("외주 개발 영역해야 합니다.");
    }
}
