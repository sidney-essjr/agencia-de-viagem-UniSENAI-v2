package com.travel_agency.api.web.dto.mapper;

import com.travel_agency.api.entity.Destination;
import com.travel_agency.api.web.dto.DestinationCreateDto;
import com.travel_agency.api.web.dto.DestinationResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class DestinationMapper {

    public static Destination toDestination(DestinationCreateDto dto) {
        return new ModelMapper().map(dto, Destination.class);
    }

    public static DestinationResponseDto toDto(Destination destination) {
        return new ModelMapper().map(destination, DestinationResponseDto.class);
    }

    public static List<DestinationResponseDto> toListDto(Page<Destination> destinations) {
        return destinations.stream().map(DestinationMapper::toDto).collect(Collectors.toList());
    }
}
