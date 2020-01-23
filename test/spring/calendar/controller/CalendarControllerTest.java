package spring.calendar.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalendarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup(){
        addUserToSecurityContext();
    }

    @Test
    public void calendarHandlerTest() throws Exception {
        this.mockMvc.perform(
                get("/calendar"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("calendar")));
    }

    @Test
    public void loginHandlerTest() throws Exception {
        this.mockMvc.perform(
                get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

}

    private void addUserToSecurityContext() {
        UserDetails user =
                org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                        .username("roeland@gmail.com")
                        .password("123")
                        .roles("USER")
                        .build();
        SecurityContextHolder.clearContext();
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(
                new TestingAuthenticationToken(user, new ArrayList<GrantedAuthority>()));
        SecurityContextHolder.setContext(securityContext);
    }

}
