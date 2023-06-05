package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.book.BookReqDto;
import com.fintech.jjeondaproject.entity.BookEntity;
import com.fintech.jjeondaproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {


    @Autowired
    private final BookRepository bookRepository;

    public void createBook(BookReqDto bookDto) {
        BookEntity bookEntity = BookEntity.builder()
                .costType(bookDto.getCostType())
                .content(bookDto.getContent())
                .cost(bookDto.getCost())
                .memo(bookDto.getMemo())
                .build();

        bookRepository.save(bookEntity);
    }
}
