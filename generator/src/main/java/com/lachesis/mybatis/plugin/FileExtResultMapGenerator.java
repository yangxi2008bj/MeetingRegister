package com.lachesis.mybatis.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;

public class FileExtResultMapGenerator extends AbstractXmlElementGenerator {

	public FileExtResultMapGenerator() {
		super();
	}

	@Override
	public void addElements(XmlElement parentElement) {		
		
		XmlElement answer = new XmlElement("resultMap"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("id", //$NON-NLS-1$
				introspectedTable.getTableConfiguration().getDomainObjectName() + "Ext"));

		String returnType;
		JavaModelGeneratorConfiguration config = context.getJavaModelGeneratorConfiguration();
		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		returnType = config.getTargetPackage() + "." + objectName + "Ext";

		answer.addAttribute(new Attribute("type", //$NON-NLS-1$
				returnType));

		JavaClientGeneratorConfiguration clientConfig = context.getJavaClientGeneratorConfiguration();
		answer.addAttribute(new Attribute("extends", //$NON-NLS-1$
				clientConfig.getTargetPackage() + "." + objectName + "Mapper.BaseResultMap"));

		context.getCommentGenerator().addComment(answer);

		@SuppressWarnings("unused")
		int i = 1;
		if (stringHasValue(introspectedTable.getSelectByPrimaryKeyQueryId())
				|| stringHasValue(introspectedTable.getSelectByExampleQueryId())) {
			i++;
		}

		if (context.getPlugins().sqlMapResultMapWithoutBLOBsElementGenerated(answer, introspectedTable)) {
			parentElement.addElement(answer);
		}
	}
}
