package kamienica.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import kamienica.core.conventer.InvoiceWaterConverter;
import kamienica.core.conventer.ReadingEnergyConverter;
import kamienica.core.conventer.ReadingGasConverter;
import kamienica.core.conventer.ReadingWaterConverter;
import kamienica.feature.invoice.InvoiceEnergyConverter;
import kamienica.feature.invoice.InvoiceGasConverter;
import kamienica.feature.meter.MeterEnergyConverter;
import kamienica.feature.meter.MeterGasConverter;
import kamienica.feature.meter.MeterWaterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "kamienica.*")
public class AppConfig extends WebMvcConfigurerAdapter {

	public static final String VERSION = "1.0";
	@Autowired
	private ReadingEnergyConverter readingEnergyConverter;
	@Autowired
	private ReadingGasConverter readingGasConverter;
	@Autowired
	private ReadingWaterConverter readingWaterConverter;
	@Autowired
	private InvoiceGasConverter invoiceGasConverter;
	@Autowired
	private InvoiceWaterConverter invoiceWaterConverter;
	@Autowired
	private InvoiceEnergyConverter invoiceEnergyConverter;
//	@Autowired
//	private ApartmentConverter apartmentConverter;
	@Autowired
	private MeterGasConverter meterGasConverter;
	@Autowired
	private MeterEnergyConverter meterEnergyConverter;
	@Autowired
	private MeterWaterConverter meterWaterConverter;
//	@Autowired
//	private TenantConverter tenantConverter;

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
		List<ViewResolver> resolvers = new ArrayList<>();
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

//		registry.addConverter(apartmentConverter);
		registry.addConverter(meterGasConverter);
		registry.addConverter(meterEnergyConverter);
		registry.addConverter(meterWaterConverter);
//		registry.addConverter(tenantConverter);

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

	
	private ObjectMapper objectMapper() {
        Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean();
        bean.setIndentOutput(true);
        bean.setSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        bean.afterPropertiesSet();
        ObjectMapper objectMapper = bean.getObject();
        objectMapper.registerModule(new JodaModule());
        return objectMapper;
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }
}
