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

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

class AvailableOnClasspathCondition implements Condition {

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(AvailableOnClasspath.class.getName());
        String className = String.valueOf(attributes.get("value"));
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
        }
        return false;
    }

}
