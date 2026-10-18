package com.example.productorderservice.docs;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ParameterDescriptorWithType;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.epages.restdocs.apispec.SimpleType;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;

class ProductDocumentationTest extends DocumentationTest {

    private static final String TAG = "Product";
    private static final String DISCOUNT_POLICY_DESCRIPTION = "할인 정책 (NONE: 할인 없음, FIX_1000_AMT: 1,000원 정액 할인)";
    private static final String GET_PRODUCT_DESCRIPTION = "상품 ID로 상품을 조회합니다. 존재하지 않는 상품이면 `404`를 응답합니다.";
    private static final String GET_PRODUCTS_DESCRIPTION = """
            id 내림차순(최신순)으로 상품 목록을 페이징 조회합니다.
            `page`가 0보다 작거나 `size`가 1 ~ 100 범위를 벗어나면 `400`을 응답합니다.""";

    @Test
    void 상품등록() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name": "상품명", "price": 1000, "discountPolicy": "NONE"}
                                """))
                .andExpect(status().isCreated())
                .andDo(document("product-create", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 등록")
                                .description("새 상품을 등록합니다. 할인 정책은 주문 금액 계산에 사용됩니다.")
                                .requestSchema(Schema.schema("AddProductRequest"))
                                .requestFields(productRequestFields())
                                .build())));
    }

    @Test
    void 상품조회() throws Exception {
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        mockMvc.perform(get("/products/{productId}", 1L))
                .andExpect(status().isOk())
                .andDo(document("product-get", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 단건 조회")
                                .description(GET_PRODUCT_DESCRIPTION)
                                .pathParameters(productIdParameter())
                                .responseSchema(Schema.schema("ProductResponse"))
                                .responseFields(productResponseFields(""))
                                .build())));
    }

    @Test
    void 상품조회_존재하지_않는_상품() throws Exception {
        mockMvc.perform(get("/products/{productId}", 1L))
                .andExpect(status().isNotFound())
                .andDo(document("product-get-not-found", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 단건 조회")
                                .description(GET_PRODUCT_DESCRIPTION)
                                .pathParameters(productIdParameter())
                                .responseSchema(ERROR_MESSAGE)
                                .build())));
    }

    @Test
    void 상품목록조회() throws Exception {
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        mockMvc.perform(get("/products")
                        .queryParam("page", "0")
                        .queryParam("size", "2"))
                .andExpect(status().isOk())
                .andDo(document("product-list", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 목록 조회")
                                .description(GET_PRODUCTS_DESCRIPTION)
                                .queryParameters(pagingParameters())
                                .responseSchema(Schema.schema("ProductListResponse"))
                                .responseFields(productResponseFields("[]."))
                                .build())));
    }

    @Test
    void 상품목록조회_잘못된_페이지() throws Exception {
        mockMvc.perform(get("/products")
                        .queryParam("page", "-1")
                        .queryParam("size", "10"))
                .andExpect(status().isBadRequest())
                .andDo(document("product-list-bad-request", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 목록 조회")
                                .description(GET_PRODUCTS_DESCRIPTION)
                                .queryParameters(pagingParameters())
                                .responseSchema(ERROR_MESSAGE)
                                .build())));
    }

    @Test
    void 상품수정() throws Exception {
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        mockMvc.perform(put("/products/{productId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name": "상품수정", "price": 2000, "discountPolicy": "FIX_1000_AMT"}
                                """))
                .andExpect(status().isOk())
                .andDo(document("product-update", resource(
                        ResourceSnippetParameters.builder()
                                .tag(TAG)
                                .summary("상품 수정")
                                .description("상품명, 가격, 할인 정책을 수정합니다. 존재하지 않는 상품이면 `404`를 응답합니다.")
                                .pathParameters(productIdParameter())
                                .requestSchema(Schema.schema("UpdateProductRequest"))
                                .requestFields(productRequestFields())
                                .build())));
    }

    private static ParameterDescriptorWithType productIdParameter() {
        return parameterWithName("productId").type(SimpleType.INTEGER).description("상품 ID");
    }

    private static ParameterDescriptorWithType[] pagingParameters() {
        return new ParameterDescriptorWithType[]{
                parameterWithName("page").type(SimpleType.INTEGER)
                        .description("페이지 번호 (0부터 시작, 기본값 0)").optional(),
                parameterWithName("size").type(SimpleType.INTEGER)
                        .description("페이지 크기 (1 ~ 100, 기본값 10)").optional()
        };
    }

    private static FieldDescriptor[] productRequestFields() {
        return new FieldDescriptor[]{
                fieldWithPath("name").description("상품명"),
                fieldWithPath("price").description("가격 (0보다 커야 함)"),
                fieldWithPath("discountPolicy").description(DISCOUNT_POLICY_DESCRIPTION)
        };
    }

    private static FieldDescriptor[] productResponseFields(final String prefix) {
        return new FieldDescriptor[]{
                fieldWithPath(prefix + "id").description("상품 ID"),
                fieldWithPath(prefix + "name").description("상품명"),
                fieldWithPath(prefix + "price").description("가격"),
                fieldWithPath(prefix + "discountPolicy").description(DISCOUNT_POLICY_DESCRIPTION)
        };
    }
}
