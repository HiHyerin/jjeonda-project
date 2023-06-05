package com.fintech.jjeondaproject.dto.book;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class BookReqDto {
// 가계부 수정 페이지에서 사용할 것 들만
    private String costType;
    private String content;
    private int cost;
    private String memo;
}
