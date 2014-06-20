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

import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {

    @AvailableOnClasspath("com.mobilepetroleum.SpringConsole")
    Object springConsole() {
        return new SpringConsole();
    }

    @AvailableOnClasspath("org.postgresql.Driver")
    String postgresDriver() {
        return "org.postgresql.Driver";
    }

}
