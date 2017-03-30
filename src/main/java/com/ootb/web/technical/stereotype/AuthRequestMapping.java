package com.ootb.web.technical.stereotype;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Adam on 2017-03-30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RequestMapping(value = "/auth")
public @interface AuthRequestMapping {
}
