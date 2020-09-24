package com.egencodechallenge.orderservice.mappers;

import com.egencodechallenge.orderservice.dtos.CardDto;
import com.egencodechallenge.orderservice.models.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDto getCardDto(Card card);

    List<CardDto> getCards(List<Card> card);




}
