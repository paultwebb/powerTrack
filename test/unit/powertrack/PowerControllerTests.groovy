package powertrack



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PowerController)
class PowerControllerTests {

    void testSomething() {
		controller.index()
		assert 'Welcome to powerTrack'
    }
}
