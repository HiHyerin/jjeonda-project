package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.dto.book.BookReqDto;
import com.fintech.jjeondaproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
// http://localhost:8080/books
@RequestMapping("/books")
@Controller
public class BookController {
    //스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동으로 연결해줌
    private final BookService bookService;

    @GetMapping("/detail")
    public String getDetailPage() {
        return "book";
    }

    @PostMapping("/details")
    //DTO로 데이터 받아옴
    public String createBook(BookReqDto bookDto) {

        bookService.createBook(bookDto);

        return "redirect:/";
    }
}
