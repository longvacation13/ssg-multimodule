package com.ssg.partner.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 제휴사 주문 조회 요청 DTO
 * 모든 제휴사가 공통으로 사용하는 요청 포맷
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    
    /**
     * 조회 시작일시
     */
    private LocalDateTime startDate;
    
    /**
     * 조회 종료일시
     */
    private LocalDateTime endDate;
    
    /**
     * 페이지 번호 (1부터 시작)
     */
    private Integer page;
    
    /**
     * 페이지 크기
     */
    private Integer pageSize;
    
    /**
     * 주문 상태 필터 (선택사항)
     */
    private String orderStatus;
}
