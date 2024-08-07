package com.juliomesquita.application.aggregate_context.infra.config;

import graphql.scalars.ExtendedScalars;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GraphqlConfiguration.class);
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.Url)
                .scalar(ExtendedScalars.UUID);
    }

    @Bean
    public GraphQlSourceBuilderCustomizer graphQlSourceBuilderCustomizer(){
        return source -> source.inspectSchemaMappings(report -> logger.info(report.toString()));
    }

    @Bean
    Faker faker(){
        return new Faker();
    }
}
