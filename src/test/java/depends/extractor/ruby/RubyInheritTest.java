package depends.extractor.ruby;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import depends.deptypes.DependencyType;
import depends.relations.Relation;

public class RubyInheritTest extends RubyParserTest {
	@Before
	public void setUp() {
		super.init();
	}
	@Test
	public void test_relation() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/ruby-code-examples/extends.rb",
	    	    };
	    
	    for (String src:srcs) {
		    RubyFileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertEquals(1,entityRepo.getEntity("Cat").getRelations().size());
        Relation r = entityRepo.getEntity("Cat").getRelations().get(0);
        assertEquals(DependencyType.INHERIT,r.getType());
        assertEquals("Animal",r.getEntity().getRawName());
	}
	
	@Test
	public void test_relation_of_included_file() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/ruby-code-examples/extends_animal.rb",
	    		"./src/test/resources/ruby-code-examples/extends_cat.rb",
	    	    };
	    
	    for (String src:srcs) {
		    RubyFileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertEquals(1,entityRepo.getEntity("Cat").getRelations().size());
        Relation r = entityRepo.getEntity("Cat").getRelations().get(0);
        assertEquals(DependencyType.INHERIT,r.getType());
        assertEquals("Animal",r.getEntity().getRawName());
	}
	
	
	@Test
	public void test_relation_with_cpath_1() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/ruby-code-examples/extends_with_cpath.rb",
	    	    };
	    
	    for (String src:srcs) {
		    RubyFileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertEquals(1,entityRepo.getEntity("Cat").getRelations().size());
        Relation r = entityRepo.getEntity("Cat").getRelations().get(0);
        assertEquals(DependencyType.INHERIT,r.getType());
        assertEquals("Animal",r.getEntity().getQualifiedName());
	}
	
	@Test
	public void test_relation_with_cpath_2() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/ruby-code-examples/extends_with_cpath.rb",
	    	    };
	    
	    for (String src:srcs) {
		    RubyFileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertEquals(1,entityRepo.getEntity("ZooCat").getRelations().size());
        Relation r = entityRepo.getEntity("ZooCat").getRelations().get(0);
        assertEquals(DependencyType.INHERIT,r.getType());
        assertEquals("Zoo.Animal",r.getEntity().getQualifiedName());
	}
	
	@Test
	public void test_relation_with_cpath_3() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/ruby-code-examples/extends_with_cpath.rb",
	    	    };
	    
	    for (String src:srcs) {
		    RubyFileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertEquals(1,entityRepo.getEntity("Zoo.Cow").getRelations().size());
        Relation r = entityRepo.getEntity("Zoo.Cow").getRelations().get(0);
        assertEquals(DependencyType.INHERIT,r.getType());
        assertEquals("Zoo.Animal",r.getEntity().getQualifiedName());
	}
	
	
	@Test
	public void test_relation_with_alias() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/ruby-code-examples/extends_with_alias.rb",
	    	    };
	    
	    for (String src:srcs) {
		    RubyFileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertEquals(1,entityRepo.getEntity("Cat").getRelations().size());
        Relation r = entityRepo.getEntity("Cat").getRelations().get(0);
        assertEquals(DependencyType.INHERIT,r.getType());
        assertEquals("Zoo_Animal",r.getEntity().getQualifiedName());
	}
}