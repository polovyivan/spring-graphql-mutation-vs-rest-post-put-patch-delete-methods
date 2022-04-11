package com.polovyi.ivan.configuration;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScalarConfig {

    @Bean
    public GraphQLScalarType data() {
        return ExtendedScalars.Date;
    }
}
