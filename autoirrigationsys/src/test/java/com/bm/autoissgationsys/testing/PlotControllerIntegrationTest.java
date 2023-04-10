package com.bm.autoissgationsys.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bm.autoirrigationsys.AutoirrigationsysApplication;
import com.bm.autoirrigationsys.model.PlotEB;
import com.bm.autoirrigationsys.repo.PlotRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AutoirrigationsysApplication.class)
@AutoConfigureMockMvc 
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class PlotControllerIntegrationTest {
	 @Autowired
	    private MockMvc mvc;

	    @Autowired
	    private PlotRepository repository;
	    

	    @Test
	    public void whenValidInput_thenCreatePlot() throws IOException, Exception {
	    	
			PlotEB expected = new PlotEB(1l,9.9,"Orange","Delta");
	        mvc.perform(post("/plot/add").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(expected)));

	        Optional<PlotEB> found = repository.findById(1l);
	        assertThat(found.get().getCrop()).isEqualTo(expected.getCrop());    }

//	    @Test
//	    public void givenPlots_whenGetPlots_thenStatus200() throws Exception {
//			PlotEB testPlot = new PlotEB();
//
//	    	createTestPlot(1l,9.9,"Orange","Delta");
//
//
//	        mvc.perform(post("/api/alterEmployeeState?employeeId="+eb.getId()))
//	          .andDo(print())
//	          .andExpect(status().isOk());
//	         
//	    }
//
//
//	 
//	    private void createTestPlot(long plotId, Double size, @NonNull String crop, @NonNull String location) {
//			PlotEB testPlot = new PlotEB(plotId,size,crop,location);
//	        repository.saveAndFlush(testPlot);
//	    }

}
