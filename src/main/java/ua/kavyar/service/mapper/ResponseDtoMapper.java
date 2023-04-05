package ua.kavyar.service.mapper;

public interface ResponseDtoMapper<M, S> {
    S mapToDto(M m);
}
