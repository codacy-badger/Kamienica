package kamienica.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.apartment.ApartmentServiceImpl;

public class ApartmentServiceImplTest {

	@Mock
	ApartmentDao dao;

	@InjectMocks
	ApartmentServiceImpl apartmentService;

	@Spy
	List<Apartment> apartments = new ArrayList<Apartment>();

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		apartments = getApartmentList();
	}

	@Test
	public void findById() {
		Apartment ap = apartments.get(0);
		when(dao.getById((long) anyInt())).thenReturn(ap);
		Assert.assertEquals(apartmentService.getById(ap.getId()), ap);
	}

	@Test
	public void saveApartment() {
		doNothing().when(dao).save(any(Apartment.class));
		apartmentService.save(any(Apartment.class));
		verify(dao, atLeastOnce()).save(any(Apartment.class));
	}

	@Test
	public void updateApartment() {
		Apartment ap = apartments.get(0);
		when(dao.getById((long) anyInt())).thenReturn(ap);
		apartmentService.update(ap);
		verify(dao, atLeastOnce()).getById((long)anyInt());
	}

	@Test
	public void deleteApartmentById() {
		doNothing().when(dao).deleteById((long)anyInt());
		apartmentService.deleteByID((long)anyInt());
		verify(dao, atLeastOnce()).deleteById((long)anyInt());
	}

	@Test
	public void findAllApartments() {
		when(dao.getList()).thenReturn(apartments);
		Assert.assertEquals(apartmentService.getList(), apartments);
	}

	@Test
	public void findApartmentById() {
		Apartment ap = apartments.get(0);
		when(dao.getById((long) anyInt())).thenReturn(ap);
		Assert.assertEquals(apartmentService.getById((long) anyInt()), ap);
	}

	public List<Apartment> getApartmentList() {
		Apartment ap1 = new Apartment();
		ap1.setApartmentNumber(0);
		ap1.setDescription("ap1");
		ap1.setId(1L);
		ap1.setIntercom("0000");

		Apartment ap2 = new Apartment();
		ap2.setApartmentNumber(0);
		ap2.setDescription("ap2");
		ap2.setId(1L);
		ap2.setIntercom("0000");

		apartments.add(ap1);
		apartments.add(ap2);
		return apartments;
	}

}
