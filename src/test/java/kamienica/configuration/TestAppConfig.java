package kamienica.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.apartment.IApartmentService;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.meter.MeterService;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.residence.IResidenceService;
import kamienica.feature.residence.ResidenceServiceImpl;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
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
@ComponentScan(basePackages = {"kamienica.*"})
public class TestAppConfig extends WebMvcConfigurerAdapter {

	private final static Logger LOG = LoggerFactory.getLogger(TestAppConfig.class);


	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}
	@Bean
	public IResidenceService residenceService() {
		return Mockito.mock(ResidenceServiceImpl.class);
	}

	@Bean
	public IMeterService meterService() {
		return Mockito.mock(MeterService.class);
	}
	@Bean
	public IReadingService readingService() {
		return Mockito.mock(ReadingService.class);
	}

	@Bean
	public IApartmentService apartmentService() {
		return Mockito.mock(ApartmentService.class);
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		List<ViewResolver> resolvers = new ArrayList<>();
		resolvers.add(jsonViewResolver());
		resolvers.add(viewResolver());
		resolver.setViewResolvers(resolvers);
		LOG.info("Setting resolvers", resolver);
		return resolver;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".jsp");
		// viewResolver.setContentType("UTF-8");
		return viewResolver;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
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
