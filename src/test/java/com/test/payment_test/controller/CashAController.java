//package com.test.payment_test.controller;
//
//import com.test.payment_test.service.CashService;
////import com.test.payment_test.service.impl.CashService;
//import org.junit.jupiter.api.Test;
////import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static javax.swing.UIManager.get;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
////@RunWith(SpringRunner.class)
//@WebMvcTest(CashAController.class)  // Replace YourController with your actual controller class
//public class CashAController {
//
//        @Autowired
//        private MockMvc mockMvc;
//
//        @MockBean
//        private CashService yourService;  // Replace YourService with the actual service used by the controller
//
//        @Test
//        public void testYourControllerMethod() throws Exception {
//            // Mocking service behavior if needed
////            when(yourService.someMethod()).thenReturn("MockedResult");
//
//            // Perform the request and assert the response
////            mockMvc.perform(get("/sendMoney"))
//                    .andExpect(status().isOk())
//                    .andExpect(view().name("your-view-name"))  // Replace with your expected view name
//                    .andExpect(model().attribute("someAttribute", "expectedValue"));  // Replace with your expected model attribute
//
//            // Optionally, verify interactions with the mocked service
//            verify(yourService, times(1)).someMethod();
//        }
//    }
