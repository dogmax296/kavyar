package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.PhotoRequestDto;
import ua.kavyar.dto.PhotoResponseDto;
import ua.kavyar.model.Photo;
import ua.kavyar.service.mapper.DtoMapper;

@Service
public class PhotoDtoMapper implements DtoMapper<Photo, PhotoRequestDto, PhotoResponseDto> {
    @Override
    public PhotoResponseDto mapToDto(Photo photo) {
        PhotoResponseDto photoResponseDto = new PhotoResponseDto();
        photoResponseDto.setId(photo.getId());
        photoResponseDto.setUrl(photo.getUrl());
        return photoResponseDto;
    }

    @Override
    public Photo mapToModel(PhotoRequestDto photoRequestDto) {
        Photo photo = new Photo();
        photo.setUrl(photoRequestDto.getUrl());
        return photo;
    }
}
