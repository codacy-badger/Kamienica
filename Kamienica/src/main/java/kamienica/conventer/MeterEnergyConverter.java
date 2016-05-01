//package kamienica.conventer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//
//import kamienica.model.MeterEnergy;
//import kamienica.service.MeterService;
//
//public class MeterEnergyConverter implements Converter<Object, MeterEnergy> {
//
//	@Autowired
//	MeterService service;
//
//	@Override
//	public MeterEnergy convert(Object element) {
//		Integer id = Integer.parseInt((String) element);
//		return service.getEnergyByID(id);
//	}
//
//}
