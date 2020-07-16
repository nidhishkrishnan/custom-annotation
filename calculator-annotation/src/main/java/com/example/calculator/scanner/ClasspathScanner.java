package com.example.calculator.scanner;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

class ClasspathScanner extends ClassPathScanningCandidateComponentProvider {

	ClasspathScanner(boolean useDefaultFilters) {
		super(useDefaultFilters);
		addIncludeFilter((metadataReader, metadataReaderFactory) -> metadataReader.getClassMetadata().isInterface());
	}
	
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return true;
	}
}
