package de.dkh.springdemoaoppointcutdeclarations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Configuration of the app.
 * We are using {@linkplain EnableAspectJAutoProxy} from AspectJ here.
 * With this Spring AOP can use proxies.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("de.dkh.springdemoaoppointcutdeclarations")
public class DemoConfig {
}
