package com.example.productorderservice.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.epages.restdocs.apispec.Schema;
import com.example.productorderservice.ApiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * API 문서(OpenAPI) 생성을 위한 테스트 기반 클래스.
 * Spring REST Docs 4.0에서 REST Assured 연동이 제거되어 문서화 테스트는 MockMvc로 작성한다.
 */
@ExtendWith(RestDocumentationExtension.class)
public abstract class DocumentationTest extends ApiTest {

    /** 에러 응답 본문(text/plain 메시지) 스키마 */
    protected static final Schema ERROR_MESSAGE = Schema.schema("ErrorMessage");

    protected MockMvc mockMvc;

    @BeforeEach
    void setUpMockMvc(final WebApplicationContext context,
            final RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }
}
