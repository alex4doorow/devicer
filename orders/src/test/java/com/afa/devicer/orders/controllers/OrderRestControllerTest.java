package com.afa.devicer.orders.controllers;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.orders.services.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@Slf4j
public class OrderRestControllerTest {

    @MockBean
    private OrdersService orderService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate restTemplate;

    //@Test
    public void testGetOrderById() throws Exception {

        final long id = 33L;

        //Customer customer = new Customer();

        SEOrder order = new SEOrder();
        order.setId(id);
        order.setOrderNum(33L);
        //order.setProductCategory(new ProductCategory(1L, "test product category"));


        Mockito.when(orderService.findById(id)).thenReturn(order);

        String body = restTemplate.getForObject("/orders/v1/orders/" + id, String.class);

        mockMvc.perform(get("/orders/v1/orders/33"))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.username").value("alex"));
    }


    //@Test
    public void testOrderByRestTemplate() throws Exception {
        final long id = 33L;
        String body = restTemplate.getForObject("/rest/v1/orders/" + id, String.class);
        Assertions.assertTrue(body.contains("order"));

    }


    //@Test
    public void testOrderByRest() throws Exception {
        final long id = 10714L;

        //insert
        /*
        this.mockMvc.perform(
                        post("/dictionary/SubSystem")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(String.format(json_SubSystem, id, "CORE", "Core module"))
                                .header(Defaults.X_Request_ID, "i-6bNJRndEur5z0uSVkzzw"))
                .andExpect(header().string(Defaults.X_Request_ID, "i-6bNJRndEur5z0uSVkzzw"))
                .andExpect(status().is(200))
                .andDo(print());
        assertEquals(true, subSystemBL.findById(id).isPresent());

        */

        //update
        /*
        this.mockMvc.perform(
                        put("/dictionary/SubSystem/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(String.format(json_SubSystem, id, "CORE_NEW", "New core module name"))
                                .header(Defaults.X_Request_ID, "i-6bNJRndEur5z0uSVkzzw"))
                .andExpect(header().string(Defaults.X_Request_ID, "i-6bNJRndEur5z0uSVkzzw"))
                .andExpect(status().is(200))
                .andDo(print());
        Optional<SESubSystem> bpt = subSystemBL.findById(id);
        assertEquals(true, bpt.isPresent());
        assertEquals("CORE_NEW", bpt.get().getCode());
        assertEquals("New core module name", bpt.get().getName());

        */

        // view
        this.mockMvc.perform(
                        get("/rest/v1/orders/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(""))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andDo(print());

        //delete
        /*
        this.mockMvc.perform(
                        delete("/dictionary/SubSystem/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(Defaults.X_Request_ID, "i-6bNJRndEur5z0uSVkzzw"))
                .andExpect(header().string(Defaults.X_Request_ID, "i-6bNJRndEur5z0uSVkzzw"))
                .andExpect(status().is(200))
                .andDo(print());
        assertEquals(false, subSystemBL.findById(id).isPresent());

        */
    }
}
