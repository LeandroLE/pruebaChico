package com.prueba.nosolosoft.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.nosolosoft.NosolosoftApplication;
import com.prueba.nosolosoft.controllers.HotelsController;
import com.prueba.nosolosoft.entities.Hotel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NosolosoftApplication.class)
@ActiveProfiles("test")
public class HotelsControllerTest {

	private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/hotels/[0-9]+";
	
    @InjectMocks
    HotelsController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    /**
     * Comprobamos q la BBDD esté vacía.
     * Con $.content miramos en el content de la Page que devuelve,
     * que sea una lista vacía.
     * @throws Exception
     */
    @Test
    public void shouldHaveEmptyDB() throws Exception {
        mvc.perform(get("/hotels")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }
	
    @Test
    public void crearObtenerBorrarPetar() throws Exception {
        Hotel r1 = this.mockHotel("Hotel Eurostar Palace", 5);
        byte[] r1Json = toJson(r1);

        //Creamos un hotel
        MvcResult result = mvc.perform(post("/hotels")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern(RESOURCE_LOCATION_PATTERN))
                .andReturn();
        long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());

        //Obtenemos el hotel
        mvc.perform(get("/hotels/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) id)))
                .andExpect(jsonPath("$.name", is(r1.getName())))
                .andExpect(jsonPath("$.category", is(r1.getCategory())));

        //Borramos el hotel
        mvc.perform(delete("/hotels/" + id))
                .andExpect(status().isNoContent());

        //Obtener el hotel debe petar
        mvc.perform(get("/hotels/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    
    
     //******************************
      
    
    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }
    
    private Hotel mockHotel(String name, Integer category) {
        Hotel r = new Hotel(name, category);
        return r;
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

    // match redirect header URL (aka Location header)
    private static ResultMatcher redirectedUrlPattern(final String expectedUrlPattern) {
        return new ResultMatcher() {
            public void match(MvcResult result) {
                Pattern pattern = Pattern.compile("\\A" + expectedUrlPattern + "\\z");
                assertTrue(pattern.matcher(result.getResponse().getRedirectedUrl()).find());
            }
        };
    }
}
