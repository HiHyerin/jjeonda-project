package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByCardId(Long cardId);


    List<CardEntity> findByUserIdAndBankId(Long userId, Long bankId);
    List<CardEntity> findByBankId(Long bankId);

    List<CardEntity> findByUserId(Long userId);

    List<CardEntity> cardListByBankId(Long bankId);

//    Optional<CardEntity> findAllBy();

//    @Query(value = "select card_id from card where id= :id", nativeQuery = true)
//    List<CardEntity> selectCard_id(@Param("id") Long id);

}
