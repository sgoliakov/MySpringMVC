package logic.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//джава класс который заменяет web.xml
public class MySpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

//подставляем класс(анологичный файл спринг конфигурации) наш SpringConfig
//теперь он знает где находится конфигурация спринга
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }
//все запросы от пользователя посылаем на диспетчер сервлет
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
