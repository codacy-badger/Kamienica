package kamienica.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import kamienica.feature.apartment.ApartmentConverter;
import kamienica.feature.invoice.InvoiceEnergyConverter;
import kamienica.feature.invoice.InvoiceGasConverter;
import kamienica.feature.invoice.InvoiceWaterConverter;
import kamienica.feature.meter.MeterEnergyConverter;
import kamienica.feature.meter.MeterGasConverter;
import kamienica.feature.meter.MeterWaterConverter;
import kamienica.feature.reading.ReadingEnergyConverter;
import kamienica.feature.reading.ReadingGasConverter;
import kamienica.feature.reading.ReadingWaterConverter;
import kamienica.feature.tenant.TenantConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "kamienica.*")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ReadingEnergyConverter readingEnergyConverter;
	@Autowired
	ReadingGasConverter readingGasConverter;
	@Autowired
	ReadingWaterConverter readingWaterConverter;
	@Autowired
	InvoiceGasConverter invoiceGasConverter;
	@Autowired
	InvoiceWaterConverter invoiceWaterConverter;
	@Autowired
	InvoiceEnergyConverter invoiceEnergyConverter;
	// start nowych
	@Autowired
	ApartmentConverter apartmentConverter;
	@Autowired
	MeterGasConverter meterGasConverter;
	@Autowired
	MeterEnergyConverter meterEnergyConverter;
	@Autowired
	MeterWaterConverter meterWaterConverter;
	@Autowired
	TenantConverter tenantConverter;

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}

	/*
	 * Configure ContentNegotiatingViewResolver
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jsonViewResolver());
		resolvers.add(viewResolver());
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		// viewResolver.setContentType("UTF-8");
		return viewResolver;
	}

	// @Bean
	// public LocaleResolver localeResolver() {
	// SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	// localeResolver.setDefaultLocale(Locale.ENGLISH); // change this
	// return localeResolver;
	// }
	//
	//
	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// registry.addInterceptor(localeChangeInterceptor());
	// }
	//
	// @Bean
	// public LocaleChangeInterceptor localeChangeInterceptor(){
	// LocaleChangeInterceptor localeChangeInterceptor=new
	// LocaleChangeInterceptor();
	// localeChangeInterceptor.setParamName("language");
	// return localeChangeInterceptor;
	// }

	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(readingEnergyConverter);
		registry.addConverter(readingGasConverter);
		registry.addConverter(readingWaterConverter);
		registry.addConverter(invoiceEnergyConverter);
		registry.addConverter(invoiceGasConverter);
		registry.addConverter(invoiceWaterConverter);

		registry.addConverter(apartmentConverter);
		registry.addConverter(meterGasConverter);
		registry.addConverter(meterEnergyConverter);
		registry.addConverter(meterWaterConverter);
		registry.addConverter(tenantConverter);

	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

}
