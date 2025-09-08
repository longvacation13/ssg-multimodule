package com.ssg.partner.common.dto;

import java.time.LocalDateTime;

/**
 * 제휴사 주문 조회 요청 DTO
 * 모든 제휴사가 공통으로 사용하는 요청 포맷
 * Java 21 Record 사용
 */
public record OrderRequest(
    /**
     * 조회 시작일시
     */
    LocalDateTime startDate,
    
    /**
     * 조회 종료일시
     */
    LocalDateTime endDate,
    
    /**
     * 페이지 번호 (1부터 시작)
     */
    Integer page,
    
    /**
     * 페이지 크기
     */
    Integer pageSize,
    
    /**
     * 주문 상태 필터 (선택사항)
     */
    String orderStatus
) {
    
    /**
     * 기본 페이지 설정이 포함된 생성자
     */
    public OrderRequest(LocalDateTime startDate, LocalDateTime endDate) {
        this(startDate, endDate, 1, 20, null);
    }
    
    /**
     * 유효성 검증
     */
    public boolean isValid() {
        return startDate != null && endDate != null && 
               !startDate.isAfter(endDate) &&
               (page == null || page >= 1) &&
               (pageSize == null || pageSize >= 1);
    }
}