package de.mrtimeey.comicscraper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class OpenApiTest {

    private static final String DOC_URL = "/api-docs/openapi";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetOpenApiDocAsJson() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(DOC_URL)
                .with(user("user"))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("info.title", is("comic-scraper")))
                .andExpect(jsonPath("info.version", notNullValue()))
                .andExpect(jsonPath("info.description", is("Scraper application for comic books")));
    }

    @Test
    void testGetOpenApiDocAsYaml() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(DOC_URL + ".yaml")
                .with(user("user"))
                .contentType("application/vnd.oai.openapi;charset=UTF-8");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/vnd.oai.openapi;charset=UTF-8"));
    }

}
