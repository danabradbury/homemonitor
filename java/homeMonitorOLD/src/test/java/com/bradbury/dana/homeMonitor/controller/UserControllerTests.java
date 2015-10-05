package com.bradbury.dana.homeMonitor.controller;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.bradbury.dana.homeMonitor.service.SimpleProductManager;

import junit.framework.TestCase;

public class UserControllerTests extends TestCase {

    public void testHandleRequestView() throws Exception{		
        UserController controller = new UserController();
        
        controller.setProductManager(new SimpleProductManager());
        
        ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        Map modelMap = (Map) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);
    }
}
