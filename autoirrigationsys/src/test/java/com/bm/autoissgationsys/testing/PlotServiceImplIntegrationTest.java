package com.bm.autoissgationsys.testing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bm.autoirrigationsys.AutoirrigationsysApplication;
import com.bm.autoirrigationsys.dto.PlotDto;
import com.bm.autoirrigationsys.model.PlotEB;
import com.bm.autoirrigationsys.repo.PlotRepository;
import com.bm.autoirrigationsys.services.PlotServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AutoirrigationsysApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
public class PlotServiceImplIntegrationTest {

	@TestConfiguration
	static class PlotServiceImplTestContextConfiguration {
		@Bean
		public PlotServiceImpl PlotService() {
			return new PlotServiceImpl();
		}
	}

	@Autowired
	private PlotServiceImpl plotService;

	@Autowired
	ModelMapper modelMapper;

	@MockBean
	private PlotRepository plotRepo;

	public PlotEB expected;

	@BeforeEach
	public void setUp() {
		expected = new PlotEB(1l, 9.9, "Orange", "Delta");

		Mockito.when(plotRepo.findById(expected.getPlotId())).thenReturn(Optional.of(expected));
		Mockito.when(plotRepo.findById(-99l)).thenReturn(Optional.empty());
	}

	@Test
	public void whenValidId_thenPlotShouldBeFound() {

		PlotDto fromDb = plotService.getPlotById(1l);
		PlotDto expectedDto = modelMapper.map(expected, PlotDto.class);
		assertThat((fromDb).equals(expectedDto));
		verifyFindByIdIsCalledOnce();
	}

	@Test
	public void whenInValidId_thenPlotShouldNotBeFound() {
		PlotDto fromDb = plotService.getPlotById(-99l);
		verifyFindByIdIsCalledOnce();
		assertThat(fromDb).isNull();
	}

	private void verifyFindByIdIsCalledOnce() {
		Mockito.verify(plotRepo, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
		Mockito.reset(plotRepo);
	}

}
