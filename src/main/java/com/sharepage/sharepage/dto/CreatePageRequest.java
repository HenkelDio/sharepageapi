package com.sharepage.sharepage.dto;

import java.util.List;

public record CreatePageRequest(String title, List<LinkDTO> links) {}
