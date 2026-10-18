package com.example.productorderservice.docs;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;

class OrderDocumentationTest extends DocumentationTest {

    private static final String TAG = "Order";
    private static final String CREATE_ORDER_DESCRIPTION = "상품과 수량으로 주문을 생성합니다. 존재하지 않는 상품이면 `404`를 응답합니다.";
    private static final String CREATE_ORDER_REQUEST = """
            {"productId": 1, "quantity": 2}
            """;

    @Test
    void 상품주문() throws Exception {
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CREATE_ORDER_REQUEST))
                .andExpect(status().isCreated())
                .andDo(document("order-create", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 주문")
                                .description(CREATE_ORDER_DESCRIPTION)
                                .requestSchema(Schema.schema("CreateOrderRequest"))
                                .requestFields(createOrderRequestFields())
                                .build())));
    }

    @Test
    void 상품주문_존재하지_않는_상품() throws Exception {
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CREATE_ORDER_REQUEST))
                .andExpect(status().isNotFound())
                .andDo(document("order-create-not-found", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 주문")
                                .description(CREATE_ORDER_DESCRIPTION)
                                .requestSchema(Schema.schema("CreateOrderRequest"))
                                .requestFields(createOrderRequestFields())
                                .responseSchema(ERROR_MESSAGE)
                                .build())));
    }

    private static FieldDescriptor[] createOrderRequestFields() {
        return new FieldDescriptor[]{
                fieldWithPath("productId").description("주문할 상품 ID"),
                fieldWithPath("quantity").description("주문 수량 (0보다 커야 함)")
        };
    }
}
