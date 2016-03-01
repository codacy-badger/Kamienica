// package kamienica.logic;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Autowired;
//
// import kamienica.core.DivisionManager;
// import kamienica.model.Apartment;
// import kamienica.model.Division;
// import kamienica.model.Tenant;
// import kamienica.service.ApartmentService;
// import kamienica.service.DivisionService;
// import kamienica.service.TenantService;
//
// public class DivisionLogic {
//
// @Autowired
// private ApartmentService apartmentService;
// @Autowired
// private TenantService tenantService;
// @Autowired
// private DivisionService divisionService;
//
// @InitBinder
// protected void initBinder(HttpServletRequest request,
// ServletRequestDataBinder binder) {
// binder.registerCustomEditor(Apartment.class, new
// ApartmentIB(this.apartmentService));
// binder.registerCustomEditor(Tenant.class, new
// TenantIB(this.tenantService));
// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
// sdf.setLenient(true);
// binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
// }
//
// public void updateDivision() {
// List<Apartment> apartments = apartmentService.getList();
// List<Tenant> tenants = tenantService.getList();
// ArrayList<Division> divisionList =
// DivisionManager.prepareDivisionListForRegistration(tenants, apartments);
//
// divisionService.saveList(divisionList);
// }
//
// public ModelAndView divisionRegister(DivisionForm divisionForm,
// BindingResult result) {
// Map<String, Object> model = new HashMap<String, Object>();
// List<Tenant> tenantList = tenantService.getCurrentTenants();
// List<Apartment> apartmentList = apartmentService.getList();
// if (tenantList.isEmpty() || apartmentList.isEmpty()) {
// model.put("error", "Brakuje danych. Upewnij się czy dane dotyczące
// mieszkań i najemców są poprawne");
// return new ModelAndView("/Admin/Division/DivisionRegister", "model",
// model);
// }
// ArrayList<Division> divisionList =
// DivisionManager.prepareDivisionListForRegistration(tenantList,
// apartmentList);
// divisionForm.setDivisionList(divisionList);
// model.put("apartment", apartmentList);
// model.put("tenantList", tenantList);
// return new ModelAndView("/Admin/Division/DivisionRegister", "model",
// model);
// }
//
// public ModelAndView divisionSave(DivisionForm divisionForm, BindingResult
// result) {
// Date date = divisionForm.getDate();
// List<Division> divisionList = divisionForm.getDivisionList();
// for (int i = 0; i < divisionList.size(); i++) {
// divisionList.get(i).setDate(date);
// }
// List<Apartment> apartmentList = apartmentService.getList();
//
// if (DivisionValidator.checksumForDivision(apartmentList, divisionList)) {
// divisionService.saveList(divisionList);
// return new ModelAndView("redirect:/Admin/Division/divisionList.html");
// } else {
// Map<String, Object> model = new HashMap<String, Object>();
// String error = "B��d. Sprawd� poprawno�� danych";
// model.put("error", error);
// model.put("apartment", apartmentList);
// model.put("tenantList", tenantService.getCurrentTenants());
// return new ModelAndView("/Admin/Division/DivisionRegister", "model",
// model);
// }
// }
//
// public ModelAndView divisionList() {
//
// System.out.println("tu jestem");
// Map<String, Object> model = new HashMap<String, Object>();
// DivisionForm divisionForm = new DivisionForm();
// divisionForm.setDivisionList((ArrayList<Division>)
// divisionService.getList());
// model.put("tenantList", tenantService.getCurrentTenants());
// model.put("apartment", apartmentService.getList());
// model.put("divisionForm", divisionForm);
// ModelAndView out = new ModelAndView("/Admin/Division/DivisionList",
// "model", model);
// return out;
//
// }
// }
