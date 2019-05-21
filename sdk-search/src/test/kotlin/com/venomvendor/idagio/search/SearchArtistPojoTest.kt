package com.venomvendor.idagio.search

import com.openpojo.reflection.impl.PojoClassFactory
import com.openpojo.validation.ValidatorBuilder
import com.openpojo.validation.rule.impl.GetterMustExistRule
import com.openpojo.validation.rule.impl.NoNestedClassRule
import com.openpojo.validation.rule.impl.SetterMustExistRule
import com.openpojo.validation.test.impl.DefaultValuesNullTester
import com.openpojo.validation.test.impl.GetterTester
import com.openpojo.validation.test.impl.SetterTester
import com.venomvendor.idagio.search.model.Artist
import com.venomvendor.idagio.search.model.Person
import com.venomvendor.idagio.search.model.SearchArtist
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class SearchArtistPojoTest {

    @Test
    fun searchArtistPojoTest() {
        val validator = ValidatorBuilder.create()
            .with(GetterMustExistRule())
            .with(SetterMustExistRule())
            .with(SetterTester())
            .with(GetterTester())
            .with(DefaultValuesNullTester())
            .with(NoNestedClassRule())
            // Build assertion criteria
            .build()

        // Add all classes
        val clzez = setOf(
            Artist::class.java,
            Person::class.java,
            SearchArtist::class.java
        )

        for (clazz in clzez) {
            // This is where the assertion happens
            validator.validate(PojoClassFactory.getPojoClass(clazz))
        }
    }
}
