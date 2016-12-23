package com.app.config.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SourceCodeAuthorMethod {

	public enum Complexity {
		LOW, MEDIUM, HIGH
	}

	public Complexity complexity() default Complexity.LOW;

	public String featureReference() default "";

	public String requestID() default "";

	public String[] comments() default "";

	public String createdBy() default "author";

	public String updatedBy() default "author";

	public String lastModified() default "2015/04/01";

	public long versionNumber() default 1;

	public boolean enabled() default true;
}
