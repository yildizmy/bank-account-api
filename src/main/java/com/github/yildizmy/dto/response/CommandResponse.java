package com.github.yildizmy.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CommandResponse(UUID id) {
}
