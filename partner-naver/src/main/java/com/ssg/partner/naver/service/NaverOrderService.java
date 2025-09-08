package com.ssg.partner.naver.service;

import com.ssg.partner.common.dto.OrderRequest;
import com.ssg.partner.common.dto.OrderResponse;
import com.ssg.partner.common.exception.PartnerException;
import com.ssg.partner.common.service.OrderConverter;
import com.ssg.partner.common.service.PartnerOrderService;
import com.ssg.partner.common.service.PartnerType;
import com.ssg.partner.common.service.NaverPartner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 네이버 제휴사 주문 조회 서비스
 * 외주 개발 영역해야 하는 부분
 * Java 21 기능 활용
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NaverOrderService implements PartnerOrderService {
    
    private static final PartnerType PARTNER_TYPE = new NaverPartner();
    
    // TODO: 외주 개발 영역
    // - 네이버 API 클라이언트
    // - 네이버 응답 데이터를 SSG 기준 DTO로 변환하는 로직
    
    @Override
    public OrderResponse getOrders(OrderRequest request) {
        // Java 21 Text Blocks를 활용한 로깅
        log.info("""
            네이버 주문 조회 요청:
            ===================
            시작일: %s
            종료일: %s
            페이지: %s
            페이지 크기: %s
            주문 상태: %s
            ===================
            """.formatted(
                request.startDate(),
                request.endDate(),
                request.page(),
                request.pageSize(),
                request.orderStatus()
            ));
        
        // 요청 유효성 검증
        if (!request.isValid()) {
            throw new IllegalArgumentException("""
                잘못된 요청 파라미터:
                - 시작일과 종료일은 필수입니다
                - 시작일은 종료일보다 늦을 수 없습니다
                - 페이지 번호는 1 이상이어야 합니다
                - 페이지 크기는 1 이상이어야 합니다
                """);
        }
        
        // TODO: 외주 개발 영역
        // 1. 네이버 API 호출
        // 2. 네이버 응답 데이터를 SSG 기준 OrderResponse로 변환
        // 3. 변환된 데이터 반환
        
        throw new UnsupportedOperationException("외주 개발 영역해야 합니다.");
    }
    
    @Override
    public String getPartnerName() {
        return PARTNER_TYPE.getPartnerName();
    }
    
    @Override
    public OrderConverter<?> getOrderConverter() {
        // TODO: 외주 개발 영역
        // 네이버 API 응답을 SSG 기준 DTO로 변환하는 컨버터 반환
        throw new UnsupportedOperationException("외주 개발 영역해야 합니다.");
    }
    
    /**
     * 네이버 특화 로직 처리
     * Java 21 Pattern Matching 활용
     */
    public void handleNaverSpecificLogic(Object data) {
        switch (PARTNER_TYPE) {
            case NaverPartner naver -> {
                log.info("네이버 특화 처리 시작: {}", naver.getPartnerCode());
                // 네이버 특화 로직 구현
            }
            default -> throw new IllegalStateException("예상치 못한 제휴사 타입");
        }
    }
}
