package com.example.calculator.scanner;

import com.example.calculator.annotations.EnableCalculatorProcessor;
import com.example.calculator.factory.CalculatorProcessorProxyBeanFactory;
import com.example.calculator.processor.CalculatorProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.*;

public class CalculatorScanner implements ImportBeanDefinitionRegistrar {

    private BeanDefinitionRegistry registry;

    public void initializeCalculatorProcessor(List<String> packages) {
        Collection<Class<?>> classes = findCalculatorProcessorClasses(packages);
        for(Class<?> t : classes)
            registerBean(t);
    }


    public final Collection<Class<?>> findCalculatorProcessorClasses(List<String> packages) {
        ClasspathScanner scanner = new ClasspathScanner(false);
        final List<Class<?>> classes = new ArrayList<>();

        for (final String basePackage : packages) {
            for (final BeanDefinition candidate : scanner.findCandidateComponents(basePackage)) {
                Class<?> resolvedClass = ClassUtils.resolveClassName(candidate.getBeanClassName(),
                        ClassUtils.getDefaultClassLoader());
                if (CalculatorProcessor.class.isAssignableFrom(resolvedClass))
                    classes.add(resolvedClass);
            }
        }

        return classes;
    }

    public void registerBean(Class<?> type){
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(CalculatorProcessorProxyBeanFactory.class);
        builder.addConstructorArgValue(type);
        builder.setLazyInit(true);
        registry.registerBeanDefinition(type.getCanonicalName(), builder.getBeanDefinition());
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        this.registry = registry;

        if (metadata.getAllAnnotationAttributes(EnableCalculatorProcessor.class.getName()) == null)
            return;
        AnnotationAttributes attributes = new AnnotationAttributes(metadata.getAnnotationAttributes(EnableCalculatorProcessor.class.getName()));

        List<String> packages = new LinkedList<>();
        packages.addAll(Arrays.asList(attributes.getStringArray("value")));
        packages.addAll(Arrays.asList(attributes.getStringArray("basePackage")));

        if (packages.size() == 0)
        {
            String className = metadata.getClassName();
            packages.add(className.substring(0, className.lastIndexOf('.')));
        }
        initializeCalculatorProcessor(packages);
    }
}
