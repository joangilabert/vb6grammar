package io.proleap.vb6.asg.call.type.member.scalar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class MemberScalarTypeCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File classInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/type/member/scalar/MyClass.cls");
		final File moduleInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/type/member/scalar/MemberScalarTypeCall.bas");

		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(classInputFile, moduleInputFile));

		{
			final ClazzModule myClass = program.getClazzModule("MyClass");
			assertNotNull(myClass);

			{
				final Function myFunction = myClass.getFunction("MyFunction");
				assertNotNull(myFunction);
				assertEquals(0, myFunction.getFunctionCalls().size());
			}

			{
				final PropertyGet myPropertyGet = myClass.getPropertyGet("MyPropertyGet");
				assertNotNull(myPropertyGet);
				assertEquals(0, myPropertyGet.getPropertyGetCalls().size());
			}
		}

		{
			final StandardModule myModuleScalar = program.getStandardModule("MemberScalarTypeCall");
			assertNotNull(myModuleScalar);

			{
				final io.proleap.vb6.asg.metamodel.Type myType = myModuleScalar.getType("MyType");
				assertNotNull(myType);
				assertEquals(myType, program.getTypeRegistry().getType("MyType"));

				{
					final TypeElement myFunction = myType.getTypeElement("MyFunction");
					assertNotNull(myFunction);
					assertEquals(VbBaseType.STRING, myFunction.getType());
					assertEquals(3, myFunction.getTypeElementCalls().size());
				}

				{
					final TypeElement mySub = myType.getTypeElement("MySub");
					assertNotNull(mySub);
					assertEquals(VbBaseType.STRING, mySub.getType());
					assertEquals(2, mySub.getTypeElementCalls().size());
				}
			}
		}
	}
}
