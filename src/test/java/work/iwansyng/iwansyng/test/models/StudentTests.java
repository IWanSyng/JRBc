package work.iwansyng.iwansyng.test.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.iwansyng.iwansyng.IwansyngApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import work.iwansyng.iwansyng.test.IwansyngApplicationTests;

public class StudentTests extends IwansyngApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testStudent() throws Exception {
        mockMvc.perform(get("/student")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.firstName").value("FirstName"))
                .andExpect(jsonPath("$.lastName").value("LastName"))
                .andExpect(jsonPath("$.uniqueId").value(105))
                .andExpect(jsonPath("$.isEnabled").value(true));
    }
}