package springbook.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JUnitTest {
	
	@Autowired
	ApplicationContext context;
	private static ApplicationContext contextObj = null;

	private static Set<JUnitTest> testObjs = new HashSet<>();
	
	@Test public void test1() {
		assertThat(testObjs, is(not(hasItem(this))));
		testObjs.add(this);
		assertThat(contextObj == null || contextObj == this.context, is(true));
		contextObj = this.context;
	}
	@Test public void test2() {
		assertThat(testObjs, is(not(hasItem(this))));
		testObjs.add(this);
		assertTrue(contextObj == null || contextObj == this.context);
		contextObj = this.context;
	}
	@Test public void test3() {
		assertThat(testObjs, is(not(hasItem(this))));
		testObjs.add(this);
		assertThat(contextObj, either(is(nullValue())).or(is(this.context)));
		contextObj = this.context;
	}
	
}
