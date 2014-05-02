/*
 * spring-console (c) by Krzysztof Bogdan
 *
 * spring-console is licensed under a
 * Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this
 * work.  If not, see <http://creativecommons.org/licenses/by-sa/4.0/>.
 */
package com.mobilepetroleum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Bean
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(AvailableOnClasspathCondition.class)
public @interface AvailableOnClasspath {
    public String value();
}
