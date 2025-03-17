package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.dto.request.CreateUserReq;
import com.example.demo.service.Impl.ApiServiceImpl;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {

    private static final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ApiServiceImpl apiServiceImpl;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.openMocks(this);
        // mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();

        apiServiceImpl = new ApiServiceImpl(); // Instantiate the service implementation

    }

    @Test
    public void testCreateUserById() throws Exception {

        // mock request
        CreateUserReq createUserReq = new CreateUserReq();
        createUserReq.setUserId(999);
        createUserReq.setBody("Testing by pab");
        createUserReq.setTitle("pab.sr");

        // start call & check response code = "000" , message = "Success"
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(gson.toJson(createUserReq)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("101"))
                .andDo(print());
    }

    @Test
    public void testGetUserById() throws Exception {

        // mock request
        long id = 1;

        // start call & check response code = "000" , message = "Success"
        mockMvc.perform(
                get("/users/{id}", id)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Success"))
                .andDo(print());

    }

    @Test
    public void testDeleteUserById() throws Exception {

        // mock request
        long id = 1;

        // start call & check response code = "000" , message = "Success"
        mockMvc.perform(
                delete("/users/{id}", id)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Success"))
                .andDo(print());

    }
}
