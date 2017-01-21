//package kamienica.controller;
//
//import static org.mockito.Mockito.atLeastOnce;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.context.MessageSource;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.servlet.ModelAndView;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Testing;
//
//import kamienica.model.Apartment;
//import kamienica.feature.apartment.ApartmentController;
//import kamienica.feature.apartment.ApartmentService;
//import kamienica.testutils.SetupMethods;
//
//public class ApartmentControllerTest {
//	@Mock
//	ApartmentService service;
//
//	@Mock
//	MessageSource message;
//
//	@InjectMocks
//	ApartmentController controller;
//
//	@Spy
//	List<Apartment> apartments = new ArrayList<Apartment>();
//
//
//	@Spy
//	ModelAndView mvc;
//
//	@Mock
//	BindingResult result;
//
//	@BeforeClass
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//		apartments = SetupMethods.getApartmentList();
//		mvc = prepareMyModel();
//	}
//
//	@Testing
//	public void listApartments() {
//		when(service.getListForOwner()).thenReturn(apartments);
//		Assert.assertEquals(controller.apartmentList().getModel().get("apartment"), apartments);
//		Assert.assertEquals(controller.apartmentList().getViewName(), "/Admin/Apartment/ApartmentList");
//		verify(service, atLeastOnce()).getListForOwner();
//
//	}
////
////	@Testing
////	public void register() {
////		Assert.assertEquals(controller.ApartmentRegister().getModel(),
////				new ModelAndView("/Admin/Apartment/ApartmentRegister"));
////	}
//
//	//// @Testing
//	//// public void register(){
//	//// Assert.assertEquals(controller.save(apartment, result),
//	//// "registration");
//	//// Assert.assertNotNull(model.get("employee"));
//	//// Assert.assertFalse((Boolean)model.get("edit"));
//	//// Assert.assertEquals(((Apartment)myModel.get("employee")).getId(), 0);
//	////
//	//
//	//// @Testing
//	//// public void register(){
//	//// Assert.assertEquals(controller.newEmployee(model), "registration");
//	//// Assert.assertNotNull(model.get("employee"));
//	//// Assert.assertFalse((Boolean)model.get("edit"));
//	//// Assert.assertEquals(((Apartment)myModel.get("employee")).getId(), 0);
//	//// }
//	////
//	////
//	//// @Testing
//	//// public void saveEmployeeWithValidationError(){
//	//// when(result.hasErrors()).thenReturn(true);
//	//// doNothing().when(service).saveEmployee(any(Employee.class));
//	//// Assert.assertEquals(controller.saveEmployee(apartments.get(0), result,
//	//// model), "registration");
//	//// }
//	////
//	//// @Testing
//	//// public void saveEmployeeWithValidationErrorNonUniqueSSN(){
//	//// when(result.hasErrors()).thenReturn(false);
//	//// when(service.isEmployeeSsnUnique(anyInt(),
//	//// anyString())).thenReturn(false);
//	//// Assert.assertEquals(controller.saveEmployee(apartments.get(0), result,
//	//// model), "registration");
//	//// }
//	////
//	////
//	//// @Testing
//	//// public void saveEmployeeWithSuccess(){
//	//// when(result.hasErrors()).thenReturn(false);
//	//// when(service.isEmployeeSsnUnique(anyInt(),
//	//// anyString())).thenReturn(true);
//	//// doNothing().when(service).saveEmployee(any(Employee.class));
//	//// Assert.assertEquals(controller.saveEmployee(apartments.get(0), result,
//	//// model), "success");
//	//// Assert.assertEquals(model.get("success"), "Employee Axel registered
//	//// successfully");
//	//// }
//	////
//	//// @Testing
//	//// public void editEmployee(){
//	//// Employee emp = apartments.get(0);
//	//// when(service.findEmployeeBySsn(anyString())).thenReturn(emp);
//	//// Assert.assertEquals(controller.editEmployee(anyString(), model),
//	//// "registration");
//	//// Assert.assertNotNull(model.get("employee"));
//	//// Assert.assertTrue((Boolean)model.get("edit"));
//	//// Assert.assertEquals(((Employee)model.get("employee")).getId(), 1);
//	//// }
//	////
//	//// @Testing
//	//// public void updateEmployeeWithValidationError(){
//	//// when(result.hasErrors()).thenReturn(true);
//	//// doNothing().when(service).updateEmployee(any(Employee.class));
//	//// Assert.assertEquals(controller.updateEmployee(apartments.get(0),
//	//// result, model,""), "registration");
//	//// }
//	////
//	//// @Testing
//	//// public void updateEmployeeWithValidationErrorNonUniqueSSN(){
//	//// when(result.hasErrors()).thenReturn(false);
//	//// when(service.isEmployeeSsnUnique(anyInt(),
//	//// anyString())).thenReturn(false);
//	//// Assert.assertEquals(controller.updateEmployee(apartments.get(0),
//	//// result, model,""), "registration");
//	//// }
//	////
//	//// @Testing
//	//// public void updateEmployeeWithSuccess(){
//	//// when(result.hasErrors()).thenReturn(false);
//	//// when(service.isEmployeeSsnUnique(anyInt(),
//	//// anyString())).thenReturn(true);
//	//// doNothing().when(service).updateEmployee(any(Employee.class));
//	//// Assert.assertEquals(controller.updateEmployee(apartments.get(0),
//	//// result, model, ""), "success");
//	//// Assert.assertEquals(model.get("success"), "Employee Axel updated
//	//// successfully");
//	//// }
//	////
//	////
//	//// @Testing
//	//// public void deleteEmployee(){
//	//// doNothing().when(service).deleteEmployeeBySsn(anyString());
//	//// Assert.assertEquals(controller.deleteEmployee("123"),
//	//// "redirect:/list");
//	//// }
//	private ModelAndView prepareMyModel() {
//		HashMap<String, Object> model = new HashMap<>();
//		model.put("apartment", SetupMethods.getApartmentList());
//		ModelAndView mvc = new ModelAndView("/Admin/Apartment/ApartmentRegister", "model", model);
//		return mvc;
//	}
//
//	
//}
