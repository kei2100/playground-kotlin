package com.github.kei2100.spring

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.annotation.RequestScope
import java.lang.reflect.Method
import kotlin.test.assertTrue

@SpringBootTest
class DetectBadConfigurationTest {
    /**
     * @Configuration クラスで @Bean ファクトリメソッドと @RequestScope の組み合わせがつかわれていないことのテスト.
     * Kotlin では @Bean ファクトリメソッドと @RequestScope の組み合わせは機能せず、リクエストごとに異なる Bean が生成されないため危険
     * https://github.com/spring-projects/spring-framework/issues/32287
     */
    @Test
    fun `@Bean ファクトリメソッドで @RequestScope が使用されていないこと`() {
        val currentPackage = DetectBadConfigurationTest::class.java.packageName
        val configClasses = findConfigurationClasses(currentPackage)
        val badAnnotatedMethods = findAnnotatedMethods(configClasses, Bean::class.java, RequestScope::class.java)
        assertTrue(
            badAnnotatedMethods.isEmpty(),
            "@Bean と @RequestScope が同時に付与されたメソッドが見つかりました: $badAnnotatedMethods"
        )
    }

    /**
     * 指定されたパッケージ内の @Configuration アノテーションが付与されたクラスを見つける
     */
    private fun findConfigurationClasses(packageName: String): Set<Class<*>> {
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        // @Configuration アノテーションが付与されたクラスのみを対象
        scanner.addIncludeFilter(AnnotationTypeFilter(Configuration::class.java))

        val classes = mutableSetOf<Class<*>>()
        val components = scanner.findCandidateComponents(packageName)

        for (component in components) {
            val className = component.beanClassName
            if (className != null) {
                val clazz = Class.forName(className)
                classes.add(clazz)
            }
        }
        return classes
    }

    /**
     * 指定されたクラスリストから methodAnnotations が付与されたメソッドを見つける
     */
    private fun findAnnotatedMethods(
        classes: Set<Class<*>>,
        vararg methodAnnotations: Class<out Annotation>
    ): Map<Class<*>, List<Method>> {
        val result = mutableMapOf<Class<*>, List<Method>>()

        for (clazz in classes) {
            val annotatedMethods = clazz.declaredMethods.filter { method ->
                methodAnnotations.all { method.isAnnotationPresent(it) }
            }
            if (annotatedMethods.isNotEmpty()) {
                result[clazz] = annotatedMethods
            }
        }
        return result
    }
}
