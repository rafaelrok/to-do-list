package br.com.rafaelvieira.todolist.user.controller;

import br.com.rafaelvieira.todolist.user.controller.CustomUtils;
import br.com.rafaelvieira.todolist.user.controller.userController;
import br.com.rafaelvieira.todolist.user.domain.user;
import br.com.rafaelvieira.todolist.user.dto.userDto;
import br.com.rafaelvieira.todolist.user.mapper.userMapper;
import br.com.rafaelvieira.todolist.user.service.userService;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class userControllerTest {
    private static final String ENDPOINT_URL = "/api/user";

    @InjectMocks
    private userController userController;

    @Mock
    private userService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<userDto> page = new PageImpl<>(Collections.singletonList(userBuilder.getDto()));

        Mockito.when(userService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(userService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(userService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(userBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(userService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(userService.save(ArgumentMatchers.any(userDto.class)))
                .thenReturn(userBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(userBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(userService, Mockito.times(1)).save(ArgumentMatchers.any(userDto.class));
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(userService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(userBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(userBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService, Mockito.times(1))
                .update(ArgumentMatchers.any(userDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(userService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(userBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }
}
