package com.kreitek.learnjhipster.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StyleMapperTest {

    private StyleMapper styleMapper;

    @BeforeEach
    public void setUp() {
        styleMapper = new StyleMapperImpl();
    }
}
